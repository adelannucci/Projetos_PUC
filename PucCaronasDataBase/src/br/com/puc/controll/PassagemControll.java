/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.AnuncioDAO;
import br.com.puc.model.dao.PassagemDAO;
import br.com.puc.model.dao.UsuarioDAO;
import br.com.puc.model.dao.table.Passagem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class PassagemControll extends Controll<Passagem> {

    public PassagemControll() {
        dao = new PassagemDAO();
        this.lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Passagem e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Passagem pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Passagem obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Passagem obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Passagem> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public Passagem buscarPorAnuncioPsgDisponivel(int idAnuncio) {
        List<Passagem> psgs = buscarPorAnuncio(idAnuncio);
        Passagem out = null;
        for (Passagem psg : psgs) {
            if (psg.getAnuncio().getUsuario().getId() == psg.getUsuario().getId()) {
                out = psg;
                break;
            }
        }

        return out;
    }

    public List<Passagem> buscarPorAnuncio(int idAnuncio) {

        List<Passagem> out = new ArrayList<Passagem>();

        String qry = "SELECT * FROM "
                + Passagem.NOME_TABLE + " WHERE "
                + Passagem.TABLE_ID_ANC + " = "
                + idAnuncio;

        try {
            ResultSet rs = dao.query(qry);
            while (rs.next()) {
                int id = rs.getInt(Passagem.TABLE_ID);
                int idUsu = rs.getInt(Passagem.TABLE_ID_USU);
                double preco = rs.getDouble(Passagem.TABLE_PRECO_PSG);
                AnuncioDAO anc = new AnuncioDAO();
                UsuarioDAO usu = new UsuarioDAO();
                out.add(new Passagem(id, preco, anc.findById(idAnuncio), usu.findById(idUsu)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioControll.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }
    
    public boolean isPassagemForAnuncioAndUsuario(int idAnuncio, int idUsu) {

        boolean out = false;

        String qry = "SELECT " + Passagem.TABLE_ID + " FROM "
                + Passagem.NOME_TABLE + " WHERE "
                + Passagem.TABLE_ID_ANC + " = "
                + idAnuncio + " AND " + Passagem.TABLE_ID_USU + " = "
                + idUsu;

        try {
            ResultSet rs = dao.query(qry);
            if (rs.next()) {
                out = true;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioControll.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }
    
    public void teste() {
        AnuncioControll anc = new AnuncioControll();
        UsuarioControll usu = new UsuarioControll();
        Passagem obj = new Passagem(-1, 3.5D, anc.pesquisar(1), usu.pesquisar(2));
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setPreco(4.5D);
        this.atualizar(obj);
        this.adicionar(new Passagem(-1, 3.5D, anc.pesquisar(2), usu.pesquisar(2)));
        this.adicionar(new Passagem(-1, 3.5D, anc.pesquisar(1), usu.pesquisar(2)));
        for (Passagem e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Passagem e : buscarTodos()) {
            System.out.println(e);
        }
    }
}
