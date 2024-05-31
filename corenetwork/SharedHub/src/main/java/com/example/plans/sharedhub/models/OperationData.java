package com.example.plans.sharedhub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("operations")
public class OperationData {
    @Id
    @JsonIgnore
    private String id;

    private String traceid;

    private OperationInfo operationInfo;

    private List<IoTDevice> ioTDevice;
}

