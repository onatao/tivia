package com.neidev.tivia.domain.repository;

import com.neidev.tivia.domain.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
}
