package com.aula.pos.inscricao_service.service.impl;

import com.aula.pos.inscricao_service.client.EventoClient;
import com.aula.pos.inscricao_service.client.UsuarioClient;
import com.aula.pos.inscricao_service.client.gateway.UsuarioGateway;
import com.aula.pos.inscricao_service.dto.InscricaoRequest;
import com.aula.pos.inscricao_service.dto.InscricaoResponse;
import com.aula.pos.inscricao_service.enums.StatusInscricao;
import com.aula.pos.inscricao_service.event.InscricaoConfirmadaEvent;
import com.aula.pos.inscricao_service.exception.BusinessException;
import com.aula.pos.inscricao_service.exception.ConflictException;
import com.aula.pos.inscricao_service.mapper.InscricaoMapper;
import com.aula.pos.inscricao_service.model.Inscricao;
import com.aula.pos.inscricao_service.repository.InscricaoRepository;
import com.aula.pos.inscricao_service.service.InscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InscricaoServiceImpl implements InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final InscricaoMapper inscricaoMapper;
    private final EventoClient eventoClient;
    //private final UsuarioClient usuarioClient;
    private final UsuarioGateway usuarioGateway;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public InscricaoResponse criar(InscricaoRequest inscricaoRequest) {
        var usuario = usuarioGateway.findById(inscricaoRequest.usuarioId());
        var evento = eventoClient.findById(inscricaoRequest.eventoId());

        if(evento.dataHoraEvento().isBefore(LocalDateTime.now())){
            throw new BusinessException("Não possível se inscrever em um evento que já foi realizado.");
        }

        var jaInscrito = inscricaoRepository.existsByUsuarioIdAndEventoIdAndStatusInscricao(
                inscricaoRequest.usuarioId(),
                inscricaoRequest.eventoId(),
                StatusInscricao.CONFIRMADA
        );

        if(jaInscrito){
            throw new ConflictException("Pessoa já inscrita no evento.");
        }

        var qtdeInscritos = inscricaoRepository.countByEventoIdAndStatusInscricao(
                inscricaoRequest.eventoId(),
                StatusInscricao.CONFIRMADA
        );

        if(qtdeInscritos >= evento.qtde()){
            throw new BusinessException("Inscrições esgotadas");
        }

        Inscricao novaInscricao = new Inscricao(
          null,
          usuario.id(),
          evento.id(),
          StatusInscricao.CONFIRMADA,
          LocalDateTime.now()
        );

        var inscricaoSalva = inscricaoRepository.save(novaInscricao);

        eventPublisher.publishEvent(new InscricaoConfirmadaEvent(
                inscricaoSalva.getId(),
                usuario.email(),
                usuario.telefone(),
                usuario.nome(),
                evento.nome()
        ));

        System.out.println("Evento: " + evento.nome()+ ", Usuario: " + usuario.nome());
        return inscricaoMapper.toResponse(inscricaoSalva, usuario, evento);
    }
}
