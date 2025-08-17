package com.example.archiveservice.db.archive.mapper;

import com.example.archiveservice.db.archive.command.ArchivePolicyCommand;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

@Mapper(
        componentModel = "spring",
        mappingControl = DeepClone.class
)
public interface ArchivePolicyCommandStructMapper {
    ArchivePolicyEntity command2Entity(ArchivePolicyCommand archivePolicyCommand);
}
