package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.model.entities.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {


    @Query("SELECT count(v.id) As total FROM Votacao v  WHERE v.idSessao = ?1")
    Double recuperarTotalVotos(Long idSessao);

    @Query("SELECT count(v.id) as total FROM Votacao v  WHERE v.idSessao = ?1 AND v.voto = 'NAO'")
    Double recuperarTotalVotosContra(Long idSessao);

    @Query("SELECT count(v.id) as total FROM Votacao v  WHERE v.idSessao = ?1 AND v.voto = 'SIM'")
    Double recuperarTotalVotosAFavor(Long idSessao);
}
