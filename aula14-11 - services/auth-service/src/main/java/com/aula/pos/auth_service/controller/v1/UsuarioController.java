package com.aula.pos.auth_service.controller.v1;

import com.aula.pos.auth_service.dto.RegisterRequest;
import com.aula.pos.auth_service.dto.RegisterResponse;
import com.aula.pos.auth_service.dto.UpdateUsuarioRequest;
import com.aula.pos.auth_service.dto.UsuarioResponse;
import com.aula.pos.auth_service.service.impl.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.context.WebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final WebServerApplicationContext context;

    @GetMapping("/status")
    public String status() {
        return String.valueOf(context.getWebServer().getPort());
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.register(request));
    }

    @GetMapping
    public List<UsuarioResponse> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable UUID id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUsuarioRequest request
    ) {
        return usuarioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
