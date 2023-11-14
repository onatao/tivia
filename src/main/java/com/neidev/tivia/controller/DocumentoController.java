package com.neidev.tivia.controller;

import com.neidev.tivia.domain.core.json.documento.DocumentoForm;
import com.neidev.tivia.service.impl.DocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.Document;
import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

    private DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Deverá listar todos os documentos de um determinado beneficiário pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação concluída com sucessso."),
            @ApiResponse(responseCode = "400", description = "Operação não pôde ser concluída"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")

    })
    public ResponseEntity<List<DocumentoForm>> listarDocumentosBeneficiario(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(documentoService.listarDocumentosBeneficiario(id));
    }
}
