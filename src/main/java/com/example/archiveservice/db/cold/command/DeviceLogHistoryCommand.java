package com.example.archiveservice.db.cold.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeviceLogHistoryCommand {
    @JsonProperty(required = true, value = "id")
    private Long id;
    @JsonProperty(required = true, value = "uid")
    private String uid;
    @JsonProperty(required = true, value = "payload")
    private String payload;
    @JsonProperty(required = true, value = "createAt")
    private Instant createAt;
    @JsonProperty(required = true, value = "eventTime")
    private Instant eventTime;
    @JsonProperty(required = true, value = "messageType")
    private String messageType;
    @JsonProperty(required = true, value = "deviceId")
    private Long deviceId;
}
