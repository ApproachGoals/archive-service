package com.example.archiveservice.db.archive.mapper;

import com.example.archiveservice.db.archive.dto.ArchivePolicyDTO;
import com.example.archiveservice.db.archive.entity.ArchivePolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

import java.util.List;

@Mapper(
        componentModel = "spring",
        mappingControl = DeepClone.class
)
public interface ArchivePolicyQueryMapper {
    ArchivePolicyDTO entity2DTO(ArchivePolicyEntity archivePolicyEntity);
    List<ArchivePolicyDTO> entities2DTOs(List<ArchivePolicyEntity> archivePolicyEntities);
}
