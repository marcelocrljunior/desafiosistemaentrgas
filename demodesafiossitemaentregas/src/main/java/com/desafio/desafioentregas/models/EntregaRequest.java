package com.desafio.desafioentregas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa o modelo Entrega Request
 * @author marceloc.r.lopesjunior
 */
public class EntregaRequest {

    @SerializedName("origem")
    @Expose
    private String origem;
    @SerializedName("destino")
    @Expose
    private String destino;
    @SerializedName("autonomia")
    @Expose
    private double autonomia;
    @SerializedName("valordolitro")
    @Expose
    private double valordolitro;

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

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public double getValordolitro() {
        return valordolitro;
    }

    public void setValordolitro(double valordolitro) {
        this.valordolitro = valordolitro;
    }

}