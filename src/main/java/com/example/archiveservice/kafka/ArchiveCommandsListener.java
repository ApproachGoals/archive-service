package com.example.archiveservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ArchiveCommandsListener {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${spring.kafka.topics.archive.commands.policy}", groupId = "data-archive")
    public void readArchivePolicyData(String message) throws Exception {
        JsonNode node = new ObjectMapper().readTree(message);
        String commandType = node.get("commandType").asText();
        switch (commandType) {
            case "getAll":
                break;
            default:
        }

        // TODO parse commands
        // TODO Kafka send

        // TODO delete, for junit test
        lastPolicyMessage = commandType;
        policyLatch.countDown();

    }

    @KafkaListener(topics = "${spring.kafka.topics.archive.commands.checkpoint}", groupId = "data-archive")
    public void readCheckpointData(String message) throws Exception {
        JsonNode node = new ObjectMapper().readTree(message);
        String commandType = node.get("commandType").asText();
        // TODO parse commands
        // TODO Kafka send

        // TODO delete, for junit test
        lastCheckpointMessage = commandType;
        checkpointLatch.countDown();
    }

    // for junit test
    private CountDownLatch policyLatch = new CountDownLatch(1);
    private CountDownLatch checkpointLatch = new CountDownLatch(1);
    private String lastPolicyMessage;
    private String lastCheckpointMessage;
    public CountDownLatch getPolicyLatch() {
        return policyLatch;
    }

    public CountDownLatch getCheckpointLatch() {
        return checkpointLatch;
    }

    public String getLastPolicyMessage() {
        return lastPolicyMessage;
    }

    public String getLastCheckpointMessage() {
        return lastCheckpointMessage;
    }

    // reset
    public void resetLatches() {
        policyLatch = new CountDownLatch(1);
        checkpointLatch = new CountDownLatch(1);
    }
}
