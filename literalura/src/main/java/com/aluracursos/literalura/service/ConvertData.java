package com.aluracursos.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public <T> T getData(String json, Class<T> _class) {
        try {
            return objectMapper.readValue(json, _class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
