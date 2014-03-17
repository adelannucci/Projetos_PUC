/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.PerfilDAO;
import br.com.puc.model.dao.table.Perfil;
import java.util.List;

/**
 *
 * @author 12647202
 */
public class PerfilControll extends Controll<Perfil> {

    public PerfilControll()
    {
        dao = new PerfilDAO();
        lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
       boolean out = dao.delete(id);
        if (out) {
            for (Perfil e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Perfil pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Perfil obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Perfil obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Perfil> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public void teste()
    {
        this.adicionar(new Perfil(1));
        this.adicionar(new Perfil(3));
        Perfil p = this.pesquisar(2);
        p.setTipo(2);
        this.atualizar(p);
        this.adicionar(new Perfil(3));
        for(Perfil e : buscarTodos())
        {
            System.out.println(e);
        }
        this.excluir(3);
        for(Perfil e : buscarTodos())
        {
            System.out.println(e);
        }
    }
    
}
