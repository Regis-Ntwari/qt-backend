package com.qtlimited.urls.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qtlimited.urls.domain.User;
import com.qtlimited.urls.dto.UserCreationDto;
import com.qtlimited.urls.payload.BodyResponse;
import com.qtlimited.urls.repository.UserRepository;
import com.qtlimited.urls.utils.JWTService;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public BodyResponse authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = userRepository.findByUsername(username).get();

        BodyResponse response = new BodyResponse();
        response.setProcessed(true);
        response.setResult(buildLoginResponse(user, false));
        response.setStatusCode(HttpStatus.OK);

        return response;
    }

    private Map<String, Object> buildLoginResponse(User user, boolean isCreation) {

        Map<String, Object> body = new HashMap<>();
        body.put("username", user.getUsername());
        body.put("email", user.getEmail());
        if (isCreation) {
            body.put("created_at", user.getCreatedAt());
        } else {
            String jwtToken = jwtService.generateToken(user);
            body.put("token", jwtToken);
        }

        return body;
    }

    @Override
    @Transactional
    public BodyResponse logout() {
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    @Transactional
    public BodyResponse createUser(UserCreationDto userCreationDto) throws Exception {

        try {
            if (userRepository.existsByEmail(userCreationDto.getEmail())) {
                throw new Exception("Email already exists");
            }

            if (userRepository.existsByUsername(userCreationDto.getUsername())) {
                throw new Exception("Username already exists");
            }

            User user = User.builder().email(userCreationDto.getEmail()).username(userCreationDto.getUsername())
                    .password(passwordEncoder.encode(userCreationDto.getPassword())).build();

            userRepository.save(user);

            BodyResponse response = new BodyResponse();
            response.setProcessed(true);
            response.setResult(buildLoginResponse(user, true));
            response.setStatusCode(HttpStatus.CREATED);

            return response;

        } catch (Exception e) {
            throw new Exception("Error Occurred: " + e.getMessage());
        }

    }
}
