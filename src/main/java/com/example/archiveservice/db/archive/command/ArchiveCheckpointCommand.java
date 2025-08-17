package com.example.archiveservice.db.archive.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchiveCheckpointCommand {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("entityName")
    private String entityName;
    @JsonProperty("lastEventTime")
    private Instant lastEventTime;
    @JsonProperty("lastId")
    private Long lastId;
}
