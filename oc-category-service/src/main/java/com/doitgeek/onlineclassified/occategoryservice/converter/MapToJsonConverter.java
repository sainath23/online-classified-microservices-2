package com.doitgeek.onlineclassified.occategoryservice.converter;

import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapToJsonConverter.class);

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String json = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            LOGGER.error(ErrorMessage.JSON_MAP_TO_STRING_FAIL, e);
        }

        return json;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dbData, HashMap.class);
        } catch (IOException e) {
            LOGGER.error(ErrorMessage.JSON_STRING_TO_MAP_FAIL, e);
            return null;
        }
    }
}
