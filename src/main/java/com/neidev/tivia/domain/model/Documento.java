package com.neidev.tivia.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity(name = "DOCUMENTO")
@Table(name = "TB_DOCUMENTO")
@NoArgsConstructor
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
    private String dataInclusao;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataAtualizacao;


}
