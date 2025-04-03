package com.example.CadastroDeVotos.service;

import com.example.CadastroDeVotos.model.Funcionario;
import com.example.CadastroDeVotos.model.Restaurante;
import com.example.CadastroDeVotos.model.TotalDeVotos;
import com.example.CadastroDeVotos.model.Votos;
import com.example.CadastroDeVotos.repository.*;
import com.example.CadastroDeVotos.excpetion.jaVotouHojeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


@Service
public class VotosService {


    @Autowired
    private SistemaDeVotosRepository sistemaDeVotosRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private VotoRepositoryImpl votoRepository;
    


    public List<TotalDeVotos> listaDeVotos() {
        return votoRepository.TotalVotosDosRestaurante();

    }

    private boolean verificaFuncVoto(Funcionario funcionario, Calendar data)  {
        return votoRepository.VotosPorDia(funcionario.getId(), data);

    }

    private Restaurante cadRestaurante(String nomeRestaurante) {
       return Optional.ofNullable(restauranteRepository.findByNomeRestaurante(nomeRestaurante))
               .orElseGet(() -> {
                   Restaurante novoRestaurante = new Restaurante();
                   novoRestaurante.setNomeRestaurante(nomeRestaurante);
                   return restauranteRepository.save(novoRestaurante);
               });
    }
    private Funcionario cadFuncionario(String nomeFuncionario) {
       return Optional.ofNullable(funcionarioRepository.findByNomeFuncionario(nomeFuncionario))
               .orElseGet(() -> {
                   Funcionario novoFunc = new Funcionario();
                   novoFunc.setNomeFuncionario(nomeFuncionario);
                  return funcionarioRepository.save(novoFunc);
               });
    }

private Calendar dataAtual(){
    Calendar calendar = Calendar.getInstance();
    return calendar;
}

public void inserirVoto(String nomeFuncionario, String nomeRestaurante) throws jaVotouHojeException {

    if (nomeFuncionario == null || nomeFuncionario.trim().isEmpty()) {
        throw new IllegalArgumentException("Nome do funcionário não pode ser vazio");
    }

    if (nomeRestaurante == null || nomeRestaurante.trim().isEmpty()) {
        throw new IllegalArgumentException("Nome do restaurante não pode ser vazio");
    }

    Calendar dataAtual = dataAtual();

    Funcionario funcionario = cadFuncionario(nomeFuncionario);

    if(verificaFuncVoto(funcionario, dataAtual)){
        throw new jaVotouHojeException("Ja votou hoje pai");
    }

    Restaurante restaurante = cadRestaurante(nomeRestaurante);

    Votos novoVoto = new Votos();
    novoVoto.setFuncionario(funcionario);
    novoVoto.setRestaurante(restaurante);
    novoVoto.setData(dataAtual);

    sistemaDeVotosRepository.save(novoVoto);
}
}





