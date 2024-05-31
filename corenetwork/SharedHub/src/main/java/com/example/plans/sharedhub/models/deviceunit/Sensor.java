package com.example.plans.sharedhub.models.deviceunit;

import lombok.Data;

@Data
public class Sensor {
    private String type;
    private Range range;
    private String status;
}
