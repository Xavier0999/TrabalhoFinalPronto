package com.example.CadastroDeVotos.repository;

import com.example.CadastroDeVotos.model.TotalDeVotos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class VotoRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public List<TotalDeVotos> TotalVotosDosRestaurante() {
        String query = "select new com.example.CadastroDeVotos.model.TotalDeVotos(e.nomeRestaurante, COUNT(c.Id)) " +
                "   from Votos c " +
                " inner join c.restaurante e " +
                "  group by e.nomeRestaurante order by COUNT(e.Id) DESC";

        TypedQuery<TotalDeVotos> TotalVotosDosRestauranteQuery =
                this.entityManager.createQuery(query, TotalDeVotos.class);
        return TotalVotosDosRestauranteQuery.getResultList();
    }


    public boolean VotosPorDia(Integer funcionarioId, Calendar data) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(g) FROM Votos g WHERE g.funcionario.id = :id AND g.data = :data", Long.class);
        query.setParameter("id", funcionarioId);
        query.setParameter("data", data, TemporalType.DATE);
        return query.getSingleResult() > 0;
    }
}
