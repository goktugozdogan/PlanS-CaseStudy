package com.example.plans.userhub.controllers;

import com.example.plans.sharedhub.models.request.OperationRequest;
import com.example.plans.sharedhub.models.request.IoTDataRequest;
import com.example.plans.sharedhub.models.response.BaseResponse;
import com.example.plans.sharedhub.models.response.StringResponse;
import com.example.plans.userhub.exceptions.UserNotFoundException;
import com.example.plans.userhub.models.request.User2DeviceHubRequest;
import com.example.plans.userhub.models.User;
import com.example.plans.userhub.models.request.UserRequest;
import com.example.plans.userhub.models.response.UserResponse;
import com.example.plans.userhub.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userhub")
public class UserHubController {
    private final UserService userService;

    public UserHubController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "{\"status\":\"UP\"}";
    }

    @PostMapping("/find_by_email")
    public BaseResponse findByEmail(@RequestBody UserRequest req) throws UserNotFoundException {
        log.info("Find by email {}", req);
        User user = userService.findByEmail(req.getEmail());
        return new UserResponse(req.getTraceid(), user);
    }

    @PutMapping("/update_by_email")
    public BaseResponse updateByEmail(@RequestBody User req) throws UserNotFoundException {
        log.info("Update by email {}", req);
        userService.update(req.getEmail(), req);
        return new StringResponse("", "User successfully updated.");
    }

    @DeleteMapping("/delete_by_email")
    public BaseResponse deleteByEmail(@RequestBody UserRequest req) throws UserNotFoundException {
        log.info("Delete by email {}", req);
        userService.deleteByEmail(req.getEmail());
        return new StringResponse(req.getTraceid(), "User successfully deleted.");
    }

    @PostMapping("/link_device")
    public BaseResponse linkDevice(@RequestBody User2DeviceHubRequest req) throws UserNotFoundException {
        log.info("Link device {}", req);
        return userService.linkDevice(req);
    }

    @PostMapping("/unlink_device")
    public BaseResponse unlinkDevice(@RequestBody User2DeviceHubRequest req) throws UserNotFoundException {
        log.info("Unlink device {}", req);
        return userService.unlinkDevice(req);
    }

    @PostMapping("/list_devices")
    public BaseResponse listDevices(@RequestBody User2DeviceHubRequest req) throws UserNotFoundException {
        return userService.listDevices(req);
    }

    @PostMapping("/list_device_data")
    public BaseResponse listDeviceData(@RequestBody IoTDataRequest req) {
        log.info("List device data {}", req);
        return userService.listDeviceComm(req);
    }

    @PostMapping("/list_all_iotdata_by_email")
    public BaseResponse listAllDataByEmail(@RequestBody IoTDataRequest req) {
        log.info("List all ioTData {}", req);
        return userService.listAllDataByEmail(req);
    }

    @PostMapping("/toggle_sensor")
    public BaseResponse toggleSensor(@RequestBody OperationRequest req) {
        log.info("Toggle sensor {}", req);
        return userService.toggleSensor(req);
    }
}
