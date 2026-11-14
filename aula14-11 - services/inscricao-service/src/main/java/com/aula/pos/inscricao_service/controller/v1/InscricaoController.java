package com.aula.pos.inscricao_service.controller.v1;

import com.aula.pos.inscricao_service.dto.InscricaoRequest;
import com.aula.pos.inscricao_service.dto.InscricaoResponse;
import com.aula.pos.inscricao_service.service.InscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoService inscricaoService;

    @PostMapping
    public InscricaoResponse criar(@RequestBody InscricaoRequest inscricaoRequest){
        return inscricaoService.criar(inscricaoRequest);
    }
}
