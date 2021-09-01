package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.model.entities.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {


}
