package com.neidev.tivia.service;

import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioCadastroForm;
import com.neidev.tivia.domain.core.json.beneficiario.BeneficiarioForm;
import com.neidev.tivia.domain.core.model.Beneficiario;

import java.util.List;

public interface BeneficiarioUseCase {

    BeneficiarioForm cadastrarBeneficiario(BeneficiarioCadastroForm data);
    List<BeneficiarioForm> listarBeneficiarios();

    BeneficiarioForm atualizarBeneficario(Beneficiario data, String id);

    void removerBeneficiario(String id);
}
