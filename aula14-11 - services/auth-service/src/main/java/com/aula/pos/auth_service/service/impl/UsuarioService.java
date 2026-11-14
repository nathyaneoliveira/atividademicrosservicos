package com.aula.pos.auth_service.service.impl;

import com.aula.pos.auth_service.dto.PublicRegisterRequest;
import com.aula.pos.auth_service.dto.RegisterRequest;
import com.aula.pos.auth_service.dto.RegisterResponse;
import com.aula.pos.auth_service.dto.UpdateUsuarioRequest;
import com.aula.pos.auth_service.dto.UsuarioResponse;
import com.aula.pos.auth_service.enums.Role;
import com.aula.pos.auth_service.exception.ConflictException;
import com.aula.pos.auth_service.exception.ResourceNotFoundException;
import com.aula.pos.auth_service.model.Usuario;
import com.aula.pos.auth_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(PublicRegisterRequest request) {
        return register(new RegisterRequest(
                request.nome(),
                request.cpf(),
                request.telefone(),
                request.email(),
                request.password(),
                Role.USER
        ));
    }

    public RegisterResponse register(RegisterRequest request) {
        var email = request.email().trim().toLowerCase();
        var cpf = onlyDigits(request.cpf());

        if (usuarioRepository.existsByEmail(email)) {
            throw new ConflictException("E-mail já cadastrado");
        }
        if (usuarioRepository.existsByCpf(cpf)) {
            throw new ConflictException("CPF já cadastrado");
        }

        var usuario = new Usuario();
        usuario.setNome(normalizeName(request.nome()));
        usuario.setCpf(cpf);
        usuario.setTelefone(onlyDigits(request.telefone()));
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRole(request.role());

        var usuarioSalvo = usuarioRepository.save(usuario);
        return RegisterResponse.from(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse findById(UUID id) {
        return UsuarioResponse.from(findUsuarioById(id));
    }

    @Transactional
    public UsuarioResponse update(UUID id, UpdateUsuarioRequest request) {
        var usuario = findUsuarioById(id);

        if (request.nome() != null) {
            usuario.setNome(normalizeName(request.nome()));
        }

        if (request.cpf() != null) {
            var cpf = onlyDigits(request.cpf());
            if (!cpf.equals(usuario.getCpf()) && usuarioRepository.existsByCpf(cpf)) {
                throw new ConflictException("CPF já cadastrado");
            }
            usuario.setCpf(cpf);
        }

        if (request.telefone() != null) {
            usuario.setTelefone(onlyDigits(request.telefone()));
        }

        if (request.email() != null) {
            var email = request.email().trim().toLowerCase();
            if (!email.equals(usuario.getEmail()) && usuarioRepository.existsByEmail(email)) {
                throw new ConflictException("E-mail já cadastrado");
            }
            usuario.setEmail(email);
        }

        if (request.password() != null) {
            usuario.setPassword(passwordEncoder.encode(request.password()));
        }

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    @Transactional
    public void delete(UUID id) {
        var usuario = findUsuarioById(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario findUsuarioById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private String normalizeName(String nome) {
        return nome.trim().replaceAll("\\s+", " ");
    }

    private String onlyDigits(String value) {
        return value.replaceAll("\\D", "");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
