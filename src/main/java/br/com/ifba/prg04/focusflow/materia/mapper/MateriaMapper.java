package br.com.ifba.prg04.focusflow.materia.mapper;

import br.com.ifba.prg04.focusflow.materia.dto.MateriaRequestDTO;
import br.com.ifba.prg04.focusflow.materia.dto.MateriaResponseDTO;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MateriaMapper {

    public Materia toEntity(MateriaRequestDTO dto, Usuario usuario){
        Materia materia = new Materia();
        materia.setNome(dto.getNome());
        materia.setMetaHora(dto.getMetaHora());
        materia.setUsuario(usuario);
        return materia;
    }

    public MateriaResponseDTO toResponseDTO(Materia materia){
        MateriaResponseDTO dto = new MateriaResponseDTO();
        dto.setId(materia.getId());
        dto.setNome(materia.getNome());
        dto.setMetaHora(materia.getMetaHora());
        dto.setUsuarioId(materia.getUsuario().getId());
        dto.setUsuarioNome(materia.getUsuario().getNome());
        return dto;
    }


}
