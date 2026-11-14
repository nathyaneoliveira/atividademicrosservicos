package com.aula.pos.msemprestimo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "equipamento_id", length = 36, nullable = false, updatable = false)
    private UUID equipamentoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusEmprestimo status = StatusEmprestimo.EM_ANDAMENTO;

    protected Emprestimo() {
    }

    public Emprestimo(UUID equipamentoId) {
        this.equipamentoId = equipamentoId;
        this.status = StatusEmprestimo.EM_ANDAMENTO;
    }

    public UUID getId() {
        return id;
    }

    public UUID getEquipamentoId() {
        return equipamentoId;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void devolver() {
        this.status = StatusEmprestimo.DEVOLVIDO;
    }
}
