package com.shibkov.tasknotebook.app.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by alexxxshib
 */
public class Parser {

    public static String toJson(Object object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
