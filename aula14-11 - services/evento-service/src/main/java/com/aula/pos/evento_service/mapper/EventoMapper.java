package com.aula.pos.evento_service.mapper;

import com.aula.pos.evento_service.dto.EventoRequest;
import com.aula.pos.evento_service.dto.EventoResponse;
import com.aula.pos.evento_service.dto.PartialEventoRequest;
import com.aula.pos.evento_service.model.Evento;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public Evento toEntity(EventoRequest request){
        return new Evento(
                null,
                request.nome().trim(),
                request.dataHoraEvento(),
                request.local().trim(),
                (request.cidade() != null) ? request.cidade().trim() : null,
                (request.estado() != null) ? request.estado().trim() : null,
                request.qtde()
        );
    }

    public void updateEntity(EventoRequest request, Evento evento){
        evento.setNome(request.nome().trim());
        evento.setDataHoraEvento(request.dataHoraEvento());
        evento.setLocal(request.local().trim());
        evento.setCidade(request.cidade().trim());
        evento.setEstado(request.estado().trim());
        evento.setQtde(request.qtde());
    }

    public void partialUpdateEntity(PartialEventoRequest request, Evento evento){
        if(request.nome() != null) evento.setNome(request.nome().trim());
        if(request.dataHoraEvento() != null) evento.setDataHoraEvento(request.dataHoraEvento());
        if(request.local() != null) evento.setLocal(request.local().trim());
        if(request.cidade() != null) evento.setCidade(request.cidade().trim());
        if(request.estado() != null) evento.setEstado(request.estado().trim());
        if(request.qtde() != null) evento.setQtde(request.qtde());
    }

    public EventoResponse toResponse(Evento evento){
        return new EventoResponse(
                evento.getId(),
                evento.getNome(),
                evento.getDataHoraEvento(),
                evento.getLocal(),
                evento.getCidade(),
                evento.getEstado(),
                evento.getQtde()
        );
    }
}
