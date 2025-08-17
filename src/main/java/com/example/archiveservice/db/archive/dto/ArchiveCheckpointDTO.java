package com.example.archiveservice.db.archive.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArchiveCheckpointDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("entityName")
    private String entityName;
    @JsonProperty("lastEventTime")
    private Instant lastEventTime;
    @JsonProperty("lastId")
    private Long lastId;
}
