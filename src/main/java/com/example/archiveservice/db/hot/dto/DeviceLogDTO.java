package com.example.archiveservice.db.hot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeviceLogDTO {
    @JsonProperty(value = "id", required = true)
    private Long id;
    @JsonProperty(value = "createAt", required = true)
    private Instant createAt;
    @JsonProperty(value = "eventTime", required = true)
    private Instant eventTime;
    @JsonProperty(value = "payload", required = true)
    private String payload;
    @JsonProperty(value = "messageType", required = true)
    private String messageType;
    @JsonProperty(value = "uid", required = true)
    private String uid;
    @JsonProperty(value = "deviceId")
    private Long deviceId;
}
