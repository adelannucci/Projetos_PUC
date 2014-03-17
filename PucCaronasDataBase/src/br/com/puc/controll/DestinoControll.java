/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.DestinoDAO;
import br.com.puc.model.dao.table.Destino;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class DestinoControll extends Controll<Destino> {
    public DestinoControll()
    {
        dao = new DestinoDAO();
        this.lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Destino e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Destino pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Destino obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Destino obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Destino> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public void teste()
    {
        EnderecoControll end = new EnderecoControll();
        Destino obj = new Destino(-1, "17/12/2013", "16:25", end.pesquisar(1));
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setHora("16:30");
        this.atualizar(obj);
        this.adicionar(new Destino(-1, "18/12/2013", "16:30", end.pesquisar(2)));
        this.adicionar(new Destino(-1, "19/12/2013", "16:20", end.pesquisar(1)));
        for (Destino e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Destino e : buscarTodos()) {
            System.out.println(e);
        }
    }    
}
