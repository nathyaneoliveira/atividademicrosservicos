package com.aula.pos.msequipamento.dto;

import jakarta.validation.constraints.NotNull;

public record ReservaRequest(
        @NotNull Boolean reservado
) {
}
