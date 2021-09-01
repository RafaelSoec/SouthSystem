package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.model.entities.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    @Query("SELECT v FROM Votacao v WHERE v.cpfAssociado = ?1")
    Votacao buscarVotacaoPorCPF(String cpfAssociado);
	

}
