package com.example.archiveservice;

import com.example.archiveservice.db.hot.dto.DeviceLogDTO;
import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import com.example.archiveservice.db.hot.enums.MessageTypeEnum;
import com.example.archiveservice.db.hot.repository.DeviceLogCommandRepository;
import com.example.archiveservice.db.hot.service.DeviceLogQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceLogQueryTest {
    @Autowired
    private DeviceLogQueryService deviceLogQueryService;
    @Autowired
    private DeviceLogCommandRepository repository;
    @BeforeEach
    public void init() {
        DeviceLogEntity deviceLogEntity = new DeviceLogEntity().toBuilder()
                .uuid(UUID.randomUUID())
                .eventTime(Instant.now())
                .createAt(Instant.now())
                .messageTypeEnum(MessageTypeEnum.NOTIFICATION)
                .payload("{\"Status\":\"OK\"}")
                .build();
        repository.save(deviceLogEntity);

    }
    @Test
    public void queryDTOTest() throws Exception {
        List<DeviceLogDTO> deviceLogDTOS = deviceLogQueryService.findAll(10, 20);
        assertNull(deviceLogDTOS);

        DeviceLogEntity deviceLogEntity = new DeviceLogEntity().toBuilder()
                .uuid(UUID.randomUUID())
                .eventTime(Instant.now().minus(5, ChronoUnit.DAYS))
                .createAt(Instant.now().minus(5, ChronoUnit.DAYS))
                .messageTypeEnum(MessageTypeEnum.NOTIFICATION)
                .payload("{\"Status\":\"OK\"}")
                .build();
        repository.save(deviceLogEntity);
        deviceLogDTOS = deviceLogQueryService.findAll(3, 20);
        assertNotNull(deviceLogDTOS);
        assertEquals(1, deviceLogDTOS.size());
    }
}
