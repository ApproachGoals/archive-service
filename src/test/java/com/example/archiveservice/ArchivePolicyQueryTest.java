package com.example.archiveservice;

import com.example.archiveservice.db.archive.command.ArchivePolicyCommand;
import com.example.archiveservice.db.archive.dto.ArchivePolicyDTO;
import com.example.archiveservice.db.archive.mapper.ArchivePolicyQueryMapper;
import com.example.archiveservice.db.archive.repository.ArchivePolicyQueryRepository;
import com.example.archiveservice.db.archive.service.ArchivePolicyCommandService;
import com.example.archiveservice.db.archive.service.ArchivePolicyQueryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArchivePolicyQueryTest {
    @Autowired
    private ArchivePolicyQueryService archivePolicyQueryService;
    @Autowired
    private ArchivePolicyQueryMapper archivePolicyQueryMapper;
    @Autowired
    private ArchivePolicyCommandService archivePolicyCommandService;
    @BeforeEach
    public void init() {
        ArchivePolicyCommand archivePolicyCommand = new ArchivePolicyCommand().toBuilder().batchSize(2000).runCron("0 0 2 * * *").enabled(true).retentionDays(7).entityName("device_log").build();
        archivePolicyCommandService.saveOrUpdate(archivePolicyCommand);
    }
    @AfterEach
    public void clean() {
        List<ArchivePolicyDTO> archivePolicyDTOS = archivePolicyQueryService.findAll();
        for (ArchivePolicyDTO archivePolicyDTO : archivePolicyDTOS) {
            archivePolicyCommandService.deleteById(new ArchivePolicyCommand().toBuilder().id(archivePolicyDTO.getId()).build());
        }
    }

    @Test
    public void findAllTest() throws Exception {
        List<ArchivePolicyDTO> archivePolicyDTOS = archivePolicyQueryService.findAll();
        assertNotNull(archivePolicyDTOS);
        assertEquals(1, archivePolicyDTOS.size());
    }

    @Test
    public void findByEntityNameTest() throws Exception {
        Optional<ArchivePolicyDTO> archivePolicyDTO = archivePolicyQueryService.findArchivePolicyByEntityName("device_log");
        assertTrue(archivePolicyDTO.isPresent());
    }
}
