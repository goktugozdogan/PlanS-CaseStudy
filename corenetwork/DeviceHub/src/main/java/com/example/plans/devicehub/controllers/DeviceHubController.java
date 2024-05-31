package com.example.plans.devicehub.controllers;

import com.example.plans.devicehub.exceptions.DeviceNotFoundException;
import com.example.plans.sharedhub.models.response.DeviceResponse;
import com.example.plans.sharedhub.models.request.DeviceRequest;
import com.example.plans.sharedhub.models.IoTDevice;
import com.example.plans.devicehub.services.DeviceService;
import com.example.plans.sharedhub.models.response.BaseResponse;
import com.example.plans.sharedhub.models.response.StringResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/devicehub")
public class DeviceHubController {
    private final DeviceService deviceService;

    public DeviceHubController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "{\"status\":\"UP\"}";
    }

    @PostMapping("/find")
    public BaseResponse findById(@RequestBody DeviceRequest req) throws DeviceNotFoundException {
        log.info("Find device by devEUI req: {}", req);

        List<IoTDevice> devices = new ArrayList<>();
        for (String deveui : req.getDevEUIs()) {
            IoTDevice ioTDevice = deviceService.findByDevEUI(deveui);
            devices.add(ioTDevice);
        }

        DeviceResponse res = new DeviceResponse(req.getTraceid(), devices);
        log.info("Find device by devEUI res: {}", res);
        return res;
    }

    @PostMapping("/create")
    public BaseResponse createDevice(@RequestBody DeviceRequest req) throws DeviceNotFoundException {
        log.info("Create device req: {}", req);

        for (IoTDevice device : req.getIoTDevices()) {
            deviceService.save(device);
        }

        StringResponse res = new StringResponse(req.getTraceid(), "Create operation successful.");
        log.info("Create device successful res: {}", res);
        return res;
    }

    @PostMapping("/update")
    public BaseResponse updateDevice(@RequestBody DeviceRequest req) throws DeviceNotFoundException {
        log.info("Update device req: {}", req);

        for (IoTDevice device : req.getIoTDevices()) {
            deviceService.update(device.getDevEUI(), device);
        }

        StringResponse res = new StringResponse(req.getTraceid(), "Update operation successful.");
        log.info("Update device successful res: {}", res);
        return res;
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteById(@RequestBody DeviceRequest req) throws DeviceNotFoundException {
        for (String deveui : req.getDevEUIs()) {
            deviceService.deleteByDevEUI(deveui);
        }
        StringResponse res = new StringResponse(req.getTraceid(), "Delete operation successful.");
        log.info("Delete device successful res: {}", res);
        return res;
    }
}
