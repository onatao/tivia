package com.neidev.tivia.domain.core.json.beneficiario;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioCadastroForm {

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
    private List<Documento> documentos = new ArrayList<>();

    public Beneficiario paraEntidade() {
        return Beneficiario.builder()
                .id(getId())
                .nome(getNome())
                .telefone(getTelefone())
                .dataNascimento(getDataNascimento())
                .documentos(getDocumentos()).build();
    }
}
