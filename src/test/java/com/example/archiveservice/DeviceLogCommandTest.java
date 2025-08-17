package com.example.archiveservice;

import com.example.archiveservice.db.hot.command.DeviceLogCommand;
import com.example.archiveservice.db.hot.dto.DeviceLogDTO;
import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import com.example.archiveservice.db.hot.enums.MessageTypeEnum;
import com.example.archiveservice.db.hot.repository.DeviceLogCommandRepository;
import com.example.archiveservice.db.hot.service.DeviceLogCommandService;
import com.example.archiveservice.db.hot.service.DeviceLogQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceLogCommandTest {
    @Autowired
    private DeviceLogCommandService deviceLogCommandService;
    @Autowired
    private DeviceLogCommandRepository repository;
    @Autowired
    private DeviceLogQueryService deviceLogQueryService;

    @Test
    public void deleteTest(){
        DeviceLogEntity deviceLogEntity = new DeviceLogEntity().toBuilder()
                .uuid(UUID.randomUUID())
                .eventTime(Instant.now().minus(5, ChronoUnit.DAYS))
                .createAt(Instant.now().minus(5, ChronoUnit.DAYS))
                .messageTypeEnum(MessageTypeEnum.NOTIFICATION)
                .payload("{\"Status\":\"OK\"}")
                .build();
        repository.save(deviceLogEntity);
        List<DeviceLogDTO> deviceLogDTOS = deviceLogQueryService.findAll(3, 20);
        assertNotNull(deviceLogDTOS);
        assertEquals(1, deviceLogDTOS.size());

        List<DeviceLogCommand> deviceLogCommands = deviceLogDTOS.stream().map(e -> new DeviceLogCommand(e.getId())).collect(Collectors.toList());

        deviceLogCommandService.batchDelete(deviceLogCommands);
        deviceLogDTOS = deviceLogQueryService.findAll(3, 20);
        assertNull(deviceLogDTOS);

    }
}
