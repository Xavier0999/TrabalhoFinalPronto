package com.example.CadastroDeVotos.controller;


import com.example.CadastroDeVotos.excpetion.jaVotouHojeException;
import com.example.CadastroDeVotos.model.Votos;
import com.example.CadastroDeVotos.service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private VotosService votosService;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario() {
        return "formulario";
    }

    @GetMapping
    public String obterLista(Model model) {
        model.addAttribute("votos", votosService.listaDeVotos());
        return "listagem";
    }

    @PostMapping
    public String votar (
                     @RequestParam String nomeFuncionario,
                     @RequestParam String nomeRestaurante) {
        try{
            votosService.inserirVoto(nomeFuncionario, nomeRestaurante);
        }catch (jaVotouHojeException e){
            e.getMessage();
            return "formulario";
        }
    return "redirect:/votos";
}
}