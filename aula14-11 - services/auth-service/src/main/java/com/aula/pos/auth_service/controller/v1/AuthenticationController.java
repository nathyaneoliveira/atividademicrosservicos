package com.aula.pos.auth_service.controller.v1;

import com.aula.pos.auth_service.dto.LoginRequest;
import com.aula.pos.auth_service.dto.PublicRegisterRequest;
import com.aula.pos.auth_service.dto.RegisterResponse;
import com.aula.pos.auth_service.dto.TokenResponse;
import com.aula.pos.auth_service.model.Usuario;
import com.aula.pos.auth_service.service.impl.TokenService;
import com.aula.pos.auth_service.service.impl.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody PublicRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.register(request));
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        var credenciais = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(credenciais);
        } catch (AuthenticationException exception) {
            log.warn("Failed login attempt for user {}", request.email());
            throw exception;
        }

        var usuario = (Usuario) auth.getPrincipal();
        String token = tokenService.generateToken(usuario);
        log.info("Successful login for user {}", usuario.getEmail());
        return new TokenResponse(token);
    }
}
