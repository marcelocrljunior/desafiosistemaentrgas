package com.desafio.desafioentregas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Interface Dao para padronizar o acesso ao dados.
 * @author marceloc.r.lopesjunior
 * @param <T>
 */
public interface Dao<T> {

    Optional<T> get(String id) throws IOException;

    List<T> getAll() throws IOException;

    void save(T t) throws Exception;

    void update(T t) throws IOException;

    void delete(T t) throws IOException;
}
