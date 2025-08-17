package com.example.archiveservice;

import com.example.archiveservice.db.archive.command.ArchivePolicyCommand;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import com.example.archiveservice.db.archive.service.ArchivePolicyCommandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArchivePolicyCommandTest {
    @Autowired
    private ArchivePolicyCommandService archivePolicyCommandService;

    @AfterEach
    public void clean() throws Exception {
        List<ArchivePolicyEntity> archivePolicyEntities = archivePolicyCommandService.findAll();
        for (ArchivePolicyEntity archivePolicyEntity : archivePolicyEntities) {
            archivePolicyCommandService.deleteById(new ArchivePolicyCommand().toBuilder().id(archivePolicyEntity.getId()).build());
        }
    }
    @Test
    public void insertNewArchivePolicy() throws Exception {
        ArchivePolicyCommand archivePolicyCommand = new ArchivePolicyCommand().toBuilder().batchSize(2000).runCron("0 0 2 * * *").enabled(true).retentionDays(7).entityName("device_log").build();
        ArchivePolicyEntity archivePolicyEntity = archivePolicyCommandService.saveOrUpdate(archivePolicyCommand);
        assertNotNull(archivePolicyEntity);
        assertNotNull(archivePolicyEntity.getId());
    }

    @Test
    public void delete() throws Exception {
        ArchivePolicyCommand archivePolicyCommand = new ArchivePolicyCommand().toBuilder().batchSize(2000).runCron("0 0 2 * * *").enabled(true).retentionDays(7).entityName("device_log").build();
        ArchivePolicyEntity archivePolicyEntity = archivePolicyCommandService.saveOrUpdate(archivePolicyCommand);

        ArchivePolicyCommand archivePolicyCommand1 = new ArchivePolicyCommand().toBuilder().id(archivePolicyEntity.getId()).build();
        archivePolicyCommandService.deleteById(archivePolicyCommand1);

        List<ArchivePolicyEntity> archivePolicyEntities = archivePolicyCommandService.findAll();
        assertNotNull(archivePolicyEntities);
        assertEquals(0, archivePolicyEntities.size());
    }
}
