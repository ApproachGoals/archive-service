package com.example.archiveservice.db.hot.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeviceLogCommand {
    @JsonProperty("id")
    private Long id;
}
