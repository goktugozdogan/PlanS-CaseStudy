package com.example.plans.userhub.services;

import com.example.plans.sharedhub.models.IoTDevice;
import com.example.plans.sharedhub.models.request.OperationRequest;
import com.example.plans.sharedhub.models.request.DeviceRequest;
import com.example.plans.sharedhub.models.request.IoTDataRequest;
import com.example.plans.sharedhub.models.response.*;
import com.example.plans.userhub.exceptions.UserNotFoundException;
import com.example.plans.userhub.models.request.User2DeviceHubRequest;
import com.example.plans.userhub.models.User;
import com.example.plans.userhub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final PasswordEncoder passwordEncoder;

    private final KafkaService kafkaService;

    @Value("${corenetwork.devicehub.url}")
    private String URL_DEVICEHUB;

    @Value("${corenetwork.iotdatahub.url}")
    private String URL_DATAHUB;

    public UserService(UserRepository userRepository, RestTemplate restTemplate, PasswordEncoder passwordEncoder, KafkaService kafkaService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
        this.kafkaService = kafkaService;
    }

    public User findByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username: " + username + " not found.");
        }

        return user;
    }

    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email: " + email + " not found.");
        }

        return user;
    }

    public User update(String email, User user) throws UserNotFoundException {
        User newUser = findByEmail(email);

        if (user.getUsername() != null) {
            newUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getFirstName() != null) {
            newUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            newUser.setLastName(user.getLastName());
        }
        if (user.getPhone() != null) {
            newUser.setPhone(user.getPhone());
        }
        if (user.getEnabled() != null) {
            newUser.setEnabled(user.getEnabled());
        }
        if (user.getAccountLocked() != null) {
            newUser.setAccountLocked(user.getAccountLocked());
        }
        if (user.getRoles() != null) {
            newUser.setRoles(user.getRoles());
        }
        if (user.getDevices() != null) {
            newUser.setDevices(user.getDevices());
        }

        return userRepository.save(newUser);
    }

    public void deleteByEmail(String email) throws UserNotFoundException {
        User user = findByEmail(email);

        userRepository.deleteById(user.getEmail());
    }

    public StringResponse linkDevice(User2DeviceHubRequest req) throws UserNotFoundException {
        IoTDevice ioTDevice = IoTDevice.builder()
                .devEUI(req.getDevEUI())
                .ownership(Collections.singletonList(req.getEmail()))
                .build();

        DeviceRequest deviceRequest = new DeviceRequest(
                req.getTraceid(),
                new ArrayList<>(),
                Collections.singletonList(ioTDevice)
        );

        // devices db
        StringResponse res = restTemplate.postForObject(URL_DEVICEHUB + "/update", deviceRequest, StringResponse.class);

        if (res == null){
            return new StringResponse(req.getTraceid(), "Failed to link device.");
        } else if (res.getCode() != 200) {
            return res;
        }

        // user db
        User user = findByEmail(req.getEmail());
        List<String> devices = user.getDevices();
        if(!devices.contains(req.getDevEUI())) {
            devices.add(req.getDevEUI());
            user.setDevices(devices);
            update(req.getEmail(), user);
        }

        return new StringResponse(res.getTraceid(), "Device successfully linked to user.");
    }

    public StringResponse unlinkDevice(User2DeviceHubRequest req) throws UserNotFoundException {
        IoTDevice ioTDevice = IoTDevice.builder()
                .devEUI(req.getDevEUI())
                .ownership(new ArrayList<>())
                .build();

        DeviceRequest deviceRequest = new DeviceRequest(
                req.getTraceid(),
                new ArrayList<>(),
                Collections.singletonList(ioTDevice)
        );

        // devices db
        StringResponse res = restTemplate.postForObject(URL_DEVICEHUB + "/update", deviceRequest, StringResponse.class);

        if (res == null){
            return new StringResponse(req.getTraceid(), "Failed to unlink device.");
        } else if (res.getCode() != 200) {
            return res;
        }

        // user db
        User user = findByEmail(req.getEmail());
        List<String> devices = user.getDevices();
        if(devices.contains(req.getDevEUI())) {
            devices.remove(req.getDevEUI());
            user.setDevices(devices);
            update(req.getEmail(), user);
        }

        return new StringResponse(res.getTraceid(), "Device successfully unlinked from user.");
    }

    public BaseResponse listDevices(User2DeviceHubRequest req) throws UserNotFoundException {
        User user = findByEmail(req.getEmail());

        List<String> devEUIList = new ArrayList<>();
        if (user != null && !user.getDevices().isEmpty()) {
            devEUIList.addAll(user.getDevices());
        }

        DeviceRequest deviceRequest = new DeviceRequest(
                req.getTraceid(),
                devEUIList,
                new ArrayList<>()
        );

        // devices db
        DeviceResponse res = restTemplate.postForObject(URL_DEVICEHUB + "/find", deviceRequest, DeviceResponse.class);

        if (res == null){
            return new StringResponse(req.getTraceid(), "Failed to retrieve devices.");
        } else if (res.getCode() != 200) {
            return res;
        }

        return res;
    }

    public BaseResponse listDeviceComm(IoTDataRequest req) {
        // devices db
        IoTDataResponse res = restTemplate.postForObject(URL_DATAHUB + "/find_by_eui", req, IoTDataResponse.class);

        if (res == null){
            return new StringResponse(req.getTraceid(), "Failed to retrieve device data.");
        } else if (res.getCode() != 200) {
            return res;
        }

        return res;
    }

    public BaseResponse listAllDataByEmail(IoTDataRequest req) {
        // devices db
        IoTDataResponse res = restTemplate.postForObject(URL_DATAHUB + "/find_by_email", req, IoTDataResponse.class);

        if (res == null){
            return new StringResponse(req.getTraceid(), "Failed to retrieve device data.");
        } else if (res.getCode() != 200) {
            return res;
        }

        return res;
    }

    public BaseResponse toggleSensor(OperationRequest req) {
        kafkaService.send(req);

        return new StringResponse(req.getTraceid(), "Operation update sensor passed to devicehub.");
    }
}
