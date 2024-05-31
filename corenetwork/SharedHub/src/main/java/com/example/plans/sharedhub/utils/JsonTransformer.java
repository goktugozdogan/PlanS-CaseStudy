package com.example.plans.sharedhub.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTransformer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Transform Object to JSON String
    public static String objectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    // Transform JSON String to Object
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, clazz);
    }
}
