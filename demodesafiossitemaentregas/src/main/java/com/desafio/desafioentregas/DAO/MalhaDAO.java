package com.desafio.desafioentregas.DAO;

import com.desafio.desafioentregas.interfaces.Dao;
import com.desafio.desafioentregas.models.Malha;
import com.google.common.io.CharStreams;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe que representa a camada de acesso aos dados do legado de lançamentos
 * @author marceloc.r.lopesjunior
 */
public class MalhaDAO implements Dao<Malha> {

    @Override
    public Optional<Malha> get(String guid) throws IOException {

        List<Malha> listamalhas = getAll();

        if(listamalhas == null)
            listamalhas = new ArrayList<>();

        Malha retorno = null;
        for(Malha malha: listamalhas)
        {
            if(malha.getGuid().equalsIgnoreCase(guid)) {
                retorno = malha;
                break;
            }

        }
        return Optional.ofNullable(retorno);
    }


    @Override
    public List<Malha> getAll() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type malhaListType = new TypeToken<ArrayList<Malha>>(){}.getType();
        List<Malha> malhas = gson.fromJson(getStrJsonArquivoMalha(), malhaListType);
        return malhas;
    }

    @Override
    public void save(Malha malha) throws Exception {

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        malha.setGuid(randomUUIDString);
        List<Malha> listamalhas = getAll();

        if(listamalhas == null)
            listamalhas = new ArrayList<>();

        boolean flagregistrou = true;
        for(Malha _malha: listamalhas)
        {
            if(_malha.getGuid().equalsIgnoreCase(malha.getGuid())) {
                flagregistrou = false;
                break;
            }

        }

        if(!flagregistrou)
            save(malha);
        else {
            listamalhas.add(malha);
            setStrJsonArquivoMalha(getStrObjetoMalhasJson(listamalhas));
        }

    }

    @Override
    public void update(Malha malha) throws IOException {

        List<Malha> listamalhas = getAll();

        if(listamalhas == null)
            listamalhas = new ArrayList<>();

        Malha malha_aux = null;
        for(Malha _malha: listamalhas)
        {
            if(_malha.getGuid().equalsIgnoreCase(malha.getGuid())) {
                malha_aux = _malha;
                break;
            }

        }

        if(malha_aux != null)
        {
            listamalhas.remove(malha_aux);
            listamalhas.add(malha);
            setStrJsonArquivoMalha(getStrObjetoMalhasJson(listamalhas));
        }
    }

    @Override
    public void delete(Malha malha) throws IOException {

        List<Malha> listamalhas = getAll();

        Malha malha_aux = null;
        for(Malha _malha: listamalhas)
        {
            if(_malha.getGuid().equalsIgnoreCase(malha.getGuid())) {
                malha_aux = _malha;
                break;
            }

        }

        if(listamalhas == null)
            listamalhas = new ArrayList<>();

        if(malha_aux != null) {
            listamalhas.remove(malha_aux);
            setStrJsonArquivoMalha(getStrObjetoMalhasJson(listamalhas));
        }
    }

    private String getStrJsonArquivoMalha() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("dados/malha.json");
        String retorno = null;
        try (final Reader reader = new InputStreamReader(inputStream)) {
            retorno = CharStreams.toString(reader);
        }
        return retorno;
    }

    private void setStrJsonArquivoMalha(String listmalhasjson) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        PrintWriter writer = new PrintWriter(new File(classLoader.getResource("dados/malha.json").getPath()));
        writer.print("");
        writer.print(listmalhasjson);
        writer.close();
    }

    private String getStrObjetoMalhasJson(List<Malha> malhas)
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type malhaListType = new TypeToken<ArrayList<Malha>>(){}.getType();
        return gson.toJson(malhas, malhaListType);

    }
}
