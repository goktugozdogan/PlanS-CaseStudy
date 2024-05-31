package com.example.plans.sharedhub.constants;

import com.example.plans.sharedhub.models.ReturnCode;

import java.util.ArrayList;
import java.util.List;

public final class ReturnCodeConstants {
    private static final List<ReturnCode> RETURN_CODES = new ArrayList<>();

    // Initialize return codes and messages
    static {
        // REST Codes

        RETURN_CODES.add(new ReturnCode(200, "Success"));
        RETURN_CODES.add(new ReturnCode(400, "Bad Request"));
        RETURN_CODES.add(new ReturnCode(401, "Unauthorized"));
        RETURN_CODES.add(new ReturnCode(403, "Forbidden"));
        RETURN_CODES.add(new ReturnCode(404, "Not Found"));
        RETURN_CODES.add(new ReturnCode(500, "Internal Server Error"));

        // DeviceHub Errors
        RETURN_CODES.add(new ReturnCode(1, "APP ERROR"));

        // DeviceHub Errors
        RETURN_CODES.add(new ReturnCode(10, "DEVICEHUB ERROR"));

        // DataTransactionHub Errors
        RETURN_CODES.add(new ReturnCode(20, "IOTDATAHUB ERROR"));

        // UserHub Errors
        RETURN_CODES.add(new ReturnCode(30, "USERHUB ERROR"));

        // MongoDB Errors
        RETURN_CODES.add(new ReturnCode(40, "Failed to connect to MongoDB server."));

        // Kafka Errors
        RETURN_CODES.add(new ReturnCode(50, "Kafka broker is not available."));

        // Auth Errors
        RETURN_CODES.add(new ReturnCode(60, "Invalid token."));
    }

    // Get message corresponding to the return code
    public static String getMessage(int code) {
        for (ReturnCode returnCode : RETURN_CODES) {
            if (returnCode.getCode() == code) {
                return returnCode.getMsg();
            }
        }
        return "Unknown code";
    }
}
