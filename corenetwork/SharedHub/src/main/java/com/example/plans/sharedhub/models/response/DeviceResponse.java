package com.example.plans.sharedhub.models.response;

import com.example.plans.sharedhub.models.IoTDevice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse extends EntityResponse {
    List<IoTDevice> answer;

    public DeviceResponse(int code, String msg, String traceid, String timestamp, double cost) {
        super(code, msg, traceid, timestamp, cost);
    }

    public DeviceResponse(String traceid, String timestamp, double cost) {
        super(traceid, timestamp, cost);
    }

    public DeviceResponse(String traceid, List<IoTDevice> answer) {
        super(traceid);
        this.answer = answer;
    }
}
