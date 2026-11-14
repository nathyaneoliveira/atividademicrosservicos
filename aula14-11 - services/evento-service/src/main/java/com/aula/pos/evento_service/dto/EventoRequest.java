package com.aula.pos.evento_service.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record EventoRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 150, message = "Nome deve possuir entre 3 e 150 caracteres")
        String nome,

        @NotNull(message = "Data/Hora é obrigatório")
        @Future(message = "Data/Hora evento deve estar no futuro")
        LocalDateTime dataHoraEvento,

        @NotBlank(message = "Local é obrigatório")
        @Size(max = 200, message = "Local deve possuir no máximo 200 caracteres")
        String local,

        @Size(max = 100, message = "A cidade deve possuir no máximo 100 caracteres")
        String cidade,

        @Size(max = 2, message = "Estado deve possuir no máximo 2 caracteres")
        String estado,

        @NotNull(message = "Qtde é obrigatório")
        @Min(value = 1, message = "Quantidade de vagas deve ser maior que zero")
        Integer qtde,

        @Size(max = 9, message = "Cep deve possuir no máximo 9 caracteres")
        String cep

) {

        @AssertTrue(message = "Cidade e Estado são obrigatórios quando o CEP não for informado")
        public boolean isCidadeEstadoValid() {

                if (cep != null && !cep.isBlank()) {
                        return true;
                }

                return cidade != null && !cidade.isBlank()
                        && estado != null && !estado.isBlank();
        }
}