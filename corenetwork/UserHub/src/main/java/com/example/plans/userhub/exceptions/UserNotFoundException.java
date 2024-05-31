package com.example.plans.userhub.exceptions;

import com.example.plans.sharedhub.constants.ReturnCodeConstants;
import com.example.plans.sharedhub.exceptions.AppException;

public class UserNotFoundException extends AppException {
    private final static int RETURN_CODE = 30;
    private final static String RETURN_MSG = ReturnCodeConstants.getMessage(RETURN_CODE);

    public UserNotFoundException(int code, String msg, String detail) {
        super(code, msg, detail);
    }

    public UserNotFoundException(String detail) {
        this(RETURN_CODE, RETURN_MSG, detail);
    }

    public UserNotFoundException() {
        this("");
    }
}
