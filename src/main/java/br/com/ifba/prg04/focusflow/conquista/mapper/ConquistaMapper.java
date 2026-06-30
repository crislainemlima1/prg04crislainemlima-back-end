package br.com.ifba.prg04.focusflow.conquista.mapper;

import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaRequestDTO;
import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaResponseDTO;
import br.com.ifba.prg04.focusflow.conquista.model.Conquista;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import org.springframework.stereotype.Component;

// Classe responsável por converter entre entidade e DTO
@Component
public class ConquistaMapper {

    // Converte RequestDTO para entidade
    public Conquista toEntity(ConquistaRequestDTO dto, Usuario usuario) {
        Conquista conquista = new Conquista();
        conquista.setTitulo(dto.getTitulo());
        conquista.setDescricao(dto.getDescricao());
        conquista.setDataConquista(dto.getDataConquista());
        conquista.setUsuario(usuario);
        return conquista;
    }

    // Converte entidade para ResponseDTO
    public ConquistaResponseDTO toResponseDTO(Conquista conquista) {
        ConquistaResponseDTO dto = new ConquistaResponseDTO();
        dto.setId(conquista.getId());
        dto.setTitulo(conquista.getTitulo());
        dto.setDescricao(conquista.getDescricao());
        dto.setDataConquista(conquista.getDataConquista());
        dto.setUsuarioId(conquista.getUsuario().getId());
        dto.setUsuarioNome(conquista.getUsuario().getNome());
        return dto;
    }
}