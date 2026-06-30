package br.com.ifba.prg04.focusflow.login.controller;

import br.com.ifba.prg04.focusflow.login.dto.LoginRequestDTO;
import br.com.ifba.prg04.focusflow.usuario.mapper.UsuarioMapper;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

// Controller responsável pela autenticação
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // autentica o usuário
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(dto.getEmail());

        // Verifica se o usuário existe e se a senha corresponde
        if (usuarioOpt.isPresent() && passwordEncoder.matches(dto.getSenha(), usuarioOpt.get().getSenha())) {
            return ResponseEntity.ok(mapper.toResponseDTO(usuarioOpt.get()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("mensagem", "E-mail ou senha inválidos"));
    }
}