package com.example.archiveservice.db.archive.service;

import com.example.archiveservice.db.archive.command.ArchiveCheckpointCommand;
import com.example.archiveservice.db.archive.entity.ArchiveCheckpointEntity;
import com.example.archiveservice.db.archive.mapper.ArchiveCheckpointCommandStructMapper;
import com.example.archiveservice.db.archive.repository.ArchiveCheckpointCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArchiveCheckpointCommandService {
    @Autowired
    private ArchiveCheckpointCommandRepository archiveCheckpointCommandRepository;
    @Autowired
    private ArchiveCheckpointCommandStructMapper archiveCheckpointCommandStructMapper;
    @Transactional(transactionManager = "archiveTransactionManager")
    public ArchiveCheckpointEntity saveOrUpdate(ArchiveCheckpointCommand archiveCheckpointCommand) {
        return archiveCheckpointCommandRepository.saveAndFlush(archiveCheckpointCommandStructMapper.command2Entity(archiveCheckpointCommand));
    }
    @Transactional(transactionManager = "archiveTransactionManager")
    public void deleteById(ArchiveCheckpointCommand archiveCheckpointCommand) {
        if(archiveCheckpointCommand!=null && archiveCheckpointCommand.getId()!=null) {
            archiveCheckpointCommandRepository.deleteById(archiveCheckpointCommand.getId());
        }
    }
    @Transactional(transactionManager = "archiveTransactionManager")
    public List<ArchiveCheckpointEntity> findAll() {
        return archiveCheckpointCommandRepository.findAll();
    }
}
