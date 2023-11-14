package com.neidev.tivia.domain.core.json.beneficiario;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BeneficiarioForm {

    @NotBlank
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String telefone;
    @NotBlank
    @NotNull
    private String dataNascimento;
    @NotBlank
    @NotNull
    private LocalDate dataInclusao;
    @NotBlank
    @NotNull
    private LocalDate dataAtualizacao;
    @NotBlank
    @NotNull
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
