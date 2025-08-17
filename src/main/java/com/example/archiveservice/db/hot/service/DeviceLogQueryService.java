package com.example.archiveservice.db.hot.service;

import com.example.archiveservice.db.hot.dto.DeviceLogDTO;
import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import com.example.archiveservice.db.hot.mapper.DeviceLogStructMapper;
import com.example.archiveservice.db.hot.repository.DeviceLogQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceLogQueryService {
    @Autowired
    private DeviceLogQueryRepository deviceLogQueryRepository;
    @Autowired
    private DeviceLogStructMapper deviceLogStructMapper;
    private Specification<DeviceLogEntity> olderThan(Instant threshold) {
        return (root, query, cb) -> cb.lessThan(root.get("eventTime"), threshold);

    }
    @Transactional(transactionManager = "hotTransactionManager")
    public List<DeviceLogDTO> findAll(int retentionDays, int batchSize) {
        Instant threshold = Instant.now().minus(Duration.ofDays(retentionDays));
        Pageable pageable = PageRequest.of(0, batchSize);

        Page<DeviceLogEntity> deviceLogEntities = deviceLogQueryRepository.findAll(olderThan(threshold), pageable);
        if(!deviceLogEntities.isEmpty()) {
            return deviceLogEntities.stream()
                    .map(deviceLogStructMapper::entity2DTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
