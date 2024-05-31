package com.example.plans.devicehub.exceptions;

import com.example.plans.sharedhub.constants.ReturnCodeConstants;
import com.example.plans.sharedhub.exceptions.AppException;

public class DeviceNotFoundException extends AppException {
    private final static int RETURN_CODE = 10;
    private final static String RETURN_MSG = ReturnCodeConstants.getMessage(RETURN_CODE);

    public DeviceNotFoundException(int code, String msg, String detail) {
        super(code, msg, detail);
    }

    public DeviceNotFoundException(String detail) {
        this(RETURN_CODE, RETURN_MSG, detail);
    }

    public DeviceNotFoundException() {
        this("");
    }
}
