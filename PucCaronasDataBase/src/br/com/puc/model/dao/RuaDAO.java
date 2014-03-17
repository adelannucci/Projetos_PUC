/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Rua;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class RuaDAO extends DAO<Rua> {
    @Override
    public Rua findById(int id) {
        Rua rua = null;

        try {
            bd = new BD();
            BairroDAO bai = new BairroDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Rua.NOME_TABLE + " where " + Rua.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                rua = new Rua(rs.getInt(Rua.TABLE_ID), rs.getString(Rua.TABLE_NOME_RUA), rs.getString(Rua.TABLE_CEP_RUA), bai.findById(rs.getInt(Rua.TABLE_ID_BAI)));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(RuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rua;
    }

    @Override
    public boolean update(Rua obj) {
        boolean out = false;
        try {
            bd = new BD();
            Rua rua = obj;
            String qry = "UPDATE " + Rua.NOME_TABLE      + 
                    " set "        + Rua.TABLE_NOME_RUA  + " = '" + rua.getNome()          + 
                    "', "          + Rua.TABLE_CEP_RUA   + " = '"   + rua.getCep()         + 
                    "', "          + Rua.TABLE_ID_BAI    + " = " + rua.getBairro().getId() + 
                    " where "      + Rua.TABLE_ID        + " = " + rua.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(RuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Rua> findAll() {
        List<Rua> l = new ArrayList<>();
        try {
            bd = new BD();
            BairroDAO bai = new BairroDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Rua.NOME_TABLE);

            while (rs.next()) {
                Rua rua = new Rua(rs.getInt(Rua.TABLE_ID), rs.getString(Rua.TABLE_NOME_RUA), rs.getString(Rua.TABLE_CEP_RUA), bai.findById(rs.getInt(Rua.TABLE_ID_BAI)));
                l.add(rua);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(RuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Rua obj) {
       boolean out = false;
        try {
            Rua rua = obj;
            bd = new BD();
            int id = super.findEnd(Rua.NOME_TABLE, Rua.TABLE_ID);
            rua.setId(id + 1);
            String qry = "INSERT INTO " + Rua.NOME_TABLE      + 
                    "( "                + Rua.TABLE_ID        + 
                    ", "                + Rua.TABLE_NOME_RUA  + 
                    ", "                + Rua.TABLE_CEP_RUA   +
                    ", "                + Rua.TABLE_ID_BAI    + 
                    " ) VALUES (" + rua.getId()  + ",'" + rua.getNome()           + "','" 
                                  + rua.getCep() + "'," + rua.getBairro().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(RuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Rua.NOME_TABLE + " WHERE " + Rua.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(RuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
    }
}
