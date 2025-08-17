package com.example.archiveservice.db.hot.mapper;

import com.example.archiveservice.db.hot.dto.DeviceLogDTO;
import com.example.archiveservice.db.hot.entity.DeviceLogEntity;
import com.example.archiveservice.db.hot.enums.MessageTypeEnum;
import com.example.archiveservice.mapper.GeneralStructMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.control.DeepClone;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {GeneralStructMapper.class},
        mappingControl = DeepClone.class
)
public interface DeviceLogStructMapper {
    @Mapping(source = "messageTypeEnum", target = "messageType", qualifiedByName = "messageTypeEnum2String")
    @Mapping(source = "uuid", target = "uid")
    DeviceLogDTO entity2DTO(DeviceLogEntity deviceLogEntity);
    List<DeviceLogDTO> entities2DTOs(List<DeviceLogEntity> deviceLogEntities);
    @Named("messageTypeEnum2String")
    default String messageTypeEnum2String(MessageTypeEnum type) {
        return type.name();
    }
}
