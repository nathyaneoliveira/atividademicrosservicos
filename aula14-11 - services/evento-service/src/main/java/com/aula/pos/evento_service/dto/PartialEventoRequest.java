package com.aula.pos.evento_service.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PartialEventoRequest(
        @Size(min = 3, max = 150, message = "Nome deve possuir entre 3 à 100 caracteres")
        String nome,
        @Future(message = "Data/Hora evento deve estar no futuro")
        LocalDateTime dataHoraEvento,
        @Size(max = 200, message = "Local deve possuir no máximo 100 caracteres")
        String local,
        @Size(max = 100, message = "A cidade deve possuir no máximo 100 caracteres")
        String cidade,
        @Size(max = 2, message = "Estado deve possuir no máximo 2 caracteres")
        String estado,
        @Min(value = 1, message = "Quantidade de vagas deve ser maior que zero")
        Integer qtde
) {
}
