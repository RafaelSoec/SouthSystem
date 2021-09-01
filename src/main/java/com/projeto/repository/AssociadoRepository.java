package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.model.entities.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {


    @Query("SELECT v FROM Associado v WHERE v.cpf = ?1")
    Associado recuperarAssociadoPorCpf(String cpf);
}
