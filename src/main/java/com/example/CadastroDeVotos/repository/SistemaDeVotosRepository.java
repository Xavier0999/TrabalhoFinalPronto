package com.example.CadastroDeVotos.repository;

import com.example.CadastroDeVotos.model.Votos;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SistemaDeVotosRepository extends JpaRepository<Votos, Integer> {



}
