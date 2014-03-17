/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.BairroDAO;
import br.com.puc.model.dao.table.Bairro;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class BairroContoll extends Controll<Bairro> {

    public BairroContoll()
    {
        dao = new BairroDAO();
        this.lista = dao.findAll();
    }
    
    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Bairro e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Bairro pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Bairro obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Bairro obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Bairro> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }
    
    public Bairro buscarPorNomeBairro(String nome) {
        for(Bairro e : lista)
        {
            if(e.getNomeBai().equals(nome)) {
                return e;
            }
        }
        return null;
    }
    
    public void teste()
    {
        CidadeControll crt = new CidadeControll();
        Bairro bai = new Bairro(-1, "Nova Campina", crt.pesquisar(1));
        this.adicionar(bai);
        bai = this.pesquisar(1);
        bai.setNomeBai("Nova Campinas");
        this.atualizar(bai);
        this.adicionar(new Bairro(-1, "Ponte Preta", crt.pesquisar(1)));
        this.adicionar(new Bairro(-1, "xas", crt.pesquisar(2)));
        for (Bairro e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Bairro e : buscarTodos()) {
            System.out.println(e);
        }
    }
    
}
