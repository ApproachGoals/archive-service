package com.example.archiveservice.db.archive.repository;

import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchivePolicyQueryRepository extends JpaRepository<ArchivePolicyEntity, Long>, JpaSpecificationExecutor<ArchivePolicyEntity> {
    @Query(value = "SELECT ape FROM ArchivePolicyEntity ape WHERE ape.entityName = :entityName")
    Optional<ArchivePolicyEntity> findByEntityName(@Param("entityName") String entityName);
}
