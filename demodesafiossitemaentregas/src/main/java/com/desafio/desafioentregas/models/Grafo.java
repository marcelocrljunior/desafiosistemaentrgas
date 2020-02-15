package com.desafio.desafioentregas.models;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<No> grafo = new ArrayList<No>();

    public void setVertices(List<No> nos) {

        this.grafo.addAll(nos);
    }

    public void adicionarNo(No novoNo) {

        this.grafo.add(novoNo);
    }

    public List<No> getNos() {

        return this.grafo;
    }

    public No encontrarNo(String nome) {

        for (int i = 0; i < this.getNos().size(); i++) {

            if (nome.equalsIgnoreCase(this.getNos().get(i).getDescricao())) {

                return this.getNos().get(i);

            }
        }

        return null;

    }

}
