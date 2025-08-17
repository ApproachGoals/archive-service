package com.example.archiveservice.db.hot.entity;

import com.example.archiveservice.db.hot.dto.DeviceLogDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "deleted")
    private Boolean isDeleted;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "deviceEntity")
    private List<DeviceLogEntity> deviceLogEntities = new ArrayList<>();
}
