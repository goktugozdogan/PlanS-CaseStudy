package com.example.plans.sharedhub.controllers;

import com.example.plans.sharedhub.exceptions.AppException;
import com.example.plans.sharedhub.models.request.BaseRequest;
import com.example.plans.sharedhub.models.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController {
    public abstract String getStatus();

    public abstract BaseResponse findById(@RequestBody BaseRequest req) throws AppException;

    public abstract BaseResponse createDevice(@RequestBody BaseRequest req) throws AppException;

    public abstract BaseResponse updateDevice(@RequestBody BaseRequest req) throws AppException;

    public abstract BaseResponse deleteById(@RequestBody BaseRequest req) throws AppException;
}
