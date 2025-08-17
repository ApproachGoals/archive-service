package com.example.archiveservice.db.archive.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchivePolicyCommand {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty("entityName")
    private String entityName;
    @JsonProperty("retentionDays")
    private Integer retentionDays;
    @JsonProperty("runCron")
    private String runCron;
    @JsonProperty("batchSize")
    private Integer batchSize;
    @JsonProperty("enabled")
    private Boolean enabled;
}
