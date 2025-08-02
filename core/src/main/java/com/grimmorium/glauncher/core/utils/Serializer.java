package com.grimmorium.glauncher.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serializer {
    private static Serializer instance;

    public static Serializer getSerializer() {
        if (instance == null) {
            instance = new Serializer();
        }

        return instance;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    private Serializer() {}

    public <TObject> String Serialize(TObject object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <TObject> TObject Deserialize(String json, Class<TObject> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
