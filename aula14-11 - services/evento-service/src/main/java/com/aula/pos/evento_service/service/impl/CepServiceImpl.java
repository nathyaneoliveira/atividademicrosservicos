package com.aula.pos.evento_service.service.impl;

import com.aula.pos.evento_service.client.ViaCepClient;
import com.aula.pos.evento_service.dto.CepResponse;
import com.aula.pos.evento_service.exception.BusinessException;
import com.aula.pos.evento_service.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CepServiceImpl implements CepService {

    private final ViaCepClient viaCepClient;

    @Override
    public CepResponse consultar(String cep) {

        String cepNormalizado = cep.replaceAll("\\D", "");

        if(cepNormalizado.length() != 8){
            throw new BusinessException("CEP deve possuir 8 digitos");
        }

        CepResponse cepResponse = viaCepClient.busarCep(cepNormalizado);

        if(cepResponse.error() != null && cepResponse.error().equalsIgnoreCase("true")){
            throw new BusinessException("CEP não encontrado");
        }

        return cepResponse;
    }
}
