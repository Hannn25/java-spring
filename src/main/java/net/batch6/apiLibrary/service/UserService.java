package net.batch6.apiLibrary.service;

import net.batch6.apiLibrary.model.dto.ResponseData;
import net.batch6.apiLibrary.model.dto.UserDto;


    public interface UserService {
        ResponseData<Object> register(UserDto request);

        ResponseData<Object> login(UserDto request);
    }

