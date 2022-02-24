package com.company.controller;

import com.company.model.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;


@RestControllerAdvice
public class FileUploadExceptionControllerAdvice {

    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public ResponseEntity<ResponseObject> handleMaxSizeException() {
        ResponseObject result = new ResponseObject();
        result.setErrors(Collections.singletonList("$.Error size, file too large. Uploaded file must be less than 10 mb"));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }
}
