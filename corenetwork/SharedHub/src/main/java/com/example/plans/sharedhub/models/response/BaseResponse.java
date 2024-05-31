package com.example.plans.sharedhub.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseResponse {
    private int code;
    private String msg;
    private String answertype;
    private String traceid;
    private String timestamp;
    private double cost;

    public BaseResponse(String answertype, String traceid, String timestamp, double cost) {
        this.code = 200;
        this.msg = "SUCCESS";
        this.answertype = answertype;
        this.traceid = traceid;
        this.timestamp = timestamp;
        this.cost = cost;
    }
}
