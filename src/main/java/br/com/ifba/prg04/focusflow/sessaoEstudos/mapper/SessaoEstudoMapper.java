package br.com.ifba.prg04.focusflow.sessaoEstudos.mapper;

import br.com.ifba.prg04.focusflow.sessaoEstudos.dto.SessaoEstudoRequestDTO;
import br.com.ifba.prg04.focusflow.sessaoEstudos.dto.SessaoEstudoResponseDTO;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.sessaoEstudos.model.SessaoEstudo;
import org.springframework.stereotype.Component;

@Component
public class SessaoEstudoMapper {

    // Converte RequestDTO para entidade
    public SessaoEstudo toEntity(SessaoEstudoRequestDTO dto, Materia materia) {
        SessaoEstudo sessao = new SessaoEstudo();
        sessao.setData(dto.getData());
        sessao.setDuracaoMinutos(dto.getDuracaoMinutos());
        sessao.setMateria(materia);
        return sessao;
    }

    // Converte entidade para ResponseDTO
    public SessaoEstudoResponseDTO toResponseDTO(SessaoEstudo sessao) {
        SessaoEstudoResponseDTO dto = new SessaoEstudoResponseDTO();
        dto.setId(sessao.getId());
        dto.setData(sessao.getData());
        dto.setDuracaoMinutos(sessao.getDuracaoMinutos());
        dto.setMateriaId(sessao.getMateria().getId());
        dto.setMateriaNome(sessao.getMateria().getNome());
        return dto;
    }
}

