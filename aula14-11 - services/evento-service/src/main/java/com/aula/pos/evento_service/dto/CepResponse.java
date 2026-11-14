package com.aula.pos.evento_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CepResponse(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    @JsonProperty("uf") String estado,
    String ibge,
    String gia,
    @JsonProperty("error") String error
) {
}
