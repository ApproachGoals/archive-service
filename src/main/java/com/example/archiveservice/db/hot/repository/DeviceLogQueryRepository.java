package com.example.archiveservice.db.hot.repository;

import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceLogQueryRepository extends JpaRepository<DeviceLogEntity, Long>, JpaSpecificationExecutor<DeviceLogEntity> {

    Page<DeviceLogEntity> findAll(Specification<DeviceLogEntity> specification, Pageable pageable);
}
