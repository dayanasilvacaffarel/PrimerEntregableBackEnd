package com.company.Main.dao;

import java.util.List;

public interface IDao <T> {

    public T registrar(T t);
    public T modificar (T t);
    public T buscar (String id);
    public void eliminar(String id);
    public List<T> listarTodos();

}
