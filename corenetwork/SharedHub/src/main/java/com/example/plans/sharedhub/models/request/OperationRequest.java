package com.example.plans.sharedhub.models.request;

import com.example.plans.sharedhub.models.deviceunit.Sensor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OperationRequest extends BaseRequest {
    private String devEUI;

    private String email;

    private List<Sensor> sensors;

}
