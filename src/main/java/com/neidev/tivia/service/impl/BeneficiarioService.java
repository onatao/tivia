package com.neidev.tivia.service.impl;

import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioCadastroForm;
import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioForm;
import com.neidev.tivia.domain.core.model.Beneficiario;
import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.service.BeneficiarioUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService implements BeneficiarioUseCase {

    private BeneficiarioRepository beneficiarioRepository;
    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    @Override
    @Transactional
    public BeneficiarioForm cadastrarBeneficiario(BeneficiarioCadastroForm data) {
        try {
            var optionalBeneficiario = beneficiarioRepository.findById(data.getId());

            if (optionalBeneficiario.isPresent())
                throw new IllegalArgumentException("Beneficiário já cadastrado no sistema");

            var beneficiarioEntidade = data.paraEntidade();
            beneficiarioEntidade.setDataInclusao(LocalDate.now());
            beneficiarioEntidade.setDataAtualizacao(LocalDate.now());

            beneficiarioRepository.save(beneficiarioEntidade);
            return beneficiarioEntidade.paraForm();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BeneficiarioForm> listarBeneficiarios() {
        try {
            List<Beneficiario> listaBeneficiarios = beneficiarioRepository.findAll();

            if (listaBeneficiarios.isEmpty())
                throw new IllegalArgumentException("Não existem beneficiários registrados");

            var listaResposta = listaBeneficiarios.stream()
                    .map(o -> o.paraForm()).collect(Collectors.toList());

            return listaResposta;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public BeneficiarioForm atualizarBeneficario(Beneficiario data, String id) {
        try {
            Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);

            if (beneficiarioOptional.isEmpty())
                throw new IllegalArgumentException("Beneficiário não cadastrado");

            var entidadeBeneficiario = beneficiarioOptional.get();
            entidadeBeneficiario.setNome(data.getNome());
            entidadeBeneficiario.setDocumentos(data.getDocumentos());
            entidadeBeneficiario.setDataAtualizacao(LocalDate.now());
            entidadeBeneficiario.setDataNascimento(data.getDataNascimento());
            entidadeBeneficiario.setDataInclusao(data.getDataInclusao());

            beneficiarioRepository.save(entidadeBeneficiario);
            return entidadeBeneficiario.paraForm();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void removerBeneficiario(String id) {
        try {
            Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);

            if (beneficiarioOptional.isEmpty())
                throw new IllegalArgumentException("Beneficiário não cadastrado");

            var entidadeBeneficario = beneficiarioOptional.get();
            beneficiarioRepository.delete(entidadeBeneficario);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
