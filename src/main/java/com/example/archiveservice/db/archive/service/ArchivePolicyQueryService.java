package com.example.archiveservice.db.archive.service;

import com.example.archiveservice.db.archive.dto.ArchivePolicyDTO;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import com.example.archiveservice.db.archive.mapper.ArchivePolicyQueryMapper;
import com.example.archiveservice.db.archive.repository.ArchivePolicyQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivePolicyQueryService {
    @Autowired
    private ArchivePolicyQueryRepository archivePolicyQueryRepository;
    @Autowired
    private ArchivePolicyQueryMapper archivePolicyQueryMapper;
    @Transactional(transactionManager = "archiveTransactionManager")
    public Optional<ArchivePolicyDTO> findArchivePolicyByEntityName(String entityName) {
        Optional<ArchivePolicyEntity> archivePolicyEntityOptional = archivePolicyQueryRepository.findByEntityName(entityName);
        if(archivePolicyEntityOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(archivePolicyQueryMapper.entity2DTO(archivePolicyEntityOptional.get()));
        }
    }
    @Transactional(transactionManager = "archiveTransactionManager")
    public List<ArchivePolicyDTO> findAll() {
        return archivePolicyQueryMapper.entities2DTOs(archivePolicyQueryRepository.findAll());
    }
}
