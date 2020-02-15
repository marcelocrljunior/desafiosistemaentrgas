package com.desafio.desafioentregas.controllers;


import com.desafio.desafioentregas.BO.MalhaBO;
import com.desafio.desafioentregas.DAO.MalhaDAO;
import com.desafio.desafioentregas.erros.MalhaServerErrorException;
import com.desafio.desafioentregas.models.Malha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class que representa a controller Controle Lançamentos
 * @author marceloc.r.lopesjunior
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MalhaController {


    /**
     * Método get que retorna um objeto Malha
     * @return Objeto Malha
     */
    @GetMapping("/malha/{guid}")
    public Malha get(@PathVariable("guid") String guid){

        try
        {
            MalhaBO malhabo = new MalhaBO();
            return malhabo.get(guid);

        }
        catch (Exception ex) {
            throw new MalhaServerErrorException();
        }
    }

    /**
     * Método get que retorna uma lista de objetos Malha
     * @return Objeto List Malha
     */
    @GetMapping("/malhas")
    public List<Malha> get(){

        try
        {
            MalhaBO malhabo = new MalhaBO();
            return malhabo.getMalhas();

        }
        catch (Exception ex) {
            throw new MalhaServerErrorException();
        }
    }

    /**
     * Método que cria uma nova malha
     * @param malha
     * @return
     */
    @PostMapping("/malha")
    public void post(@RequestBody Malha malha) {

        try
        {
            MalhaBO malhabo = new MalhaBO();
            malhabo.post(malha);

        }
        catch (Exception ex)
        {
            throw new MalhaServerErrorException();

        }
    }

    /**
     * Método que atualiza uma malha
     * @param malha
     * @return
     */
    @PutMapping("/malha")
    public void put(@RequestBody Malha malha) {

        try
        {
            MalhaBO malhabo = new MalhaBO();
            malhabo.update(malha);

        }
        catch (Exception ex)
        {
            throw new MalhaServerErrorException();

        }
    }

    /**
     * Método que remove uma malha
     * @param guid
     * @return
     */
    @DeleteMapping("/malha/{guid}")
    public void delete(@PathVariable("guid") String guid) {

        try
        {
            Malha malha = new Malha();
            malha.setGuid(guid);
            MalhaBO malhabo = new MalhaBO();
            malhabo.delete(malha);

        }
        catch (Exception ex)
        {
            throw new MalhaServerErrorException();

        }
    }
}
