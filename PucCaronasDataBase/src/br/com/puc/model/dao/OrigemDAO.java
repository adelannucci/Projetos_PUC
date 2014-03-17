/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Origem;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class OrigemDAO extends DAO<Origem> {
    
    @Override
    public Origem findById(int id) {
        Origem org = null;

        try {
            bd = new BD();
            EnderecoDAO end = new EnderecoDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Origem.NOME_TABLE + " where " + Origem.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                org = new Origem(rs.getInt(Origem.TABLE_ID), rs.getString(Origem.TABLE_DATA_ORG), rs.getString(Origem.TABLE_HORA_ORG) ,end.findById(rs.getInt(Origem.TABLE_ID_END)));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(OrigemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return org;
    }

    @Override
    public boolean update(Origem obj) {
        boolean out = false;
        try {
            bd = new BD();
            Origem org = obj;
            String qry = "UPDATE " + Origem.NOME_TABLE        + 
                    " set "        + Origem.TABLE_DATA_ORG    + " = '" + org.getData()             + 
                    "', "           + Origem.TABLE_HORA_ORG   + " = '" + org.getHora()             + 
                    "', "           + Origem.TABLE_ID_END     + " = "  + org.getEndereco().getId() + 
                    " where "      + Origem.TABLE_ID          + " = "  + org.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(OrigemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Origem> findAll() {
        List<Origem> l = new ArrayList<>();
        try {
            bd = new BD();
            EnderecoDAO end = new EnderecoDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Origem.NOME_TABLE);

            while (rs.next()) {
                Origem org = new Origem(rs.getInt(Origem.TABLE_ID), rs.getString(Origem.TABLE_DATA_ORG), rs.getString(Origem.TABLE_HORA_ORG) ,end.findById(rs.getInt(Origem.TABLE_ID_END)));
                l.add(org);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(OrigemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Origem obj) {
       boolean out = false;
        try {
            Origem org = obj;
            bd = new BD();
            int id = super.findEnd(Origem.NOME_TABLE, Origem.TABLE_ID);
            org.setId(id + 1);
            String qry = "INSERT INTO " + Origem.NOME_TABLE     + 
                    "( "                + Origem.TABLE_ID       +
                    ", "                + Origem.TABLE_DATA_ORG + 
                    ", "                + Origem.TABLE_HORA_ORG + 
                    ", "                + Origem.TABLE_ID_END   + 
                    " ) VALUES (" + org.getId() + ",'" + org.getData() + "','" + org.getHora() + "'," + org.getEndereco().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(OrigemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Origem.NOME_TABLE + " WHERE " + Origem.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(OrigemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
    }
}
