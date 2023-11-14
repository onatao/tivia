package com.neidev.tivia.domain.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Excecao {

    private Date data;
    private String mensagem;
    private String detalhes;

}
