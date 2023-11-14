package com.neidev.tivia.domain.core.json.beneficiario;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BeneficiarioCadastroForm {

    private String id;
    private String nome;
    private String telefone;
    private String dataNascimento;
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
