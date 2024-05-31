package com.example.plans.sharedhub.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StringResponse extends BaseResponse {
    private String answer;

    public StringResponse(int code, String msg, String traceid, String timestamp, double cost, String answer) {
        super(code, msg, "string", traceid, timestamp, cost);
        this.answer = answer;
    }

    public StringResponse(String traceid, String timestamp, double cost, String answer) {
        super("string", traceid, timestamp, cost);
        this.answer = answer;
    }

    public StringResponse(String traceid, String answer) {
        super("string", traceid, "", 0);
        this.answer = answer;
    }

    public StringResponse(int code, String msg, String answer) {
        super(code, msg, "string", "", "", 0);
        this.answer = answer;
    }
}
