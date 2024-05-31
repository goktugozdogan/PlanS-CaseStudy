package com.example.plans.iotdatahub.controllers;

import com.example.plans.iotdatahub.exceptions.IoTDataNotFoundException;
import com.example.plans.iotdatahub.services.IoTDataService;
import com.example.plans.sharedhub.models.IoTDeviceData;
import com.example.plans.sharedhub.models.request.IoTDataRequest;
import com.example.plans.sharedhub.models.response.BaseResponse;
import com.example.plans.sharedhub.models.response.IoTDataResponse;
import com.example.plans.sharedhub.models.response.StringResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/iotdatahub")
public class IotDataController {
    private final IoTDataService iotDataService;

    @Value("${spring.kafka.topics.groundstation.iotdata}")
    String topic;

    public IotDataController(IoTDataService iotDataService) {
        this.iotDataService = iotDataService;
    }

    @PostMapping("/find_by_id")
    public BaseResponse findById(@RequestBody IoTDataRequest req) throws IoTDataNotFoundException {
        log.info("Find device by id req: {}", req);

        List<IoTDeviceData> iotData = new ArrayList<>();
        for (String devEUI : req.getIds()) {
            IoTDeviceData ioTDeviceData = iotDataService.findById(devEUI);
            iotData.add(ioTDeviceData);
        }

        IoTDataResponse res = new IoTDataResponse(req.getTraceid(), iotData);
        log.info("Find device by id res: {}", res);
        return res;
    }

    @PostMapping("/find_by_eui")
    public BaseResponse findByDevEUI(@RequestBody IoTDataRequest req) throws IoTDataNotFoundException {
        log.info("Find device by devEUI req: {}", req);

        List<IoTDeviceData> iotData = new ArrayList<>();
        for (String devEUI : req.getDevEUIs()) {
            List<IoTDeviceData> ioTDeviceData = iotDataService.findByDevEUI(devEUI);
            iotData.addAll(ioTDeviceData);
        }

        IoTDataResponse res = new IoTDataResponse(req.getTraceid(), iotData);
        log.info("Find device by devEUI res: {}", res);
        return res;
    }

    @PostMapping("/create")
    public BaseResponse createDevice(@RequestBody IoTDataRequest req) throws IoTDataNotFoundException {
        log.info("Create device req: {}", req);
        for (IoTDeviceData ioTDeviceData : req.getIoTDeviceData()) {
            iotDataService.save(ioTDeviceData);
        }
        StringResponse res = new StringResponse(req.getTraceid(), "Create operation successful.");
        log.info("Create device res: {}", res);
        return res;
    }

    @PutMapping("/update")
    public BaseResponse updateDevice(@RequestBody IoTDataRequest req) throws IoTDataNotFoundException {
        log.info("Update device req: {}", req);

        for (IoTDeviceData ioTDeviceData : req.getIoTDeviceData()) {
            iotDataService.update(ioTDeviceData.getEui(), ioTDeviceData);
        }

        StringResponse res = new StringResponse(req.getTraceid(), "Update operation successful.");
        log.info("Update device res: {}", res);
        return res;
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteById(@RequestBody IoTDataRequest req) throws IoTDataNotFoundException {
        log.info("Delete device req: {}", req);

        for (String id : req.getIds()) {
            iotDataService.deleteById(id);
        }
        StringResponse res = new StringResponse(req.getTraceid(), "Delete operation successful.");
        log.info("Delete device res: {}", res);
        return res;
    }
}
