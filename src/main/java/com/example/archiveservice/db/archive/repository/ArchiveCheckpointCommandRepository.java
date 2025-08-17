package com.example.archiveservice.db.archive.repository;

import com.example.archiveservice.db.archive.entity.ArchiveCheckpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveCheckpointCommandRepository extends JpaRepository<ArchiveCheckpointEntity, Long> {
}
