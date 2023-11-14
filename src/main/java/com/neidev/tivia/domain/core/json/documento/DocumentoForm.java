package com.neidev.tivia.domain.core.json.documento;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class DocumentoForm {

    @NotBlank
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String tipoDocumento;
    @NotBlank
    @NotNull
    private String descricao;
    @NotBlank
    @NotNull
    private LocalDate dataInclusao;
    @NotBlank
    @NotNull
    private LocalDate dataAtualizacao;
    @NotBlank
    @NotNull
    private Beneficiario beneficiario;

    public Documento paraEntidade() {
        return Documento.builder()
                .id(getId())
                .tipoDocumento(getTipoDocumento())
                .descricao(getDescricao())
                .dataInclusao(getDataInclusao())
                .dataAtualizacao(getDataAtualizacao())
                .beneficiario(getBeneficiario()).build();
    }
}
