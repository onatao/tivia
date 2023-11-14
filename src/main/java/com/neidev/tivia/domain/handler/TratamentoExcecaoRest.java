package com.neidev.tivia.domain.handler;

import com.neidev.tivia.domain.handler.excecoes.BeneficiarioExcecao;
import com.neidev.tivia.domain.handler.excecoes.DocumentoExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class TratamentoExcecaoRest extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Excecao> trataExcecoesGerais(Exception e, WebRequest request) {
        Excecao excecao = new Excecao(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(excecao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DocumentoExcecao.class)
    public final ResponseEntity<Excecao> trataExcecaoDocumento(Exception e, WebRequest request) {
        Excecao excecao = new Excecao(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(excecao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BeneficiarioExcecao.class)
    public final ResponseEntity<Excecao> trataExcecaoBeneficiario(Exception e, WebRequest request) {
        Excecao excecao = new Excecao(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(excecao, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
