package com.aula.pos.evento_service.service.impl;

import com.aula.pos.evento_service.dto.EventoRequest;
import com.aula.pos.evento_service.dto.EventoResponse;
import com.aula.pos.evento_service.dto.PartialEventoRequest;
import com.aula.pos.evento_service.exception.ResourceNotFoundException;
import com.aula.pos.evento_service.mapper.EventoMapper;
import com.aula.pos.evento_service.model.Evento;
import com.aula.pos.evento_service.repository.EventoRepository;
import com.aula.pos.evento_service.service.CepService;
import com.aula.pos.evento_service.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository repository;
    private final EventoMapper mapper;
    private final CepService cepService;

    @Override
    public Page<EventoResponse> findAll(String nome, String cidade, String estado, Pageable pageable) {
        Page<Evento> page = ((nome != null) && (!nome.isBlank())) ? repository.findByNomeContainingIgnoreCase(nome.trim(), pageable) :
                ((cidade != null) && (!cidade.isBlank())) ? repository.findByCidadeIgnoreCase(cidade.trim(), pageable) :
                        ((estado != null) && (!estado.isBlank())) ? repository.findByEstadoIgnoreCase(estado.trim(), pageable) :
                                repository.findAll(pageable);

        return page.map(mapper::toResponse);
    }

    @Override
    public EventoResponse findById(UUID id) {
        return mapper.toResponse(findByIdOrThrow(id));
    }

    @Override
    public EventoResponse save(EventoRequest request) {

        var evento = mapper.toEntity(request);

        if(request.cep() != null && !request.cep().isBlank()){
            var data = cepService.consultar(request.cep().trim());

            evento.setCidade(data.localidade());
            evento.setEstado(data.estado());
        }

        return mapper.toResponse(repository.save(evento));
    }

    @Override
    public EventoResponse update(UUID id, EventoRequest request) {
        var evento = findByIdOrThrow(id);

//        long inscritos = inscricaoRepository.countByEventoIdAndStatusInscricao(
//                id, StatusInscricao.ATIVA
//        );
//
//        if (inscritos > 0) {
//            throw new BusinessException(
//                    "Não é possível excluir um evento com inscrições ativas"
//            );
//        }

        mapper.updateEntity(request, evento);
        return mapper.toResponse(repository.save(evento));
    }

    @Override
    public EventoResponse partialUpdate(UUID id, PartialEventoRequest request) {
        var evento = findByIdOrThrow(id);

//        if (request.qtde() != null) {
//            long inscritos = inscricaoRepository.countByEventoIdAndStatusInscricao(
//                    id, StatusInscricao.ATIVA
//            );
//
//            if (request.qtde() < inscritos) {
//                throw new BusinessException(
//                        "A quantidade de vagas não pode ser menor que o número de inscrições ativas"
//                );
//            }
//        }

        mapper.partialUpdateEntity(request, evento);
        return mapper.toResponse(repository.save(evento));
    }

    @Override
    public void deleteById(UUID id) {
        findByIdOrThrow(id);
        repository.deleteById(id);
    }

    private Evento findByIdOrThrow(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
