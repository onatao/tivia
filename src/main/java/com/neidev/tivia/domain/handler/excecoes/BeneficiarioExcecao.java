package com.neidev.tivia.domain.handler.excecoes;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeneficiarioExcecao extends RuntimeException{

    public BeneficiarioExcecao(String mensagem) {
        super(mensagem);
    }
}
