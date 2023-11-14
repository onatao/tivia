package com.neidev.tivia.service;

import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioCadastroForm;
import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import com.neidev.tivia.domain.handler.excecoes.BeneficiarioExcecao;
import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.service.impl.BeneficiarioService;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeneficiarioServiceTests {

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @InjectMocks
    private BeneficiarioService beneficiarioService;

    private Beneficiario beneficiarioMock;

    private Documento documentoMock;

    @BeforeEach
    void setUp() {
        documentoMock = new Documento(
                "7a2caa40-82fb-11ee-b962-0242ac120004",
                "CPF Teste",
                "Documento CPF Teste",
                LocalDate.now(),
                LocalDate.now(),
                beneficiarioMock

        );

        beneficiarioMock = new Beneficiario(
                "7a2caa40-82fb-11ee-b962-0242ac120002",
                "Beneficiario Teste",
                "12345678910",
                "14/11/2023",
                LocalDate.now(),
                LocalDate.now(),
                List.of(documentoMock)
        );
    }

    @Test
    @DisplayName("Deverá lancar uma exceção quando tentar cadastrar um usuário já existente")
    void deveLancarExcecaoQuandoTentarCadastrarUmBenficiarioJaCadastrado() {
        when(beneficiarioRepository.findById(any())).thenReturn(Optional.of(beneficiarioMock));

        BeneficiarioCadastroForm form = new BeneficiarioCadastroForm();
        var excecao = assertThrows(BeneficiarioExcecao.class,
                () -> beneficiarioService.cadastrarBeneficiario(form));

        verify(beneficiarioRepository, never()).save(any());
        assertEquals(excecao.getMessage(), "Beneficiário já cadastrado no sistema");
        assertTrue(excecao.getMessage().contains("Beneficiário já cadastrado no sistema"));
    }

    @Test
    @DisplayName("Deverá cadastrar um usuário e seus documentos com sucesso")
    void deveCadastrarUmBeneficiarioComSucessoERetornarBeneficiarioForm() {
        BeneficiarioCadastroForm form = new BeneficiarioCadastroForm(
                "7a2caa40-82fb-11ee-b962-0242ac120002",
                "Beneficiario Teste",
                "12345678910",
                "14/11/2023",
                List.of(documentoMock)
        );

        when(beneficiarioRepository.findById(any())).thenReturn(Optional.empty());
        when(beneficiarioRepository.save(any())).thenReturn(beneficiarioMock);

        var beneficiario = beneficiarioService.cadastrarBeneficiario(form);

        verify(beneficiarioRepository, times(1)).findById(any());
        verify(beneficiarioRepository, times(1)).save(any());

        assertEquals("7a2caa40-82fb-11ee-b962-0242ac120002", beneficiario.getId());
        assertEquals("Beneficiario Teste", beneficiario.getNome());
        assertEquals("12345678910", beneficiario.getTelefone());
        assertEquals("14/11/2023", beneficiario.getDataNascimento());
    }


    @Test
    @DisplayName("Deverá lançar uma exceção quando não houver beneficiários cadastrados")
    void deveLancarUmaExcecaoQuandoNaoHouverBeneficiariosCadastrados() {
        when(beneficiarioRepository.findAll()).thenReturn(Collections.emptyList());

        var excecao = assertThrows(BeneficiarioExcecao.class,
                () -> beneficiarioService.listarBeneficiarios());

        verify(beneficiarioRepository, times(1)).findAll();
        assertEquals(excecao.getMessage(), "Não existem beneficiários registrados");
        assertTrue(excecao.getMessage().contains("Não existem beneficiários registrados"));
    }

    @Test
    @DisplayName("Deverá retornar uma lista cotendo todos os beneficiários cadastrados")
    void deveListarTodosOsBeneficiariosCadastradosComSucesso() {
        when(beneficiarioRepository.findAll()).thenReturn(List.of(beneficiarioMock));

        var listaBeneficiario = beneficiarioService.listarBeneficiarios();
        var beneficiarioTestado = listaBeneficiario.get(0);

        verify(beneficiarioRepository, times(1)).findAll();
        assertTrue(listaBeneficiario.size() == 1);

        assertEquals("7a2caa40-82fb-11ee-b962-0242ac120002", beneficiarioTestado.getId());
        assertEquals("Beneficiario Teste", beneficiarioTestado.getNome());
        assertEquals("12345678910", beneficiarioTestado.getTelefone());
        assertEquals("14/11/2023", beneficiarioTestado.getDataNascimento());
    }

    @Test
    @DisplayName("Deverá lançar uma exceção quando tentar atualizar um beneficiário não existente")
    void deveLancarExcecaoQuantoTentarAtualizarUmBeneficiarioNaoExistente() {
        var dadosAtualizarBeneficario =  new Beneficiario(
                "7a5caa40-82fb-11ee-b962-0242ac120002",
                "Beneficiario Teste Atualizado",
                "12345678910 Atualizado",
                "14/11/2023 Atualizado",
                LocalDate.now(),
                LocalDate.now(),
                List.of(documentoMock)
        );

        when(beneficiarioRepository.findById(any())).thenReturn(Optional.empty());

        var excecao = assertThrows(BeneficiarioExcecao.class,
                () -> beneficiarioService.atualizarBeneficario(dadosAtualizarBeneficario, beneficiarioMock.getId()));

        verify(beneficiarioRepository, never()).save(any());
        assertEquals(excecao.getMessage(), "Beneficiário não cadastrado");
        assertTrue(excecao.getMessage().contains("Beneficiário não cadastrado"));
    }

    @Test
    @DisplayName("Deverá encontrar um beneficiário e atualizã-lo com sucesso")
    void deveEncontrarUmBeneficiarioEAtualizarSuasInformacoes() {
        var dadosAtualizarBeneficario =  new Beneficiario(
                "7a5caa40-82fb-11ee-b962-0242ac120002",
                "Beneficiario Teste Atualizado",
                "12345678910 Atualizado",
                "14/11/2023 Atualizado",
                LocalDate.now(),
                LocalDate.now(),
                List.of(documentoMock)
        );
        when(beneficiarioRepository.findById(any())).thenReturn(Optional.of(beneficiarioMock));

        var beneficiarioAtualizado = beneficiarioService
                .atualizarBeneficario(dadosAtualizarBeneficario, beneficiarioMock.getId());

        verify(beneficiarioRepository, times(1)).save(any());
        assertEquals("Beneficiario Teste Atualizado", beneficiarioAtualizado.getNome());
        assertEquals("12345678910 Atualizado", beneficiarioAtualizado.getTelefone());
        assertEquals("14/11/2023 Atualizado", beneficiarioAtualizado.getDataNascimento());
    }

    @Test
    @DisplayName("Deverá lançar uma exceção quando tentar excluir um beneficiário que não está cadastrado")
    void deveLancarExecaoQuandoTentarExcluirUmBeneficiarioNaoCadastrado() {
        when(beneficiarioRepository.findById(any())).thenReturn(Optional.empty());

        var excecao = assertThrows(BeneficiarioExcecao.class,
                () -> beneficiarioService.removerBeneficiario(beneficiarioMock.getId()));

        verify(beneficiarioRepository, never()).deleteById(any());
        assertEquals(excecao.getMessage(), "Beneficiário não cadastrado");
        assertTrue(excecao.getMessage().contains("Beneficiário não cadastrado"));
    }

    @Test
    @DisplayName("Deverá encontrar um beneficiário pelo seu ID, deletá-lo e não retornar nada")
    void deveEncontrarUmBeneficiarioPeloIdDeletarOMesmoENaoRetornanNada() {
        doNothing().when(beneficiarioRepository).deleteById(any());
        when(beneficiarioRepository.findById(any())).thenReturn(Optional.of(beneficiarioMock));

        beneficiarioService.removerBeneficiario(beneficiarioMock.getId());
        verify(beneficiarioRepository, times(1)).deleteById(any());
    }

}
