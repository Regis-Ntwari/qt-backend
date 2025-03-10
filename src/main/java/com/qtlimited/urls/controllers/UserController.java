package com.qtlimited.urls.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qtlimited.urls.dto.UserCreationDto;
import com.qtlimited.urls.dto.UserLoginDto;
import com.qtlimited.urls.payload.BodyResponse;
import com.qtlimited.urls.service.UserServiceInterface;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/login")
    public ResponseEntity<BodyResponse> authenticate(@RequestBody UserLoginDto loginDto) {
        try {
            return new ResponseEntity<>(userService.authenticate(loginDto.getUsername(), loginDto.getPassword()),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<BodyResponse> createUser(@RequestBody UserCreationDto userCreationDto) {
        try {
            return new ResponseEntity<>(userService.createUser(userCreationDto),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<BodyResponse> logout() {
        try {
            // return new ResponseEntity<>(userService.authenticate(loginDto.getUsername(),
            // loginDto.getPassword()),
            // HttpStatus.OK);

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BodyResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
