package com.aula.pos.msemprestimo.client;

import com.aula.pos.msemprestimo.dto.CondicaoRequest;
import com.aula.pos.msemprestimo.dto.EquipamentoResponse;
import com.aula.pos.msemprestimo.dto.ReservaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "msequipamento")
public interface EquipamentoClient {

    @PatchMapping("/api/v1/equipamentos/{id}/condicao")
    EquipamentoResponse alterarCondicao(@PathVariable UUID id, @RequestBody CondicaoRequest request);

    @PatchMapping("/api/v1/equipamentos/{id}/reserva")
    EquipamentoResponse alterarReserva(@PathVariable UUID id, @RequestBody ReservaRequest request);
}
