package com.aula.pos.msequipamento.model;

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
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Condicao condicao = Condicao.BOM;

    @Column(nullable = false)
    private boolean reservado;

    protected Equipamento() {
    }

    public Equipamento(String nome) {
        this.nome = nome;
        this.condicao = Condicao.BOM;
        this.reservado = false;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Condicao getCondicao() {
        return condicao;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void alterarCondicao(Condicao condicao) {
        this.condicao = condicao;
    }

    public void alterarReserva(boolean reservado) {
        this.reservado = reservado;
    }
}
