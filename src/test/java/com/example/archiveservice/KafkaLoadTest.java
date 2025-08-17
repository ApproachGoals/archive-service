package com.example.archiveservice;

import com.example.archiveservice.kafka.ArchiveCommandsListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
        topics = { "archive-commands-policy", "archive-commands-checkpoint" },
        brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaLoadTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @Autowired
    private ArchiveCommandsListener archiveCommandsListener;
    @Value("${spring.kafka.topics.archive.commands.policy}")
    private String policyTopic;

    @Value("${spring.kafka.topics.archive.commands.checkpoint}")
    private String checkpointTopic;

    @BeforeEach
    void setup() {
        archiveCommandsListener.resetLatches();
    }

    @Test
    void testPolicyCommandConsumed() throws Exception {
        String json = "{\"commandType\":\"getAll\"}";

        kafkaTemplate.send(policyTopic, json);

        boolean consumed = archiveCommandsListener.getPolicyLatch().await(5, TimeUnit.SECONDS);
        assertTrue(consumed, "Policy listener 没有消费消息！");
        assertEquals("getAll", archiveCommandsListener.getLastPolicyMessage());
    }

    @Test
    void testCheckpointCommandConsumed() throws Exception {
        String json = "{\"commandType\":\"sync\"}";

        kafkaTemplate.send(checkpointTopic, json);

        boolean consumed = archiveCommandsListener.getCheckpointLatch().await(5, TimeUnit.SECONDS);
        assertTrue(consumed, "Checkpoint listener 没有消费消息！");
        assertEquals("sync", archiveCommandsListener.getLastCheckpointMessage());
    }
}
