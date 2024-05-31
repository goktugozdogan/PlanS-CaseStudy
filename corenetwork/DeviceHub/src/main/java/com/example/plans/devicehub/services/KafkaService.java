package com.example.plans.devicehub.services;

import com.example.plans.devicehub.exceptions.DeviceNotFoundException;
import com.example.plans.sharedhub.models.IoTDevice;
import com.example.plans.sharedhub.models.OperationData;
import com.example.plans.sharedhub.models.OperationInfo;
import com.example.plans.sharedhub.models.request.OperationRequest;
import com.example.plans.sharedhub.utils.JsonTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;

@Slf4j
@Service
public class KafkaService {
    @Value("${spring.kafka.topics.groundstation.operations}")
    String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final DeviceService deviceService;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, DeviceService deviceService) {
        this.kafkaTemplate = kafkaTemplate;
        this.deviceService = deviceService;
    }

    @KafkaListener(topics = "${spring.kafka.topics.devicehub.opt}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOperationsFromUserHub(String message) throws JsonProcessingException, DeviceNotFoundException {
        log.info("Kafka message received msg: {}", message);

        OperationRequest req = JsonTransformer.jsonToObject(message, OperationRequest.class);

        IoTDevice device = deviceService.findByDevEUI(req.getDevEUI());

        IoTDevice tempDevice = new IoTDevice();
        tempDevice.setSensors(req.getSensors());
        tempDevice = deviceService.replaceInfo(device, tempDevice);

        OperationInfo operationInfo = new OperationInfo();
        operationInfo.setInitiatorEmail(req.getEmail());
        operationInfo.setInitiateTime(Instant.now().toString());

        OperationData operationData = new OperationData();
        operationData.setTraceid(req.getTraceid());
        operationData.setIoTDevice(Collections.singletonList(tempDevice));
        operationData.setOperationInfo(operationInfo);

        try {
            send(operationData);
            log.info("Successfully sent operation data to {}, data: {}", topic, operationData);
        } catch (JsonProcessingException e) {
            log.error("Kafka send message failed. {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "${spring.kafka.topics.devicehub.update}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeUpdatesFromDataHub(String message) throws JsonProcessingException, DeviceNotFoundException {
        log.info("Kafka message received msg: {}", message);
        IoTDevice req = JsonTransformer.jsonToObject(message, IoTDevice.class);

        deviceService.update(req.getDevEUI(), req);
        log.info("Successfully sent update data {}", req);
    }

    public void send(OperationData optData) throws JsonProcessingException {
        String json = JsonTransformer.objectToJson(optData);
        kafkaTemplate.send(topic, json);
    }
}
