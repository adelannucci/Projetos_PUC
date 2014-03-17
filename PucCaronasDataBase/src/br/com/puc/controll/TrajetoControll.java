/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.TrajetoDAO;
import br.com.puc.model.dao.table.Destino;
import br.com.puc.model.dao.table.Origem;
import br.com.puc.model.dao.table.Trajeto;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class TrajetoControll extends Controll<Trajeto>{

    public TrajetoControll()
    {
        dao = new TrajetoDAO();
        this.lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Trajeto e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Trajeto pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Trajeto obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Trajeto obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Trajeto> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public void teste()
    {
        OrigemControll org = new OrigemControll();
        DestinoControll des = new DestinoControll();
        Trajeto obj = new Trajeto(-1, des.pesquisar(2), org.pesquisar(2));
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setDestino(des.pesquisar(1));
        this.atualizar(obj);
        this.adicionar(new Trajeto(-1, des.pesquisar(2), org.pesquisar(1)));
        this.adicionar(new Trajeto(-1, des.pesquisar(1), org.pesquisar(2)));
        for (Trajeto e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Trajeto e : buscarTodos()) {
            System.out.println(e);
        }
    }    
}
