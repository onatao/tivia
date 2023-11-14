package com.neidev.tivia.service;

import com.neidev.tivia.domain.model.Beneficiario;
import com.neidev.tivia.domain.model.Documento;
import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.domain.repository.DocumentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService {

    private BeneficiarioRepository beneficiarioRepository;
    private DocumentoRepository documentoRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, DocumentoRepository documentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.documentoRepository = documentoRepository;
    }


}
