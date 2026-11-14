package com.aula.pos.evento_service.model;

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
@Table(name = "tb_eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private LocalDateTime dataHoraEvento;
    @Column(nullable = false, length = 200)
    private String local;
    @Column(nullable = false, length = 100)
    private String cidade;
    @Column(nullable = false, length = 2)
    private String estado;
    @Column(nullable = false)
    private Integer qtde;
}
