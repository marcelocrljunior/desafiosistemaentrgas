package com.desafio.desafioentregas.models;

import java.util.ArrayList;
import java.util.List;

public class No implements Comparable<No> {

    private String descricao;
    private double distancia;
    private boolean visitado = false;
    private No pai;
    private List<Malha> malhas = new ArrayList<Malha>();
    private List<No> vizinhos = new ArrayList<No>();

    public void setDescricao(String nome){

        this.descricao = nome;
    }

    public String getDescricao(){

        return descricao;

    }

    public void visitar (){

        this.visitado = true;
    }

    public boolean verificarVisita(){

        return visitado;
    }

    public void setDistancia(double distancia){

        this.distancia = distancia;
    }

    public double getDistancia(){

        return this.distancia;
    }

    public void setPai(No pai){

        this.pai = pai;
    }

    public No getPai(){

        return this.pai;
    }

    public void setVizinhos(List<No> vizinhos) {

        this.vizinhos.addAll(vizinhos);

    }

    public List<No> getVizinhos(){

        return this.vizinhos;
    }

    public void setMalhas(List <Malha> arestas){

        this.malhas.addAll(arestas);

    }

    public List<Malha> getMalhas() {

        return malhas;
    }

    public int compareTo(No no) {
        if(this.getDistancia() < no.getDistancia()) return -1;
        else if(this.getDistancia() == no.getDistancia()) return 0;

        return 1;


    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof No){
            No vRef = (No) obj;
            if(this.getDescricao().equals(vRef.getDescricao())) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = " ";
        s+= this.getDescricao();
        return s;
    }

}
