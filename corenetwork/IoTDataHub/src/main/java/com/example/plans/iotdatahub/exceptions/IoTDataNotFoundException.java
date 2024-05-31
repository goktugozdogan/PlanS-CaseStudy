package com.example.plans.iotdatahub.exceptions;

import com.example.plans.sharedhub.constants.ReturnCodeConstants;
import com.example.plans.sharedhub.exceptions.AppException;

public class IoTDataNotFoundException extends AppException {
    private final static int RETURN_CODE = 20;
    private final static String RETURN_MSG = ReturnCodeConstants.getMessage(RETURN_CODE);

    public IoTDataNotFoundException(int code, String msg, String detail) {
        super(code, msg, detail);
    }

    public IoTDataNotFoundException(String detail) {
        this(RETURN_CODE, RETURN_MSG, detail);
    }

    public IoTDataNotFoundException() {
        this("");
    }
}
