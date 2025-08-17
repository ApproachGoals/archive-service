package com.example.archiveservice.core;

import com.example.archiveservice.db.archive.dto.ArchivePolicyDTO;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import com.example.archiveservice.db.archive.service.ArchivePolicyQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class ArchivePolicyInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private ArchivePolicyQueryService policyQueryService;
    @Autowired
    private ArchiveScheduler scheduler;
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("Initialize Schedule.............");
        try {
            initOnStartup();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return;
    }
    //@EventListener(ApplicationReadyEvent.class)
    public void initOnStartup() {
        List<ArchivePolicyDTO> policies = policyQueryService.findAll();
        for (ArchivePolicyDTO policyDTO : policies) {
            scheduler.scheduleArchive(policyDTO.getRunCron(),
                    policyDTO.getRetentionDays(),
                    policyDTO.getBatchSize());
        }
    }

}
