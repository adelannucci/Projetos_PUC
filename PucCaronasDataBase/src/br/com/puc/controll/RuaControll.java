/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.RuaDAO;
import br.com.puc.model.dao.table.Rua;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class RuaControll extends Controll<Rua> {

    public RuaControll()
    {
        dao = new RuaDAO();
        this.lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Rua e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Rua pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Rua obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Rua obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Rua> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public Rua buscarPorNomeRua(String nome) {
        for(Rua e : lista)
        {
            if(e.getNome().equals(nome)) {
                return e;
            }
        }
        return null;
    }
    
    public Rua buscarPorCep(String cep) {
        for(Rua e : lista)
        {
            if(e.getCep().equals(cep)) {
                return e;
            }
        }
        return null;
    }
    
    public Rua buscarPorNomeRuaOuCep(String nome, String cep) {
        for(Rua e : lista)
        {
            if(e.getNome().equals(nome) || e.getCep().equals(cep) ) {
                return e;
            }
        }
        return null;
    }
    
    public void teste()
    {
        BairroContoll bai = new BairroContoll();
        Rua obj = new Rua(-1, "Engenheiro Carlos Steverson", "13092132", bai.pesquisar(1)) ;
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setNome("Avenida Engenheiro Carlos Steverson");
        this.atualizar(obj);
        this.adicionar(new Rua(-1, "Rua Monsenhor Manoel Corrêa Macedo", "13041610", bai.pesquisar(2)));
        this.adicionar(new Rua(-1, "Hermas Bragassss", "13092142", bai.pesquisar(1)));
        for (Rua e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Rua e : buscarTodos()) {
            System.out.println(e);
        }
    }
        
}
