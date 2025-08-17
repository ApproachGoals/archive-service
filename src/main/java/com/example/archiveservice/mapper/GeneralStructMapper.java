package com.example.archiveservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        mappingControl = DeepClone.class
)
public interface GeneralStructMapper {
    default UUID mapStringToUUID(String uuid) {
        return uuid == null ? null : UUID.fromString(uuid);
    }
    default String mapUUIDToString(UUID uuid) {
        return uuid == null? null : uuid.toString();
    }
}
