package com.example.archiveservice.mapper;

import java.util.UUID;

public class GeneralStructMapperTools {
    public static UUID mapStringToUUID(String uuid) {
        return uuid == null ? null : UUID.fromString(uuid);
    }
    public static String mapUUIDToString(UUID uuid) {
        return uuid == null? null : uuid.toString();
    }
}
