package com.neidev.tivia.service;

import com.neidev.tivia.domain.core.json.BeneficiarioForm;
import com.neidev.tivia.domain.core.model.Beneficiario;

import java.util.List;

public interface BeneficiarioUseCase {

    BeneficiarioForm cadastrarBeneficiario(Beneficiario data);
    List<BeneficiarioForm> listarBeneficiarios();

    BeneficiarioForm atualizarBeneficario(Beneficiario data, String id);

    void removerBeneficiario(String id);
}
