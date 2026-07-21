package br.com.ifba.prg04.focusflow.resumo.mapper;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.resumo.dto.ResumoRequestDTO;
import br.com.ifba.prg04.focusflow.resumo.dto.ResumoResponseDTO;
import br.com.ifba.prg04.focusflow.resumo.model.Resumo;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ResumoMapper {
    public Resumo toEntity(ResumoRequestDTO dto, Usuario usuario, Materia materia) {
        Resumo resumo = new Resumo();
        resumo.setIdeiaCentral(dto.getIdeiaCentral());
        resumo.setConceitosChave(dto.getConceitosChave());
        resumo.setConexoes(dto.getConexoes());
        resumo.setDificuldadeEstimada(dto.getDificuldadeEstimada());
        resumo.setUsuario(usuario);
        resumo.setMateria(materia);
        return resumo;
    }

    public ResumoResponseDTO toResponseDTO(Resumo resumo) {
        ResumoResponseDTO dto = new ResumoResponseDTO();
        dto.setId(resumo.getId());
        dto.setIdeiaCentral(resumo.getIdeiaCentral());
        dto.setConceitosChave(resumo.getConceitosChave());
        dto.setConexoes(resumo.getConexoes());
        dto.setDificuldadeEstimada(resumo.getDificuldadeEstimada());
        dto.setUsuarioId(resumo.getUsuario().getId());
        dto.setUsuarioNome(resumo.getUsuario().getNome());
        dto.setMateriaId(resumo.getMateria().getId());
        dto.setMateriaNome(resumo.getMateria().getNome());
        return dto;
    }
}
