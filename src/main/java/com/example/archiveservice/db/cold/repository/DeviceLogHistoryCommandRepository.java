package com.example.archiveservice.db.cold.repository;

import com.example.archiveservice.db.cold.entity.DeviceLogHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceLogHistoryCommandRepository extends JpaRepository<DeviceLogHistoryEntity, Long>, JpaSpecificationExecutor<DeviceLogHistoryEntity> {
}
