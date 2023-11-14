package com.neidev.tivia.domain.core.model;

import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioForm;
import com.neidev.tivia.security.enums.CargoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "BENEFICIARIO")
@Table(name = "TB_BENEFICIARIO")
@Builder
@AllArgsConstructor
public class Beneficiario extends User {

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
    private LocalDate dataInclusao;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    private List<Documento> documentos = new ArrayList<>();

    public Beneficiario(String login, String senha, CargoEnum cargo) {
        super(login, senha, cargo);
    }

    public BeneficiarioForm paraForm() {
        return BeneficiarioForm.builder()
                .id(getId())
                .nome(getNome())
                .telefone(getTelefone())
                .dataNascimento(getDataNascimento())
                .dataInclusao(getDataInclusao())
                .dataAtualizacao(getDataAtualizacao()).build();
    }

}