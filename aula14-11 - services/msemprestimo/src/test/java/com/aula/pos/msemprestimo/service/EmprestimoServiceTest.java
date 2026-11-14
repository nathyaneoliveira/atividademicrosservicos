package com.aula.pos.msemprestimo.service;

import com.aula.pos.msemprestimo.client.EquipamentoGateway;
import com.aula.pos.msemprestimo.dto.DevolucaoRequest;
import com.aula.pos.msemprestimo.model.Condicao;
import com.aula.pos.msemprestimo.model.Emprestimo;
import com.aula.pos.msemprestimo.model.StatusEmprestimo;
import com.aula.pos.msemprestimo.repository.EmprestimoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository repository;

    @Mock
    private EquipamentoGateway equipamentoGateway;

    private Emprestimo emprestimo;
    private EmprestimoService service;
    private UUID emprestimoId;

    @BeforeEach
    void setUp() {
        service = new EmprestimoService(repository, equipamentoGateway);
        emprestimoId = UUID.randomUUID();
        emprestimo = new Emprestimo(UUID.randomUUID());
        when(repository.findById(emprestimoId)).thenReturn(Optional.of(emprestimo));
    }

    @Test
    void devolucaoAlteraCondicaoAntesDeLiberarReservaEConcluir() {
        when(repository.save(any(Emprestimo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = service.devolver(emprestimoId, new DevolucaoRequest(Condicao.AVARIADO));

        InOrder ordem = inOrder(equipamentoGateway, repository);
        ordem.verify(equipamentoGateway).alterarCondicao(emprestimo.getEquipamentoId(),
                new com.aula.pos.msemprestimo.dto.CondicaoRequest(Condicao.AVARIADO));
        ordem.verify(equipamentoGateway).alterarReserva(emprestimo.getEquipamentoId(), false);
        ordem.verify(repository).save(emprestimo);
        assertEquals(StatusEmprestimo.DEVOLVIDO, response.status());
    }

    @Test
    void falhaAoAlterarCondicaoNaoLiberaReservaNemConcluiEmprestimo() {
        when(equipamentoGateway.alterarCondicao(any(), any()))
                .thenThrow(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE));

        assertThrows(ResponseStatusException.class,
                () -> service.devolver(emprestimoId, new DevolucaoRequest(Condicao.BOM)));

        verify(equipamentoGateway, never()).alterarReserva(any(), anyBoolean());
        verify(repository, never()).save(any(Emprestimo.class));
        assertEquals(StatusEmprestimo.EM_ANDAMENTO, emprestimo.getStatus());
    }
}
