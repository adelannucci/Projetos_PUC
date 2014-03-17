/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.DAO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public abstract class Controll<T> implements Serializable{
    
    protected DAO<T> dao;
    protected List<T> lista;

    public abstract boolean excluir(int id);
    public abstract T pesquisar(int id);
    public abstract boolean adicionar(T obj);
    public abstract boolean atualizar(T obj);
    public abstract List<T> buscarTodos();
    
    public List<T> getLista()
    {
        return lista;
    }
}
