package com.neidev.tivia.controller;

import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioCadastroForm;
import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioForm;
import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.service.impl.BeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    private BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Deverá cadastrar um beneficiário com sucesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação concluída com sucessso."),
            @ApiResponse(responseCode = "400", description = "Operação não pôde ser concluída"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")

    })
    public ResponseEntity<BeneficiarioForm> cadastrarBeneficiario(@RequestBody BeneficiarioCadastroForm data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.cadastrarBeneficiario(data));
    }

    @GetMapping("/listar")
    @Operation(summary = "Deverá listar todos os beneficiários cadastrados com sucesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação concluída com sucessso."),
            @ApiResponse(responseCode = "400", description = "Operação não pôde ser concluída"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")

    })
    public ResponseEntity<List<BeneficiarioForm>> listarBeneficiarios() {
        return ResponseEntity.status(HttpStatus.OK).body(beneficiarioService.listarBeneficiarios());
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Deverá atualizar dados de um beneficiário com sucesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação concluída com sucessso."),
            @ApiResponse(responseCode = "400", description = "Operação não pôde ser concluída"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")

    })
    public ResponseEntity<BeneficiarioForm> atualizarBeneficario(@RequestBody Beneficiario data, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(beneficiarioService.atualizarBeneficario(data, id));
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Deverá excluir um beneficiário com sucesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação concluída com sucessso."),
            @ApiResponse(responseCode = "400", description = "Operação não pôde ser concluída"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")

    })
    public ResponseEntity removerBeneficiario(@PathVariable String id) {
        beneficiarioService.removerBeneficiario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
