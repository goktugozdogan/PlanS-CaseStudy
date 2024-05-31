package com.example.plans.iotdatahub.services;

import com.example.plans.iotdatahub.exceptions.IoTDataNotFoundException;
import com.example.plans.iotdatahub.repositories.OperationsRepository;
import com.example.plans.sharedhub.models.OperationData;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationService {
    private final OperationsRepository operationsRepository;

    public OperationService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public OperationData findById(String id) throws IoTDataNotFoundException {
        Optional<OperationData> device = operationsRepository.findById(id);
        if (device.isEmpty()) {
            throw new IoTDataNotFoundException("Operation Data with id: " + id + " not found.");
        }

        return device.get();
    }

    public OperationData save(OperationData operationData) throws IoTDataNotFoundException {
        if (operationData.getId() != null && operationsRepository.existsById(operationData.getId())) {
            throw new IoTDataNotFoundException("IotDevice Data with id: " + operationData.getId() + " already exists.");
        }

        return operationsRepository.save(operationData);
    }
}
