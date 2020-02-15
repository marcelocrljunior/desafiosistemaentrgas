package com.desafio.desafioentregas.controllers;


import com.desafio.desafioentregas.BO.EntregaBO;
import com.desafio.desafioentregas.models.EntregaRequest;
import com.desafio.desafioentregas.models.EntregaResponse;
import org.springframework.web.bind.annotation.*;

/**
 * Class que representa a controller Entregas
 * @author marceloc.r.lopesjunior
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EntregaController {


    /**
     * Método que retorna o melhor caminho (custo mais baixo) e o seu custo.
     *
     * @param origem
     * @param destino
     * @param autonomia
     * @param valordolitro
     * @return
     */
    @GetMapping("entrega/melhorcaminho")
    public EntregaResponse getMelhorCaminho(@RequestParam("origem") String origem, @RequestParam("destino") String destino, @RequestParam("autonomia") double autonomia, @RequestParam("valordolitro") double valordolitro) {
        try {
            EntregaRequest entregarequest = new EntregaRequest();
            entregarequest.setOrigem(origem);
            entregarequest.setDestino(destino);
            entregarequest.setAutonomia(autonomia);
            entregarequest.setValordolitro(valordolitro);

            EntregaBO entregabo = new EntregaBO();

            return entregabo.getMelhorCaminho(entregarequest);

        } catch (Exception ex) {
            return null;
        }
    }
}
