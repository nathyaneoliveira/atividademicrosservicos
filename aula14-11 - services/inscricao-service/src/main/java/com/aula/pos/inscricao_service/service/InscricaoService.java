package com.aula.pos.inscricao_service.service;


import com.aula.pos.inscricao_service.dto.InscricaoRequest;
import com.aula.pos.inscricao_service.dto.InscricaoResponse;

public interface InscricaoService {

    InscricaoResponse criar(InscricaoRequest inscricaoRequest);
}
