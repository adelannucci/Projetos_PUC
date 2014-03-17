/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.AnuncioDAO;
import br.com.puc.model.dao.table.Anuncio;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class AnuncioControll extends Controll<Anuncio> {

    public AnuncioControll() {
        dao = new AnuncioDAO();
        this.lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Anuncio e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Anuncio pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Anuncio obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Anuncio obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Anuncio> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public void teste() {
        UsuarioControll usu = new UsuarioControll();
        TrajetoControll tra = new TrajetoControll();
        Anuncio obj = new Anuncio(-1, 4, usu.pesquisar(2), tra.pesquisar(2));
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setQuantidade(1);
        this.atualizar(obj);
        this.adicionar(new Anuncio(-1, 1, usu.pesquisar(2), tra.pesquisar(2)));
        this.adicionar(new Anuncio(-1, 1, usu.pesquisar(2), tra.pesquisar(1)));
        for (Anuncio e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Anuncio e : buscarTodos()) {
            System.out.println(e);
        }
    }
}
