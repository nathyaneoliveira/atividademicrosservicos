package com.aula.pos.inscricao_service.service.impl;

import com.aula.pos.inscricao_service.dto.InscricaoRequest;
import com.aula.pos.inscricao_service.dto.InscricaoResponse;
import com.aula.pos.inscricao_service.mapper.InscricaoMapper;
import com.aula.pos.inscricao_service.repository.InscricaoRepository;
import com.aula.pos.inscricao_service.service.InscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InscricaoServiceImpl implements InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final InscricaoMapper inscricaoMapper;


    @Override
    @Transactional
    public InscricaoResponse criar(InscricaoRequest inscricaoRequest) {
        return null;
    }
}
