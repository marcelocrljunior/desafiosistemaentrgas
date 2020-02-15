package com.desafio.desafioentregas.erros;

/**
 * Classe que representa um erro interno de servidor para o objeto Malha
 * @author marceloc.r.lopesjunior
 */
public class MalhaServerErrorException extends RuntimeException {

    public MalhaServerErrorException() {
        super("Algum erro ocorreu no processamento da requisição");
    }

}
