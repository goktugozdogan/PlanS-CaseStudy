package com.example.plans.userhub.services;

import com.example.plans.sharedhub.models.request.OperationRequest;
import com.example.plans.sharedhub.utils.JsonTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    @Value("${spring.kafka.topics.devicehub.opt}")
    String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OperationRequest req) {
        try {
            String json = JsonTransformer.objectToJson(req);
            kafkaTemplate.send(topic, json);
        } catch (JsonProcessingException e) {
            log.error("Kafka json parser error {}", e.getMessage());
        }
    }
}
