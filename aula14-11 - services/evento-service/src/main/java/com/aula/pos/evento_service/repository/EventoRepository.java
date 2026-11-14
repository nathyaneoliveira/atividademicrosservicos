package com.aula.pos.evento_service.repository;

import com.aula.pos.evento_service.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {

    Page<Evento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Evento> findByCidadeIgnoreCase(String cidade, Pageable pageable);
    Page<Evento> findByEstadoIgnoreCase(String estado, Pageable pageable);
}
