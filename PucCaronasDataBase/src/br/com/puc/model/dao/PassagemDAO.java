/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.controll.UsuarioControll;
import br.com.puc.model.dao.table.Passagem;
import br.com.puc.tools.database.BD;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class PassagemDAO extends DAO<Passagem> {
    
    @Override
    public Passagem findById(int id) {
        Passagem psg = null;
        try {
            AnuncioDAO anc = new AnuncioDAO();
            UsuarioDAO usu = new UsuarioDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Passagem.NOME_TABLE + " where " + Passagem.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                int id_psg = rs.getInt(Passagem.TABLE_ID);
                double preco = rs.getDouble(Passagem.TABLE_PRECO_PSG);
                int id_anc = rs.getInt(Passagem.TABLE_ID_ANC);
                int id_usu = rs.getInt(Passagem.TABLE_ID_USU);
                psg =  new Passagem(id_psg, preco, anc.findById(id_anc), usu.findById(id_usu));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return psg;
    }

    @Override
    public boolean update(Passagem obj) {
        boolean out = false;
        try {
            bd = new BD();
            Passagem psg = obj;
            String qry = "UPDATE " + Passagem.NOME_TABLE      + 
                    " set "        + Passagem.TABLE_PRECO_PSG + " = " + psg.getPreco()            + 
                    ","            + Passagem.TABLE_ID_ANC    + " = " + psg.getAnuncio().getId()  + 
                    ","            + Passagem.TABLE_ID_USU    + " = " + psg.getUsuario().getId()  + 
                    " where "      + Passagem.TABLE_ID        + " = " + psg.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Passagem> findAll() {
        List<Passagem> l = new ArrayList<>();
        try {
            AnuncioDAO anc = new AnuncioDAO();
            UsuarioDAO usu = new UsuarioDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Passagem.NOME_TABLE);

            while (rs.next()) {
                int id_psg = rs.getInt(Passagem.TABLE_ID);
                double preco = rs.getDouble(Passagem.TABLE_PRECO_PSG);
                int id_anc = rs.getInt(Passagem.TABLE_ID_ANC);
                int id_usu = rs.getInt(Passagem.TABLE_ID_USU);
                Passagem psg =  new Passagem(id_psg, preco, anc.findById(id_anc), usu.findById(id_usu));
                l.add(psg);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Passagem obj) {
       boolean out = false;
        try {
            Passagem psg = obj;
            bd = new BD();
            int id = super.findEnd(Passagem.NOME_TABLE, Passagem.TABLE_ID);
            psg.setId(id + 1);
            String qry = "INSERT INTO " + Passagem.NOME_TABLE      + 
                    "( "                + Passagem.TABLE_ID        +
                    ", "                + Passagem.TABLE_PRECO_PSG + 
                    ", "                + Passagem.TABLE_ID_ANC    + 
                    ", "                + Passagem.TABLE_ID_USU    + 
                    " ) VALUES (" + psg.getId() + "," + psg.getPreco() + "," + psg.getAnuncio().getId() + "," + psg.getUsuario().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Passagem.NOME_TABLE + " WHERE " + Passagem.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
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
            bd = new BD();
            ResultSet rs = bd.execConsulta(qry);
            while (rs.next()) {
                int id = rs.getInt(Passagem.TABLE_ID);
                int idUsu = rs.getInt(Passagem.TABLE_ID_USU);
                double preco = rs.getDouble(Passagem.TABLE_PRECO_PSG);
                AnuncioDAO anc = new AnuncioDAO();
                UsuarioDAO usu = new UsuarioDAO();
                out.add(new Passagem(id, preco, anc.findById(idAnuncio), usu.findById(idUsu)));
            }
            rs.close();
            bd.fechar();
        } catch (Exception ex) {
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
            bd = new BD();
            ResultSet rs = bd.execConsulta(qry);
            if (rs.next()) {
                out = true;
            }
            rs.close();
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControll.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }
}
