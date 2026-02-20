package com.fsr.security.mapper;

import org.mapstruct.Mapper;

import com.fsr.security.dto.UsuarioDTO;
import com.fsr.security.model.Usuario;

@Mapper(componentModel = "spring", uses = TokenMapper.class)
public interface UsuarioMapper {

	UsuarioDTO toDTO(Usuario usuario);

	Usuario toEntity(UsuarioDTO dto);

}
