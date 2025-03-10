package com.qtlimited.urls.service;

import com.qtlimited.urls.dto.UserCreationDto;
import com.qtlimited.urls.payload.BodyResponse;

public interface UserServiceInterface {
    BodyResponse authenticate(String username, String password);

    BodyResponse logout();

    BodyResponse createUser(UserCreationDto userCreationDto) throws Exception;
}
