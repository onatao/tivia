package com.neidev.tivia.controller;

import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioCadastroForm;
import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioForm;
import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.service.impl.BeneficiarioService;
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
    public ResponseEntity<BeneficiarioForm> cadastrarBeneficiario(@RequestBody BeneficiarioCadastroForm data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.cadastrarBeneficiario(data));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<BeneficiarioForm>> listarBeneficiarios() {
        return ResponseEntity.status(HttpStatus.OK).body(beneficiarioService.listarBeneficiarios());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<BeneficiarioForm> atualizarBeneficario(@RequestBody Beneficiario data, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(beneficiarioService.atualizarBeneficario(data, id));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity removerBeneficiario(@PathVariable String id) {
        beneficiarioService.removerBeneficiario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
