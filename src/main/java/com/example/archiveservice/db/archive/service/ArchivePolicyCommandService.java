package com.example.archiveservice.db.archive.service;

import com.example.archiveservice.db.archive.command.ArchivePolicyCommand;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import com.example.archiveservice.db.archive.mapper.ArchivePolicyCommandStructMapper;
import com.example.archiveservice.db.archive.repository.ArchivePolicyCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArchivePolicyCommandService {

    @Autowired
    private ArchivePolicyCommandRepository archivePolicyRepository;
    @Autowired
    private ArchivePolicyCommandStructMapper archivePolicyCommandStructMapper;

    @Transactional(transactionManager = "archiveTransactionManager")
    public ArchivePolicyEntity saveOrUpdate(ArchivePolicyCommand archivePolicyCommand) {
        return archivePolicyRepository.saveAndFlush(archivePolicyCommandStructMapper.command2Entity(archivePolicyCommand));
    }
    @Transactional(transactionManager = "archiveTransactionManager")
    public void deleteById(ArchivePolicyCommand archivePolicyCommand) {
        if(archivePolicyCommand!=null && archivePolicyCommand.getId()!=null) {
            archivePolicyRepository.deleteById(archivePolicyCommand.getId());
        }
    }
    @Transactional(transactionManager = "archiveTransactionManager")
    public List<ArchivePolicyEntity> findAll() {
        return archivePolicyRepository.findAll();
    }
}
