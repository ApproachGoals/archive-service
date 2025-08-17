package com.example.archiveservice;

import com.example.archiveservice.db.archive.command.ArchiveCheckpointCommand;
import com.example.archiveservice.db.archive.entity.ArchiveCheckpointEntity;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import com.example.archiveservice.db.archive.service.ArchiveCheckpointCommandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArchiveCheckpointCommandTest {
    @Autowired
    private ArchiveCheckpointCommandService archiveCheckpointCommandService;
    @AfterEach
    public void init() throws Exception {
        List<ArchiveCheckpointEntity> archiveCheckpointEntities = archiveCheckpointCommandService.findAll();
        for (ArchiveCheckpointEntity archiveCheckpointEntity : archiveCheckpointEntities) {
            archiveCheckpointCommandService.deleteById(new ArchiveCheckpointCommand().toBuilder().id(archiveCheckpointEntity.getId()).build());
        }
    }
    @Test
    public void insert() throws Exception {
        ArchiveCheckpointCommand archiveCheckpointCommand = new ArchiveCheckpointCommand().toBuilder()
                .entityName("device_log").lastId(20L).lastEventTime(Instant.now().minus(2, ChronoUnit.DAYS)).build();
        ArchiveCheckpointEntity archiveCheckpointEntity = archiveCheckpointCommandService.saveOrUpdate(archiveCheckpointCommand);
        assertNotNull(archiveCheckpointEntity);
        assertNotNull(archiveCheckpointEntity.getId());
    }

    @Test
    public void update() throws Exception {
        ArchiveCheckpointCommand archiveCheckpointCommand = new ArchiveCheckpointCommand().toBuilder()
                .entityName("device_log").lastId(20L).lastEventTime(Instant.now().minus(2, ChronoUnit.DAYS)).build();
        ArchiveCheckpointEntity archiveCheckpointEntity1 = archiveCheckpointCommandService.saveOrUpdate(archiveCheckpointCommand);

        archiveCheckpointCommand = new ArchiveCheckpointCommand().toBuilder().id(archiveCheckpointEntity1.getId())
                .entityName("device_log").lastId(2000L).lastEventTime(Instant.now().minus(2, ChronoUnit.DAYS)).build();
        ArchiveCheckpointEntity archiveCheckpointEntity2 = archiveCheckpointCommandService.saveOrUpdate(archiveCheckpointCommand);

        assertNotEquals(archiveCheckpointEntity1.getLastId(), archiveCheckpointEntity2.getLastId());
        assertEquals(archiveCheckpointEntity1.getId(), archiveCheckpointEntity2.getId());
    }
}
