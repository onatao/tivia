package com.neidev.tivia.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "BENEFICIARIO")
@Table(name = "TB_BENEFICIARIO")
@NoArgsConstructor
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataNascimento;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataInclusao;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataAtualizacao;


}