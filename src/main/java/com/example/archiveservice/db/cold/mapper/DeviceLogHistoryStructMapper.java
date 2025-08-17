package com.example.archiveservice.db.cold.mapper;

import com.example.archiveservice.db.cold.command.DeviceLogHistoryCommand;
import com.example.archiveservice.db.cold.entity.DeviceLogHistoryEntity;
import com.example.archiveservice.db.cold.enums.HistoryMessageTypeEnum;
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
public interface DeviceLogHistoryStructMapper {
    @Mapping(source = "uid", target = "uuid")
    @Mapping(source = "messageType", target = "messageTypeEnum" ,qualifiedByName = "string2HistoryMessageTypeEnum")
    DeviceLogHistoryEntity command2Entity(DeviceLogHistoryCommand deviceLogHistoryCommand);

    List<DeviceLogHistoryEntity> commands2Entities(List<DeviceLogHistoryCommand> deviceLogHistoryCommands);

    @Named("string2HistoryMessageTypeEnum")
    default HistoryMessageTypeEnum string2HistoryMessageTypeEnum(String type) {
        return HistoryMessageTypeEnum.valueOf(type);
    }
}
