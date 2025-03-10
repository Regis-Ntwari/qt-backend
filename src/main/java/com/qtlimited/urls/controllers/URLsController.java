package com.qtlimited.urls.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.qtlimited.urls.dto.ShortenURLDto;
import com.qtlimited.urls.payload.BodyResponse;
import com.qtlimited.urls.service.URLsServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class URLsController {

    @Autowired
    private URLsServiceInterface urLsService;

    @PostMapping("/shorten")
    public ResponseEntity<BodyResponse> shortenURL(@RequestBody ShortenURLDto shortenURLDto) {
        try {
            System.out.println("Hello there");
            return new ResponseEntity<>(urLsService.shortenUrl(shortenURLDto.getLongUrl()),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
