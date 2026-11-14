package com.aula.pos.inscricao_service.model;


import com.aula.pos.inscricao_service.enums.StatusInscricao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_inscricoes")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "usuario_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID usuarioId;

    @Column(name = "evento_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID eventoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_inscricao", nullable = false, length = 20)
    private StatusInscricao statusInscricao;

    @Column(name = "data_hora_inscricao", nullable = false)
    private LocalDateTime dataHoraInscricao;
}
