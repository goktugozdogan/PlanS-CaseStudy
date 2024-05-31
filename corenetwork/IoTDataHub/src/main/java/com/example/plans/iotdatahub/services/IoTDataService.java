package com.example.plans.iotdatahub.services;

import com.example.plans.iotdatahub.exceptions.IoTDataNotFoundException;
import com.example.plans.iotdatahub.repositories.IoTDataRepository;
import com.example.plans.sharedhub.models.IoTDeviceData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IoTDataService {
    private final IoTDataRepository iotDataRepository;

    public IoTDataService(IoTDataRepository iotDataRepository) {
        this.iotDataRepository = iotDataRepository;
    }

    public IoTDeviceData findById(String id) throws IoTDataNotFoundException {
        Optional<IoTDeviceData> device = iotDataRepository.findById(id);
        if (device.isEmpty()) {
            throw new IoTDataNotFoundException("IotDevice Data with id: " + id + " not found.");
        }

        return device.get();
    }

    public List<IoTDeviceData> findByDevEUI(String devEUI) throws IoTDataNotFoundException {
        List<IoTDeviceData> iotData = iotDataRepository.findByDevEUI(devEUI);
        if (iotData.isEmpty()) {
            throw new IoTDataNotFoundException("IotDevice Data with id: " + devEUI + " not found.");
        }

        return iotData;
    }

    public IoTDeviceData save(IoTDeviceData ioTDeviceData) throws IoTDataNotFoundException {
        if (ioTDeviceData.getDevEUI() != null && iotDataRepository.existsById(ioTDeviceData.getId())) {
            throw new IoTDataNotFoundException("IotDevice Data with id: " + ioTDeviceData.getId() + " already exists.");
        }

        return iotDataRepository.save(ioTDeviceData);
    }

    // todo - update()
    public IoTDeviceData update(String id, IoTDeviceData IoTDeviceData) throws IoTDataNotFoundException {
        IoTDeviceData newIoTData = findById(id);

        return iotDataRepository.save(IoTDeviceData);
    }

    public void deleteById(String id) throws IoTDataNotFoundException {
        if (!iotDataRepository.existsById(id)) {
            throw new IoTDataNotFoundException("IotDevice Data with id: " + id + " not found.");
        }
        iotDataRepository.deleteById(id);
    }
}
