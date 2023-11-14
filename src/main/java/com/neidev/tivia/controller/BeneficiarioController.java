package com.neidev.tivia.controller;

import com.neidev.tivia.domain.model.Beneficiario;
import com.neidev.tivia.domain.model.Documento;
import com.neidev.tivia.service.BeneficiarioService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BeneficiarioController {

    private BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Beneficiario> registrar(@RequestBody Beneficiario data) {
        return ResponseEntity.ok().body(beneficiarioService.cadastrar(data));
    }

    @GetMapping("/documentos/{id}")
    public ResponseEntity<List<Documento>> documentos(@PathVariable String id) {
        return ResponseEntity.ok().body(beneficiarioService.findDocById(id));
    }
}
