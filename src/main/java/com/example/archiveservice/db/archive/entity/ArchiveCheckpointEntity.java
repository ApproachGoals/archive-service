package com.example.archiveservice.db.archive.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Table(name = "archive_checkpoint")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ArchiveCheckpointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "entity_name")
    private String entityName;
    @Column(name = "last_event_time")
    private Instant lastEventTime;
    @Column(name = "last_id")
    private Long lastId;
}
