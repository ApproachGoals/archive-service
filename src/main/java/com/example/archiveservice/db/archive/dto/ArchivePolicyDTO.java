package com.example.archiveservice.db.archive.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArchivePolicyDTO {
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
