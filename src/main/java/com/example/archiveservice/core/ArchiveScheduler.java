package com.example.archiveservice.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class ArchiveScheduler {
    @Autowired
    private TaskScheduler scheduler;
    @Autowired
    private ArchiveService archiveService;
    private ScheduledFuture<?> future;

    public synchronized void scheduleArchive(String cronExpr, int retentionDays, int batchSize) {
        if (future != null) {
            future.cancel(false);
        }
        future = scheduler.schedule(
                () -> archiveService.archiveDevices(retentionDays, batchSize),
                new CronTrigger(cronExpr)
        );
    }
}
/*
@Component
public class ArchiveScheduler {
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private ArchivePolicyRepository policyRepository;
    @Scheduled(cron = "0 0 2 * * *") // default 2:00
    public void scheduledArchive() {
        Optional<ArchivePolicyEntity> policy = policyRepository.findByEntityName("device_log");
        if (policy.isPresent() && policy.get().getEnabled()) {
            archiveService.archiveDevices(
                    policy.get().getRetentionDays(),
                    policy.get().getBatchSize());
        }
    }
}
*/