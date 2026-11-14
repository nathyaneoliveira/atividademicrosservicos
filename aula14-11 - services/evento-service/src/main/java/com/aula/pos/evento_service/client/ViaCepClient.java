package com.aula.pos.evento_service.client;

import com.aula.pos.evento_service.dto.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "viaCeoClient",
        url = "${integrations.viacep.url}"
)
public interface ViaCepClient {
    @GetMapping("/ws/{cep}/json")
    CepResponse busarCep(@PathVariable String cep);
}
