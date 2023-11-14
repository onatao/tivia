package com.neidev.tivia.domain.core.model;

import com.neidev.tivia.domain.core.json.documento.DocumentoForm;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity(name = "DOCUMENTO")
@Table(name = "TB_DOCUMENTO")
@Builder
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String tipoDocumento;
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInclusao;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;

    public DocumentoForm paraForm() {
        return DocumentoForm.builder()
                .id(getId())
                .tipoDocumento(getTipoDocumento())
                .descricao(getDescricao())
                .dataInclusao(getDataInclusao())
                .dataAtualizacao(getDataAtualizacao()).build();
    }


}
