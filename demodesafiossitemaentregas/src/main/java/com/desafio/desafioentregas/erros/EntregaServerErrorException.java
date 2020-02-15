package com.desafio.desafioentregas.erros;

/**
 * Classe que representa um erro interno de servidor para o objeto Entrega
 * @author marceloc.r.lopesjunior
 */
public class EntregaServerErrorException extends RuntimeException {

    public EntregaServerErrorException() {
        super("Algum erro ocorreu no processamento da requisição");
    }

}
