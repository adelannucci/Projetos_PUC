/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.CidadeDAO;
import br.com.puc.model.dao.table.Cidade;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class CidadeControll extends Controll<Cidade> {

    public CidadeControll() {
        dao = new CidadeDAO();
        this.lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Cidade e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Cidade pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Cidade obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Cidade obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Cidade> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public Cidade buscarPorNomeCidade(String nome) {
        for(Cidade e : lista)
        {
            if(e.getNomeCidade().equals(nome)) {
                return e;
            }
        }
        return null;
    }
    
    public void teste()
    {
        EstadoControll ctr = new EstadoControll();
        this.adicionar(new Cidade(-1, "Campina", ctr.pesquisar(1)));
        this.adicionar(new Cidade(-1, "São Paulo", ctr.pesquisar(1)));
        Cidade obj = this.pesquisar(1);
        obj.setNomeCidade("Campinas");
        this.atualizar(obj);
        this.adicionar(new Cidade(-1, "asdfa", ctr.pesquisar(1)));
        
        for(Cidade e : buscarTodos())
        {
            System.out.println(e);
        }
        this.excluir(3);
        
        for(Cidade e : buscarTodos())
        {
            System.out.println(e);
        }
    }
}
