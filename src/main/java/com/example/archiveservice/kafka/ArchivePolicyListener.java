package com.example.archiveservice.kafka;


import com.example.archiveservice.core.ArchiveScheduler;
import com.example.archiveservice.db.archive.command.ArchivePolicyCommand;
import com.example.archiveservice.db.archive.service.ArchivePolicyCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArchivePolicyListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ArchivePolicyCommandService archivePolicyService;
    @Autowired
    private ArchiveScheduler dynamicScheduler;

    @KafkaListener(topics = "${spring.kafka.topics.archivePolicyUpdate}", groupId = "archive-hk-service")
    public void onPolicyUpdate(String message) {
        try {
            if(message!=null) {
                ArchivePolicyCommand cmd = objectMapper.readValue(message, ArchivePolicyCommand.class);
                archivePolicyService.saveOrUpdate(cmd);

                String cron = cmd.getRunCron(); // e.g. "0 30 2 * * *"
                dynamicScheduler.scheduleArchive(cron, cmd.getRetentionDays(), cmd.getBatchSize());
            }
        } catch (Exception e) {
            log.info("cannot save command from archive-policy-update due to "+e.getMessage());
            // TODO
        }

    }
}
