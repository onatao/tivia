package com.neidev.tivia.service;

import com.neidev.tivia.domain.core.json.documento.DocumentoForm;

import java.util.List;

public interface DocumentoUseCase {

    List<DocumentoForm> listarDocumentosBeneficiario(String id);
}
