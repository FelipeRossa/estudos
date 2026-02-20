package com.fsr.security.mapper;

import org.mapstruct.Mapper;

import com.fsr.security.dto.TokenDTO;
import com.fsr.security.model.Token;

@Mapper(componentModel = "spring")
public interface TokenMapper {

	TokenDTO toDTO(Token usuario);

	Token toEntity(TokenDTO dto);
}
