package com.example.plans.sharedhub.models.deviceunit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Metadata {
    private String manufacturer;
    private String model;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("manufacture_date")
    private String manufactureDate;
}
