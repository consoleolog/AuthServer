package com.example.authserver.v1.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String filedName;
    private final Object fieldValue;

    public ResourceNotFoundException(String resourceName,String filedName,  Object fieldValue) {
        super("not found with " + resourceName + ":" + filedName + ":" + fieldValue);
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
