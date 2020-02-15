package com.desafio.desafioentregas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa o modelo Entrega Response
 * @author marceloc.r.lopesjunior
 */
public class EntregaResponse {

    @SerializedName("rota")
    @Expose
    private String rota;
    @SerializedName("custo")
    @Expose
    private double custo;

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

}