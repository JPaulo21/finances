package com.jp.finances.web.mapper;

import com.jp.finances.domain.account.Account;
import com.jp.finances.web.dto.request.AccountCreateRequest;
import com.jp.finances.web.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountCreateRequest accountCreateRequest);

    @Mapping(target = "userId", source = "user.id")
    AccountResponse toResponse(Account account);
}
