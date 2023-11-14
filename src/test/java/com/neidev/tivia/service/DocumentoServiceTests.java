package com.neidev.tivia.service;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import com.neidev.tivia.domain.handler.excecoes.DocumentoExcecao;
import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.service.impl.DocumentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentoServiceTests {


    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @InjectMocks
    private DocumentoService documentoService;

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
    @DisplayName("Deverá lançar uma exceção quando não houver um beneficiário para o ID informado.")
    void deveLancarExcecaoQuandoNaoEncontrarBeneficiarioAoListarDocumentos() {
        when(beneficiarioRepository.findById(beneficiarioMock.getId())).thenReturn(Optional.empty());

        var exception = assertThrows(DocumentoExcecao.class,
                () -> documentoService.listarDocumentosBeneficiario(beneficiarioMock.getId()));

        verify(beneficiarioRepository, never()).findAll();
        assertEquals(exception.getMessage(), "Beneficiário não cadastrado no sistema");
        assertTrue(exception.getMessage().contains("Beneficiário não cadastrado no sistema"));
    }

    @Test
    @DisplayName("Deverá encontrar um beneficiário por ID e retornar seus respectivos documentos.")
    void deveEncontrarUmBeneficiarioPorIdERetornarSeusDocumentosComSucesso() {
        when(beneficiarioRepository.findById(beneficiarioMock.getId())).thenReturn(Optional.of(beneficiarioMock));

        var listaDocumentoTeste = documentoService
                .listarDocumentosBeneficiario(beneficiarioMock.getId());
        var documentoTeste = listaDocumentoTeste.get(0);

        assertNotNull(listaDocumentoTeste);
        assertEquals("7a2caa40-82fb-11ee-b962-0242ac120004", documentoTeste.getId());
        assertEquals("CPF Teste", documentoTeste.getTipoDocumento());
        assertEquals("Documento CPF Teste", documentoTeste.getDescricao());
    }
}
