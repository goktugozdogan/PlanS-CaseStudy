package com.example.plans.sharedhub.models.request;

import com.example.plans.sharedhub.models.IoTDeviceData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IoTDataRequest extends BaseRequest {
    @JsonProperty("id")
    private List<String> ids;

    @JsonProperty("devEUI")
    private List<String> devEUIs;

    @JsonProperty("email")
    private List<String> emails;

    @JsonProperty("ioTData")
    private List<IoTDeviceData> ioTDeviceData;

    public IoTDataRequest(String traceid, List<String> devEUIs, List<String> emails, List<IoTDeviceData> ioTDeviceData) {
        super(traceid);
        this.devEUIs = devEUIs;
        this.emails = emails;
        this.ioTDeviceData = ioTDeviceData;
    }
}
