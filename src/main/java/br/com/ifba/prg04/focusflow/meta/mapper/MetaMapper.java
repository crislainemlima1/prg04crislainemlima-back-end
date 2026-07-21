package br.com.ifba.prg04.focusflow.meta.mapper;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.meta.dto.MetaRequestDTO;
import br.com.ifba.prg04.focusflow.meta.dto.MetaResponseDTO;
import br.com.ifba.prg04.focusflow.meta.model.Meta;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MetaMapper {

    // Converte RequestDTO para entidade
    public Meta toEntity(MetaRequestDTO dto, Usuario usuario, Materia materia) {
        Meta meta = new Meta();
        meta.setHorasObjetivo(dto.getHorasObjetivo());
        meta.setDataLimite(dto.getDataLimite());
        meta.setConcluida(dto.getConcluida() != null ? dto.getConcluida() : false);
        meta.setUsuario(usuario);
        meta.setMateria(materia);
        return meta;
    }

    // Converte entidade para ResponseDTO
    public MetaResponseDTO toResponseDTO(Meta meta) {
        MetaResponseDTO dto = new MetaResponseDTO();
        dto.setId(meta.getId());
        dto.setHorasObjetivo(meta.getHorasObjetivo());
        dto.setDataLimite(meta.getDataLimite());
        dto.setConcluida(meta.getConcluida());
        dto.setUsuarioId(meta.getUsuario().getId());
        dto.setUsuarioNome(meta.getUsuario().getNome());
        dto.setMateriaId(meta.getMateria().getId());
        dto.setMateriaNome(meta.getMateria().getNome());
        return dto;
    }
}
