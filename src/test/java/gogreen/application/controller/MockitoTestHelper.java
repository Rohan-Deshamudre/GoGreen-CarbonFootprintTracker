package gogreen.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockitoTestHelper {
    /**
     * Converts an arbitrary object to a json string using the jackson library.
     * @param obj - an arbitrary object to convert.
     * @return - a string representation of the given object.
     */
    public static String toJSONString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
