package com.example.archiveservice.db.cold.entity;

import com.example.archiveservice.db.cold.enums.HistoryMessageTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "device_log_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeviceLogHistoryEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "payload")
    private String payload;
    @Column(name = "message_type")
    private HistoryMessageTypeEnum messageTypeEnum;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name = "create _at")
    private Instant createAt;
    @Column(name = "event_time")
    private Instant eventTime;
}
