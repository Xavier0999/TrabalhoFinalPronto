package com.example.CadastroDeVotos.repository;

import com.example.CadastroDeVotos.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {


    Restaurante findByNomeRestaurante(String nomeRestaurante);
}
