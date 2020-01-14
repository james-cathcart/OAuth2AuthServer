package com.madhax.oauthdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/read-check")
    public ResponseEntity<String> readCheck() {
        ResponseEntity<String> responseEntity =
                new ResponseEntity<>(
                        "Successfully accessed read endpoint.",
                        HttpStatus.OK
                );

        return  responseEntity;
    }

    @GetMapping("/write-check")
    public ResponseEntity<String> writeCheck() {
        ResponseEntity<String> responseEntity =
                new ResponseEntity<>(
                        "Successfully accessed write endpoint.",
                        HttpStatus.OK
                );

        return  responseEntity;
    }

    @GetMapping("/delete-check")
    public ResponseEntity<String> deleteCheck() {
        ResponseEntity<String> responseEntity =
                new ResponseEntity<>(
                        "Successfully accessed delete endpoint.",
                        HttpStatus.OK
                );

        return  responseEntity;
    }

}
