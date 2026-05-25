package br.com.ifba.prg04.focusflow.mapper;

import br.com.ifba.prg04.focusflow.DTO.UsuarioRequestDTO;
import br.com.ifba.prg04.focusflow.DTO.UsuarioResponseDTO;
import br.com.ifba.prg04.focusflow.model.Usuario;
import org.springframework.stereotype.Component;

// classe responsavel por converter entre entidade e dto
@Component
public class UsuarioMapper {

    // converte o requestDTO para entidade
    public Usuario toEntity(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    //converte entidade para o respondeDTO
    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
