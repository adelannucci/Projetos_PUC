/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.model.dao.table.Anuncio;
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
public class AnuncioDAO extends DAO<Anuncio> {

    @Override
    public Anuncio findById(int id) {
        Anuncio anc = null;

        try {
            UsuarioDAO usu = new UsuarioDAO();
            TrajetoDAO tra = new TrajetoDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Anuncio.NOME_TABLE + " where " + Anuncio.TABLE_ID + " = " + id + " ");

            while (rs.next()) {
                int id_anc = rs.getInt(Anuncio.TABLE_ID);
                int qtd = rs.getInt(Anuncio.TABLE_QTD_ANC);
                int id_usu = rs.getInt(Anuncio.TABLE_ID_USU);
                int id_tra = rs.getInt(Anuncio.TABLE_ID_TRA);
                anc = new Anuncio(id_anc, qtd, usu.findById(id_usu), tra.findById(id_tra));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return anc;
    }

    @Override
    public boolean update(Anuncio obj) {
        boolean out = false;
        try {
            bd = new BD();
            Anuncio anc = obj;
            String qry = "UPDATE " + Anuncio.NOME_TABLE
                    + " set "      + Anuncio.TABLE_QTD_ANC + " = " + anc.getQuantidade()
                    + ","          + Anuncio.TABLE_ID_USU  + " = " + anc.getUsuario().getId()
                    + ","          + Anuncio.TABLE_ID_TRA  + " = " + anc.getTrajeto().getId()
                    + " where "    + Anuncio.TABLE_ID      + " = " + anc.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Anuncio> findAll() {
        List<Anuncio> l = new ArrayList<>();
        try {
            UsuarioDAO usu = new UsuarioDAO();
            TrajetoDAO tra = new TrajetoDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Anuncio.NOME_TABLE);

            while (rs.next()) {
                int id_anc = rs.getInt(Anuncio.TABLE_ID);
                int qtd = rs.getInt(Anuncio.TABLE_QTD_ANC);
                int id_usu = rs.getInt(Anuncio.TABLE_ID_USU);
                int id_tra = rs.getInt(Anuncio.TABLE_ID_TRA);
                Anuncio anc = new Anuncio(id_anc, qtd, usu.findById(id_usu), tra.findById(id_tra));
                l.add(anc);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Anuncio obj) {
        boolean out = false;
        try {
            Anuncio anc = obj;
            bd = new BD();
            int id = super.findEnd(Anuncio.NOME_TABLE, Anuncio.TABLE_ID);
            anc.setId(id + 1);
            String qry = "INSERT INTO " + Anuncio.NOME_TABLE
                    + "( " + Anuncio.TABLE_ID
                    + ", " + Anuncio.TABLE_QTD_ANC
                    + ", " + Anuncio.TABLE_ID_USU
                    + ", " + Anuncio.TABLE_ID_TRA
                    + " ) VALUES (" + anc.getId() + "," + anc.getQuantidade() + "," + anc.getUsuario().getId() + "," + anc.getTrajeto().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Anuncio.NOME_TABLE + " WHERE " + Anuncio.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
}
