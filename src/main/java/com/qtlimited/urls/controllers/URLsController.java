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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class URLsController {

    @Autowired
    private URLsServiceInterface urLsService;

    @PostMapping("/shorten")
    public ResponseEntity<BodyResponse> shortenURL(@RequestBody ShortenURLDto shortenURLDto) {
        try {
            return new ResponseEntity<>(urLsService.shortenUrl(shortenURLDto.getLongUrl()),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/urls")
    public ResponseEntity<BodyResponse> getURLs() {
        try {
            return new ResponseEntity<>(urLsService.getUrls(),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/analytics/{shortUrl}")
    public ResponseEntity<BodyResponse> getAnalytics(@PathVariable String shortUrl) {
        try {
            return new ResponseEntity<>(urLsService.getUrlsAnalytics(shortUrl),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
