package com.madhax.oauthdemo.controller;

import com.madhax.oauthdemo.constants.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    public static final String SUCCESSFULLY_ACCESSED_READ_ENDPOINT = "Successfully accessed read endpoint.";
    public static final String SUCCESSFULLY_ACCESSED_WRITE_ENDPOINT = "Successfully accessed write endpoint.";
    public static final String SUCCESSFULLY_ACCESSED_DELETE_ENDPOINT = "Successfully accessed delete endpoint.";

    @GetMapping(AppConstants.READ_CHECK_URI)
    public ResponseEntity<String> readCheck() {
        ResponseEntity<String> responseEntity =
                new ResponseEntity<>(
                        SUCCESSFULLY_ACCESSED_READ_ENDPOINT,
                        HttpStatus.OK
                );

        return  responseEntity;
    }

    @GetMapping(AppConstants.WRITE_CHECK_URI)
    public ResponseEntity<String> writeCheck() {
        ResponseEntity<String> responseEntity =
                new ResponseEntity<>(
                        SUCCESSFULLY_ACCESSED_WRITE_ENDPOINT,
                        HttpStatus.OK
                );

        return  responseEntity;
    }

    @GetMapping(AppConstants.DELETE_CHECK_URI)
    public ResponseEntity<String> deleteCheck() {
        ResponseEntity<String> responseEntity =
                new ResponseEntity<>(
                        SUCCESSFULLY_ACCESSED_DELETE_ENDPOINT,
                        HttpStatus.OK
                );

        return  responseEntity;
    }

}
