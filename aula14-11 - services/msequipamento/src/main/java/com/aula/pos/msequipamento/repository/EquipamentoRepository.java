package com.aula.pos.msequipamento.repository;

import com.aula.pos.msequipamento.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipamentoRepository extends JpaRepository<Equipamento, UUID> {
}
