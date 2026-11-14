package com.aula.pos.inscricao_service.repository;

import com.aula.pos.inscricao_service.enums.StatusInscricao;
import com.aula.pos.inscricao_service.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, UUID> {

    boolean existsByUsuarioIdAndEventoIdAndStatusInscricao(UUID usuarioId, UUID eventoId, StatusInscricao statusInscricao);
    long countByEventoIdAndStatusInscricao(UUID eventoId, StatusInscricao statusInscricao);

}
