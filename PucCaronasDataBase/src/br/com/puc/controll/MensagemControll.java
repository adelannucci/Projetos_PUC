/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.MensagemDAO;
import br.com.puc.model.dao.table.Mensagem;
import java.util.List;

/**
 *
 * @author 12647202
 */
public class MensagemControll extends Controll<Mensagem> {
    
    public MensagemControll() {
        dao = new MensagemDAO();
        lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Mensagem e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Mensagem pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Mensagem obj) {
        boolean out = false;
        out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Mensagem obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }
    
    @Override
    public List<Mensagem> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public void teste()
    {
        UsuarioControll ctr = new UsuarioControll();
        AnuncioControll anc = new AnuncioControll();
        Mensagem msg = new Mensagem(-1, "Passa lah?", 2, anc.pesquisar(1), ctr.pesquisar(2));
        this.adicionar(msg);
        msg = this.pesquisar(1);
        msg.setTipo(1);
        this.atualizar(msg);
        this.adicionar(new Mensagem(-1, "nao", 2, anc.pesquisar(1), ctr.pesquisar(1)));
        this.adicionar(new Mensagem(-1, "?", 1, anc.pesquisar(1), ctr.pesquisar(2)));
        for (Mensagem e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Mensagem e : buscarTodos()) {
            System.out.println(e);
        }
    }    
}
