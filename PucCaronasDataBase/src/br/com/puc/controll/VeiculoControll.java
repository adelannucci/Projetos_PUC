/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.VeiculoDAO;
import br.com.puc.model.dao.table.Veiculo;
import java.util.List;

/**
 *
 * @author 12647202
 */
public class VeiculoControll extends Controll<Veiculo> {
 
    public VeiculoControll() {
        dao = new VeiculoDAO();
        lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Veiculo e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Veiculo pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Veiculo obj) {
        boolean out = false;
        out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Veiculo obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }
    
    @Override
    public List<Veiculo> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public void teste()
    {
        UsuarioControll ctr = new UsuarioControll();
        Veiculo vel = new Veiculo(-1, "Fiat", "JEE-0042", "Unu", ctr.pesquisar(2));
        this.adicionar(vel);
        vel = this.pesquisar(1);
        vel.setModelo("Uno");
        this.atualizar(vel);
        this.adicionar(new Veiculo(-1, "Fiat", "JEE-4242", "Uno", ctr.pesquisar(2)));
        this.adicionar(new Veiculo(-1, "Fiat", "JEE-0042", "Unu", ctr.pesquisar(1)));
        for (Veiculo e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Veiculo e : buscarTodos()) {
            System.out.println(e);
        }
    }    
}
