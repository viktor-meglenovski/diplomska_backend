package com.diplomska_backend.service;

import com.diplomska_backend.model.dto.CredentialsDto;
import com.diplomska_backend.model.dto.SignUpDto;
import com.diplomska_backend.model.dto.UserDto;

public interface UserService {
    UserDto login(CredentialsDto credentialsDto);
    UserDto register(SignUpDto userDto);
    UserDto findByLogin(String login);
}
