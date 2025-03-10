package com.qtlimited.urls.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.qtlimited.urls.domain.URLs;
import com.qtlimited.urls.domain.User;
import com.qtlimited.urls.payload.BodyResponse;
import com.qtlimited.urls.repository.URLsRepository;
import com.qtlimited.urls.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class URLsService implements URLsServiceInterface {

    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    private URLsRepository urLsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public BodyResponse shortenUrl(String url) throws Exception {
        try {
            User user = getAuthenticatedUser();

            // shorten Url
            String shortUrl = generateRandomShortCode(url);

            URLs urLs = new URLs();
            urLs.setClicks(0L);
            urLs.setLongUrl(url);
            urLs.setUser(user);
            urLs.setShortCode(shortUrl);

            urLsRepository.save(urLs);

            BodyResponse response = new BodyResponse();
            response.setProcessed(true);
            response.setResult(urLs);
            response.setStatusCode(HttpStatus.CREATED);

            return response;
        } catch (Exception e) {
            throw new Exception("Error Occurred: " + e.getMessage());
        }
    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        }
        throw new RuntimeException("Unauthorized access");

    }

    private String generateRandomShortCode(String longUrl) {
        String shortURL;
        do {
            shortURL = generateRandomString();
        } while (urLsRepository.existsByShortCode(shortURL));
        return shortURL;
    }

    private String generateRandomString() {
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            shortCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortCode.toString();
    }

    @Override
    @Transactional
    public BodyResponse getUrls() throws Exception {
        throw new Exception();
    }

    @Override
    @Transactional
    public BodyResponse getUrlsAnalytics(String url) throws Exception {
        throw new Exception();
    }

}
