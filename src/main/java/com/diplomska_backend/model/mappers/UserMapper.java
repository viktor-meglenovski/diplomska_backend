package com.diplomska_backend.model.mappers;

import com.diplomska_backend.model.dto.SignUpDto;
import com.diplomska_backend.model.dto.UserDto;
import com.diplomska_backend.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
