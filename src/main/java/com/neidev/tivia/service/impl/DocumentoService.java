package com.neidev.tivia.service.impl;

import com.neidev.tivia.domain.core.json.documento.DocumentoForm;
import com.neidev.tivia.domain.handler.excecoes.DocumentoExcecao;
import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.domain.repository.DocumentoRepository;
import com.neidev.tivia.service.DocumentoUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentoService implements DocumentoUseCase {

    private BeneficiarioRepository beneficiarioRepository;
    private DocumentoRepository documentoRepository;

    public DocumentoService(BeneficiarioRepository beneficiarioRepository, DocumentoRepository documentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.documentoRepository = documentoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentoForm> listarDocumentosBeneficiario(String id) {
        try {
            var beneficiarioOptional = beneficiarioRepository.findById(id);

            if (beneficiarioOptional.isEmpty())
                throw new DocumentoExcecao("Beneficiário não cadastrado no sistema");

            var entidadeBeneficiario = beneficiarioOptional.get();

            var listaEntidadeDocumentos = entidadeBeneficiario.getDocumentos();

            if (listaEntidadeDocumentos.isEmpty())
                throw new DocumentoExcecao("Não há registro de documentos cadastrados");

            return listaEntidadeDocumentos.stream()
                    .map(o -> o.paraForm()).collect(Collectors.toList());
        } catch (DocumentoExcecao e) {
            throw new DocumentoExcecao(e.getMessage());
        }
    }
}
