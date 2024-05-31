package com.example.plans.sharedhub.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EntityResponse extends BaseResponse {

    public EntityResponse(int code, String msg, String traceid, String timestamp, double cost) {
        super(code, msg, "entity", traceid, timestamp, cost);
    }

    public EntityResponse(String traceid, String timestamp, double cost) {
        super("entity", traceid, timestamp, cost);
    }

    public EntityResponse(String traceid) {
        super("entity", traceid, "", 0);
    }
}
