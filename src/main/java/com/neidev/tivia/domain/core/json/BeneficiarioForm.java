package com.neidev.tivia.domain.core.json;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BeneficiarioForm {

    private String id;
    private String nome;
    private String telefone;
    private String dataNascimento;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;
    private List<Documento> documentos = new ArrayList<>();

    public Beneficiario paraEntidade() {
        return Beneficiario.builder()
                .id(getId())
                .nome(getNome())
                .telefone(getTelefone())
                .dataNascimento(getDataNascimento())
                .dataInclusao(getDataInclusao())
                .dataAtualizacao(getDataAtualizacao())
                .documentos(getDocumentos()).build();
    }
}
