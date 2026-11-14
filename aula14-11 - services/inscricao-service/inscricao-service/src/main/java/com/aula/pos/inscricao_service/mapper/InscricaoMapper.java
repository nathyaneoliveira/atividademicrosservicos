package com.aula.pos.inscricao_service.mapper;

import com.aula.pos.inscricao_service.dto.EventoResponse;
import com.aula.pos.inscricao_service.dto.InscricaoResponse;
import com.aula.pos.inscricao_service.dto.UsuarioResponse;
import com.aula.pos.inscricao_service.model.Inscricao;
import org.springframework.stereotype.Component;

@Component
public class InscricaoMapper {

    public InscricaoResponse toResponse(Inscricao inscricao, UsuarioResponse usuarioResponse, EventoResponse eventoResponse){
        return new InscricaoResponse(
                inscricao.getId(),
                usuarioResponse,
                eventoResponse,
                inscricao.getStatusInscricao(),
                inscricao.getDataHoraInscricao()
        );
    }
}
