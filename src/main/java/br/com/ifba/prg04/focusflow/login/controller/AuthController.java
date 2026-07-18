package br.com.ifba.prg04.focusflow.login.controller;

import br.com.ifba.prg04.focusflow.login.dto.LoginRequestDTO;
import br.com.ifba.prg04.focusflow.login.dto.LoginResponseDTO;
import br.com.ifba.prg04.focusflow.security.JwtService;
import br.com.ifba.prg04.focusflow.usuario.mapper.UsuarioMapper;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

// Controller responsável pela autenticação
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // Injeta automaticamente o service de usuário
    private final UsuarioService usuarioService;

    // Injeta o mapper para converter entidade <> DTO
    private final UsuarioMapper mapper;

    // Injeta o PasswordEncoder para verificar senhas criptografadas
    private final PasswordEncoder passwordEncoder;

    // Injeta o serviço responsável por gerar o token JWT
    private final JwtService jwtService;

    // autentica o usuário e retorna um token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(dto.getEmail()); // Busca o usuário pelo email informado

        // Verifica se o usuário existe e se a senha informada corresponde à senha criptografada no banco
        if (usuarioOpt.isPresent() && passwordEncoder.matches(dto.getSenha(), usuarioOpt.get().getSenha())) {
            Usuario usuario = usuarioOpt.get();
            String token = jwtService.gerarToken(usuario.getId(), usuario.getEmail());

            // Retorna o token + os dados do usuário (sem senha)
            return ResponseEntity.ok(new LoginResponseDTO(token, mapper.toResponseDTO(usuario)));
        }

        // Se inválido, retorna erro 401 (não autorizado) com mensagem
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("mensagem", "E-mail ou senha inválidos"));
    }
}