package com.example.plans.sharedhub.models.request;

import com.example.plans.sharedhub.models.IoTDevice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeviceRequest extends BaseRequest {
    @JsonProperty("devEUI")
    private List<String> devEUIs;

    @JsonProperty("iot_devices")
    private List<IoTDevice> ioTDevices;

    public DeviceRequest(String traceid, List<String> devEUIs, List<IoTDevice> ioTDevices) {
        super(traceid);
        this.devEUIs = devEUIs;
        this.ioTDevices = ioTDevices;
    }
}
