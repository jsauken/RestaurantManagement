package com.example.restapi.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Long value;
    public ResourceNotFoundException(String resourceName,String fieldName, Long value){
        super(String.format(("%s not found with %s: %s"),resourceName,fieldName,value));
        this.fieldName=fieldName;
    }
}
