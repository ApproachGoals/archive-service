package com.example.archiveservice.db.archive.mapper;

import com.example.archiveservice.db.archive.command.ArchiveCheckpointCommand;
import com.example.archiveservice.db.archive.entity.ArchiveCheckpointEntity;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

@Mapper(
        componentModel = "spring",
        mappingControl = DeepClone.class
)
public interface ArchiveCheckpointCommandStructMapper {
    ArchiveCheckpointEntity command2Entity(ArchiveCheckpointCommand archiveCheckpointCommand);
}
