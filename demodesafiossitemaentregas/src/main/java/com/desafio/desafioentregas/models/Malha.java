package com.desafio.desafioentregas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class Malha {

    @SerializedName("origem")
    @Expose
    private String origem;
    @SerializedName("destino")
    @Expose
    private String destino;
    @SerializedName("distancia")
    @Expose
    private double distancia;
    @SerializedName("guid")
    @Expose
    private String guid;
    @JsonIgnore
    private No noorigem;
    @JsonIgnore
    private No nodestino;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


    public No getNoorigem() {
        return noorigem;
    }

    public void setNoorigem(No noorigem) {
        this.noorigem = noorigem;
    }

    public No getNodestino() {
        return nodestino;
    }

    public void setNodestino(No nodestino) {
        this.nodestino = nodestino;
    }



    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

}