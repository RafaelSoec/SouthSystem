package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.model.entities.Pasta;

@Repository
public interface PastaRepository extends JpaRepository<Pasta, Long> {


}
