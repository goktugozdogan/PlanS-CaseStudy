package com.example.plans.sharedhub.models.response;

import com.example.plans.sharedhub.models.IoTDevice;
import com.example.plans.sharedhub.models.IoTDeviceData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IoTDataResponse extends EntityResponse {
    List<IoTDeviceData> answer;

    public IoTDataResponse(int code, String msg, String traceid, String timestamp, double cost) {
        super(code, msg, traceid, timestamp, cost);
    }

    public IoTDataResponse(String traceid, String timestamp, double cost) {
        super(traceid, timestamp, cost);
    }

    public IoTDataResponse(String traceid, List<IoTDeviceData> answer) {
        super(traceid);
        this.answer = answer;
    }
}
