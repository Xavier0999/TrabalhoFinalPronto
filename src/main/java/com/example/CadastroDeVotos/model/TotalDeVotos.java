package com.example.CadastroDeVotos.model;


public class TotalDeVotos {


private String nomeRestaurante;
private Long totalDeVotos;

    public TotalDeVotos( String nomeRestaurante, Long totalDeVotos) {

        this.nomeRestaurante = nomeRestaurante;
        this.totalDeVotos = totalDeVotos;
    }

    @Override
    public String toString() {
        return "TotalDeVotos{"  +
                ", nomeRestaurante='" + nomeRestaurante + '\'' +
                ", totalDeVotos=" + totalDeVotos +
                '}';
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public Long getTotalDeVotos() {
        return totalDeVotos;
    }

    public void setTotalDeVotos(Long totalDeVotos) {
        this.totalDeVotos = totalDeVotos;
    }
}
