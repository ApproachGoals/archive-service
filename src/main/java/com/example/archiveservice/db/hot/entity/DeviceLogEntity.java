package com.example.archiveservice.db.hot.entity;

import com.example.archiveservice.db.hot.enums.MessageTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "device_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeviceLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_at")
    private Instant createAt;
    @Column(name = "event_time")
    private Instant eventTime;
    @Column(name = "payload")
    private String payload;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "message_type")
    private MessageTypeEnum messageTypeEnum;
    @Column(name = "device_id")
    private Long deviceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_devicelog_device"), insertable = false, updatable = false)
    private DeviceEntity deviceEntity;

}
