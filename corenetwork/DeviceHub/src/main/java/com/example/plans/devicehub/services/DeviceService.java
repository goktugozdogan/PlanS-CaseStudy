package com.example.plans.devicehub.services;

import com.example.plans.devicehub.exceptions.DeviceNotFoundException;
import com.example.plans.sharedhub.models.IoTDevice;
import com.example.plans.devicehub.repositories.DeviceRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepo deviceRepo;

    public DeviceService(DeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    public List<IoTDevice> findAll() {
        return deviceRepo.findAll();
    }

    public IoTDevice findByDevEUI(String devEUI) throws DeviceNotFoundException {
        Optional<IoTDevice> device = deviceRepo.findById(devEUI);
        if (device.isEmpty()) {
            throw new DeviceNotFoundException("IotDevice with DevEUI " + devEUI + " not found.");
        }

        return device.get();
    }

    public IoTDevice save(IoTDevice ioTDevice) throws DeviceNotFoundException {
        if (ioTDevice.getDevEUI() != null && deviceRepo.existsById(ioTDevice.getDevEUI())) {
            throw new DeviceNotFoundException("IotDevice with DevEUI " + ioTDevice.getDevEUI() + " already exists.");
        }
        return deviceRepo.save(ioTDevice);
    }

    public IoTDevice update(String devEUI, IoTDevice ioTDevice) throws DeviceNotFoundException {
        IoTDevice newDevice = findByDevEUI(devEUI);

        return deviceRepo.save(replaceInfo(newDevice, ioTDevice));
    }

    public void deleteByDevEUI(String devEUI) throws DeviceNotFoundException {
        if (!deviceRepo.existsById(devEUI)) {
            throw new DeviceNotFoundException("IotDevice with DevEUI " + devEUI + " not found.");
        }
        deviceRepo.deleteById(devEUI);
    }


    public IoTDevice replaceInfo (IoTDevice origin, IoTDevice replace) throws DeviceNotFoundException {
        if (replace.getOwnership() != null) {
            origin.setOwnership(replace.getOwnership());
        }
        if (replace.getLocation() != null) {
            origin.setLocation(replace.getLocation());
        }
        if (replace.getOperationalDates() != null) {
            origin.setOperationalDates(replace.getOperationalDates());
        }
        if (replace.getSensors() != null) {
            origin.setSensors(replace.getSensors());
        }
        if (replace.getMetadata() != null) {
            origin.setMetadata(replace.getMetadata());
        }
        if (replace.getSecurity() != null) {
            origin.setSecurity(replace.getSecurity());
        }
        if (replace.getConnectivity() != null) {
            origin.setConnectivity(replace.getConnectivity());
        }
        if (replace.getPowerSource() != null) {
            origin.setPowerSource(replace.getPowerSource());
        }
        if (replace.getFirmwareVersion() != null) {
            origin.setFirmwareVersion(replace.getFirmwareVersion());
        }

        return origin;
    }
}
