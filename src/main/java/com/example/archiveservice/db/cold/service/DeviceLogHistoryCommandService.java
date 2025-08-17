package com.example.archiveservice.db.cold.service;

import com.example.archiveservice.db.cold.command.DeviceLogHistoryCommand;
import com.example.archiveservice.db.cold.entity.DeviceLogHistoryEntity;
import com.example.archiveservice.db.cold.mapper.DeviceLogHistoryStructMapper;
import com.example.archiveservice.db.cold.repository.DeviceLogHistoryCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceLogHistoryCommandService {
    @Autowired
    private DeviceLogHistoryCommandRepository deviceLogHistoryRepository;
    @Autowired
    private DeviceLogHistoryStructMapper deviceLogHistoryStructMapper;
    @Transactional(transactionManager = "coldTransactionManager")
    public List<DeviceLogHistoryEntity> saveOrUpdate(List<DeviceLogHistoryCommand> deviceLogHistoryCommands) {
        return deviceLogHistoryRepository.saveAllAndFlush(deviceLogHistoryStructMapper.commands2Entities(deviceLogHistoryCommands));
    }
    @Transactional(transactionManager = "coldTransactionManager")
    public void saveAll(List<DeviceLogHistoryEntity> deviceLogHistoryEntities) {
        deviceLogHistoryRepository.saveAll(deviceLogHistoryEntities);
    }
}
