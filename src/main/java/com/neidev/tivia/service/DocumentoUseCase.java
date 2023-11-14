package com.neidev.tivia.service;

import com.neidev.tivia.domain.core.json.DocumentoForm;
import com.neidev.tivia.domain.core.model.Documento;

import java.util.List;

public interface DocumentoUseCase {

    List<DocumentoForm> listarDocumentosBeneficiario(String id);
}
