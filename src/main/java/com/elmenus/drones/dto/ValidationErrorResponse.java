package com.elmenus.drones.dto;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponse {

    public static Map<String,String> serializeErrorResult (BindingResult bindingResult){
        Map<String,String> fieldErrors = new HashMap<>();
        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return fieldErrors;
    }
}
