package com.example.archiveservice.db.archive.mapper;

import com.example.archiveservice.db.archive.dto.ArchiveCheckpointDTO;
import com.example.archiveservice.db.archive.entity.ArchiveCheckpointEntity;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

import java.util.List;

@Mapper(
        componentModel = "spring",
        mappingControl = DeepClone.class
)
public interface ArchiveCheckpointQueryStructMapper {
    ArchiveCheckpointDTO entity2DTO(ArchiveCheckpointEntity archiveCheckpointEntity);
    List<ArchiveCheckpointDTO> entities2DTOs(List<ArchivePolicyEntity> archivePolicyEntities);
}
