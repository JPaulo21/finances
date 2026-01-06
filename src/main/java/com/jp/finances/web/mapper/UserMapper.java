package com.jp.finances.web.mapper;

import com.jp.finances.domain.user.User;
import com.jp.finances.web.dto.request.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDTO dto);
}
