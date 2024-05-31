package com.example.plans.sharedhub.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationInfo {
    private String result;

    private String msg;

    private String initiatorEmail;

    private String initiateTime;

    private String operationTime;
}
