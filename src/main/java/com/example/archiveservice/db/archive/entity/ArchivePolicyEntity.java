package com.example.archiveservice.db.archive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "archive_policies", uniqueConstraints = @UniqueConstraint(name = "uc_archivepolicy_entityname", columnNames = "entity_name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ArchivePolicyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "entity_name")
    private String entityName;
    @Column(name = "retention_days")
    private Integer retentionDays;
    @Column(name = "run_cron")
    private String runCron;
    @Column(name = "batch_size")
    private Integer batchSize;
    @Column(name = "enabled")
    private Boolean enabled;
}
