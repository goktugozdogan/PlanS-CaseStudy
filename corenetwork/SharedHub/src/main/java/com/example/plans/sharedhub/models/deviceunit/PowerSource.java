package com.example.plans.sharedhub.models.deviceunit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PowerSource {
    private String type;
    private ValueUnit capacity;
    private ValueUnit voltage;

    @JsonProperty("expected_lifetime")
    private String expectedLifetime;
}

@Data
class ValueUnit {
    private double value;
    private String unit;
}