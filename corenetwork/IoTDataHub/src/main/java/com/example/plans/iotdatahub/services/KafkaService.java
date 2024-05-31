package com.example.plans.iotdatahub.services;

import com.example.plans.iotdatahub.exceptions.IoTDataNotFoundException;
import com.example.plans.sharedhub.models.IoTDevice;
import com.example.plans.sharedhub.models.IoTDeviceData;
import com.example.plans.sharedhub.models.OperationData;
import com.example.plans.sharedhub.utils.JsonTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {
    @Value("${spring.kafka.topics.devicehub.update}")
    String sendTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final IoTDataService iotDataService;

    private final OperationService operationService;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, IoTDataService iotDataService, OperationService operationService) {
        this.kafkaTemplate = kafkaTemplate;
        this.iotDataService = iotDataService;
        this.operationService = operationService;
    }

    @KafkaListener(topics = "${spring.kafka.topics.groundstation.iotdata}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeGroundStationForIoTData(String message) throws JsonProcessingException, IoTDataNotFoundException {
        log.info("Received Ground Station message: {}", message);

        IoTDeviceData ioTDeviceData = JsonTransformer.jsonToObject(message, IoTDeviceData.class);

        iotDataService.save(ioTDeviceData);

        log.info("IoTData saved: {}", ioTDeviceData);
    }

    @KafkaListener(topics = "${spring.kafka.topics.groundstation.operations}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeGroundStationForOperations(String message) throws JsonProcessingException, IoTDataNotFoundException {
        log.info("Received Ground Station message: {}", message);

        OperationData operationData = JsonTransformer.jsonToObject(message, OperationData.class);

        operationService.save(operationData);
        log.info("Operation saved: {}", operationData);

        // send update message to devicehub
        for (IoTDevice ioTDevice : operationData.getIoTDevice()) {
            send(ioTDevice);
        }

        log.info("All IoTDevice relted messages sent ");
    }

    public void send(IoTDevice ioTDevice) throws JsonProcessingException {
        String json = JsonTransformer.objectToJson(ioTDevice);
        kafkaTemplate.send(sendTopic, json);
    }
}
