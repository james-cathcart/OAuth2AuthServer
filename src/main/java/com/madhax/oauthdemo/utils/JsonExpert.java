package com.madhax.oauthdemo.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonExpert {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonExpert() { }

    public static String serialize(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T deserialize(String jsonString, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonString, clazz);
    }

}
