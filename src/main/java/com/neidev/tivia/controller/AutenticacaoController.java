package com.neidev.tivia.controller;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.security.enums.CargoEnum;
import com.neidev.tivia.security.record.LoginRecord;
import com.neidev.tivia.security.record.LoginResponse;
import com.neidev.tivia.security.service.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AutenticacaoController {

    private AuthenticationManager manager;
    private JwtTokenService tokenService;
    private BeneficiarioRepository beneficiarioRepository;

    public AutenticacaoController(AuthenticationManager manager, JwtTokenService tokenService, BeneficiarioRepository beneficiarioRepository) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody LoginRecord data) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = manager.authenticate(usernamePasswordToken);
        var token = tokenService.gerarToken((Beneficiario) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponse(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid Beneficiario data) {
        if (beneficiarioRepository.findByLogin(data.getLogin()) != null)
            return ResponseEntity.badRequest().build();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(data.getSenha());
        Beneficiario newUser = new Beneficiario(data.getLogin(), senhaEncriptada, CargoEnum.ADMIN);

        beneficiarioRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
