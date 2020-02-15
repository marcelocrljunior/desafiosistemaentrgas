package com.desafio.desafioentregas.BO;

import com.desafio.desafioentregas.DAO.MalhaDAO;
import com.desafio.desafioentregas.models.EntregaRequest;
import com.desafio.desafioentregas.models.EntregaResponse;
import com.desafio.desafioentregas.models.Malha;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Classe que representa a camada de negócio dos objetos de Entrega
 * @author marceloc.r.lopesjunior
 */
public class MalhaBO {

    private MalhaDAO malhadao = new MalhaDAO();

    public List<Malha> getMalhas() throws Exception {
        return malhadao.getAll();
    }

    public Malha get(String guid) throws IOException {
        return malhadao.get(guid).orElse(new Malha());
    }

    public void post(Malha malha) throws Exception {
        malhadao.save(malha);
    }

    public void update(Malha malha) throws IOException {
        malhadao.update(malha);
    }

    public void delete(Malha malha) throws IOException{
        malhadao.delete(malha);
    }

}
