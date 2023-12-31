package com.neidev.tivia.domain.repository;

import com.neidev.tivia.domain.core.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, String> {

    UserDetails findByLogin(String login);
}
