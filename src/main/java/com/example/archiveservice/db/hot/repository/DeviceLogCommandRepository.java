package com.example.archiveservice.db.hot.repository;

import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceLogCommandRepository extends JpaRepository<DeviceLogEntity, Long> {
    @Modifying
    @Query(value = "DELETE FROM DeviceLogEntity d WHERE d.id IN :ids")
    void deleteByIds(@Param("ids") List<Long> ids);
}
