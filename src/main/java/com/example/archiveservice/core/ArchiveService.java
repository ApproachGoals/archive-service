package com.example.archiveservice.core;

import com.example.archiveservice.db.cold.entity.DeviceLogHistoryEntity;
import com.example.archiveservice.db.cold.enums.HistoryMessageTypeEnum;
import com.example.archiveservice.db.cold.service.DeviceLogHistoryCommandService;
import com.example.archiveservice.db.hot.command.DeviceLogCommand;
import com.example.archiveservice.db.hot.dto.DeviceLogDTO;
import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import com.example.archiveservice.db.hot.service.DeviceLogCommandService;
import com.example.archiveservice.db.hot.service.DeviceLogQueryService;
import com.example.archiveservice.mapper.GeneralStructMapperTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class ArchiveService {
    @Autowired
    private DeviceLogQueryService deviceLogQueryService;
    @Autowired
    private DeviceLogCommandService deviceLogCommandService;
    @Autowired
    private DeviceLogHistoryCommandService deviceLogHistoryCmd;
    private Specification<DeviceLogEntity> olderThan(Instant threshold) {
        return (root, query, cb) -> cb.lessThan(root.get("eventTime"), threshold);
    }
    @Transactional(transactionManager = "hotTransactionManager", readOnly = true)
    public List<DeviceLogDTO> fetchOldLogs(int retentionDays, int batchSize) {
        return deviceLogQueryService.findAll(retentionDays, batchSize);
    }

    @Transactional(transactionManager = "coldTransactionManager")
    public void writeToColdDB(List<DeviceLogDTO> batch) {
        List<DeviceLogHistoryEntity> histories = batch.stream()
                .map(d ->
                        new DeviceLogHistoryEntity(
                                d.getId(),
                                GeneralStructMapperTools.mapStringToUUID(d.getUid()),
                                d.getPayload(),
                                HistoryMessageTypeEnum.valueOf(d.getMessageType()),
                                d.getDeviceId(),
                                d.getCreateAt(),
                                d.getEventTime()
                                )
                )
                .toList();
        deviceLogHistoryCmd.saveAll(histories);
    }
    @Transactional(transactionManager = "hotTransactionManager")
    public void deleteFromHotDB(List<DeviceLogDTO> batch) {
        List<DeviceLogCommand> deviceLogCommands = batch.stream().map(
                d->
                    new DeviceLogCommand(d.getId())
        ).toList();
        deviceLogCommandService.deleteByIds(deviceLogCommands);
    }

    public void archiveDevices(int retentionDays, int batchSize) {
        List<DeviceLogDTO> batch;
        do {
            batch = fetchOldLogs(retentionDays, batchSize);//deviceLogQuery.findOldDeviceLogs(threshold, pageable);
            if (!batch.isEmpty()) {
                // write ColdDB
                writeToColdDB(batch);
                // delete from HotDB
                deleteFromHotDB(batch);
            }
        } while (!batch.isEmpty());
    }
}
