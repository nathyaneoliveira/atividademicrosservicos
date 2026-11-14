package com.aula.pos.msemprestimo.repository;

import com.aula.pos.msemprestimo.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {
}
