package com.example.archiveservice;

import com.example.archiveservice.db.cold.command.DeviceLogHistoryCommand;
import com.example.archiveservice.db.cold.entity.DeviceLogHistoryEntity;
import com.example.archiveservice.db.cold.service.DeviceLogHistoryCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DeviceLogHistorySaveTest {
    @Autowired
    private DeviceLogHistoryCommandService deviceLogHistoryService;

    @Test
    public void insertTest() throws Exception {
        DeviceLogHistoryCommand deviceLogHistoryCommand = new DeviceLogHistoryCommand()
                .toBuilder()
                .deviceId(1L)
                .id(1L)
                .uid(UUID.randomUUID().toString())
                .eventTime(Instant.now())
                .createAt(Instant.now())
                .messageType("NOTIFICATION")
                .payload("{\"Status\":\"OK\"}")
                .build();
        List<DeviceLogHistoryCommand> commands = new ArrayList<>();
        commands.add(deviceLogHistoryCommand);
        List<DeviceLogHistoryEntity> savedEntities = deviceLogHistoryService.saveOrUpdate(commands);
        assertNotNull(savedEntities);
        assertEquals(1, savedEntities.size());
    }
}
