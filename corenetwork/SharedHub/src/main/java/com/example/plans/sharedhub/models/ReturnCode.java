package com.example.plans.sharedhub.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnCode {
    private int code;
    private String msg;
}
