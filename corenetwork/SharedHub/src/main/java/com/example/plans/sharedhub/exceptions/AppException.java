package com.example.plans.sharedhub.exceptions;

import com.example.plans.sharedhub.models.response.StringResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends Exception {
    private int code;
    private String msg;
    private String detail;

    public AppException(int code, String msg, String detail) {
        super(detail);
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public AppException(String detail) {
        super(detail);
        this.code = 1;
        this.msg = "APP ERROR";
        this.detail = detail;
    }

    public AppException() {
        super();
        this.code = 1;
        this.msg = "APP ERROR";
        this.detail = "";
    }

    public StringResponse res(){
        return new StringResponse(code, msg, detail);
    }
}
