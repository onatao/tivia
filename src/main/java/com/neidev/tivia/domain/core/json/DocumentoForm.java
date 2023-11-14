package com.neidev.tivia.domain.core.json;

import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.core.model.Documento;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class DocumentoForm {

    private String id;
    private String tipoDocumento;
    private String descricao;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;
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
