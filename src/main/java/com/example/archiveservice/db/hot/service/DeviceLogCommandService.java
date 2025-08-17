package com.example.archiveservice.db.hot.service;

import com.example.archiveservice.db.hot.command.DeviceLogCommand;
import com.example.archiveservice.db.hot.repository.DeviceLogCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceLogCommandService {
    @Autowired
    private DeviceLogCommandRepository deviceLogCommandRepository;
    @Transactional(transactionManager = "hotTransactionManager")
    public void batchDelete(List<DeviceLogCommand> deviceCommands) {
        deviceLogCommandRepository.deleteByIds(deviceCommands
                .stream()
                .map(DeviceLogCommand::getId)
                .collect(Collectors.toList())
        );
    }
    @Transactional(transactionManager = "hotTransactionManager")
    public void deleteByIds(List<DeviceLogCommand> deviceLogCommands) {
        deviceLogCommandRepository.deleteByIds(deviceLogCommands.stream().map(DeviceLogCommand::getId).collect(Collectors.toList()));
    }
}
