/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.EstadoDAO;
import br.com.puc.model.dao.table.Estado;
import java.util.List;

/**
 *
 * @author 12647202
 */
public class EstadoControll extends Controll<Estado> {

    public EstadoControll()
    {
        dao = new EstadoDAO();
        this.lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Estado e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Estado pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Estado obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Estado obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Estado> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public Estado buscarPorNomeEstado(String nome) {
        for(Estado e : lista)
        {
            if(e.getNomeEstado().equals(nome))
                return e;
        }
        return null;
    }
    
    public void teste()
    {
        Estado est = new Estado("São Paul", "SP");
        this.adicionar(est);
        est = this.pesquisar(1);
        est.setNomeEstado("São Paulo");
        this.atualizar(est);
        this.adicionar(new Estado("Minas Gerais", "MG"));
        this.adicionar(new Estado("xxx", "xx"));
        for (Estado e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Estado e : buscarTodos()) {
            System.out.println(e);
        }
    }
}
