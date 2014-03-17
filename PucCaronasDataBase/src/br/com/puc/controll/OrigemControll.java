/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.OrigemDAO;
import br.com.puc.model.dao.table.Origem;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class OrigemControll extends Controll<Origem> {

    public OrigemControll()
    {
        dao = new OrigemDAO();
        this.lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Origem e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Origem pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Origem obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Origem obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Origem> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public void teste()
    {
        EnderecoControll end = new EnderecoControll();
        Origem obj = new Origem(-1, "17/12/2013", "16:25", end.pesquisar(1));
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setHora("16:30");
        this.atualizar(obj);
        this.adicionar(new Origem(-1, "18/12/2013", "16:25", end.pesquisar(2)));
        this.adicionar(new Origem(-1, "19/12/2013", "16:25", end.pesquisar(2)));
        for (Origem e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Origem e : buscarTodos()) {
            System.out.println(e);
        }
    }    
}
