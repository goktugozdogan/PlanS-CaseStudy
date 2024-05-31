package com.example.plans.userhub.exceptions;

import com.example.plans.sharedhub.constants.ReturnCodeConstants;
import com.example.plans.sharedhub.exceptions.AppException;

public class AuthenticationException extends AppException {
    private final static int RETURN_CODE = 401;
    private final static String RETURN_MSG = ReturnCodeConstants.getMessage(RETURN_CODE);

    public AuthenticationException(int code, String msg, String detail) {
        super(code, msg, detail);
    }

    public AuthenticationException(String detail) {
        this(RETURN_CODE, RETURN_MSG, detail);
    }

    public AuthenticationException() {
        this("");
    }
}
