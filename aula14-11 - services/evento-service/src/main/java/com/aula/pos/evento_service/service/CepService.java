package com.aula.pos.evento_service.service;


import com.aula.pos.evento_service.dto.CepResponse;

public interface CepService {

    CepResponse consultar(String cep);
}
