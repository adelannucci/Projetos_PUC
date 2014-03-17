/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Trajeto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class TrajetoDAO extends DAO<Trajeto> {
    
    @Override
    public Trajeto findById(int id) {
        Trajeto tra = null;

        try {
            bd = new BD();
            DestinoDAO des = new DestinoDAO();
            OrigemDAO org = new OrigemDAO();
            
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Trajeto.NOME_TABLE + " where " + Trajeto.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                tra = new Trajeto(rs.getInt(Trajeto.TABLE_ID), des.findById(rs.getInt(Trajeto.TABLE_ID_DES)), org.findById(rs.getInt(Trajeto.TABLE_ID_ORG)));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(TrajetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tra;
    }

    @Override
    public boolean update(Trajeto obj) {
        boolean out = false;
        try {
            bd = new BD();
            Trajeto org = obj;
            String qry = "UPDATE " + Trajeto.NOME_TABLE   + 
                    " set "        + Trajeto.TABLE_ID_DES + " = " + org.getDestino().getId() + 
                    ","            + Trajeto.TABLE_ID_ORG + " = " + org.getOrigem().getId()  + 
                    " where "      + Trajeto.TABLE_ID     + " = "  + org.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(TrajetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Trajeto> findAll() {
        List<Trajeto> l = new ArrayList<>();
        try {
            bd = new BD();
            DestinoDAO des = new DestinoDAO();
            OrigemDAO org = new OrigemDAO();
            
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Trajeto.NOME_TABLE);

            while (rs.next()) {
                Trajeto tra = new Trajeto(rs.getInt(Trajeto.TABLE_ID), des.findById(rs.getInt(Trajeto.TABLE_ID_DES)), org.findById(rs.getInt(Trajeto.TABLE_ID_ORG)));
                l.add(tra);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(TrajetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Trajeto obj) {
       boolean out = false;
        try {
            Trajeto org = obj;
            bd = new BD();
            int id = super.findEnd(Trajeto.NOME_TABLE, Trajeto.TABLE_ID);
            org.setId(id + 1);
            String qry = "INSERT INTO " + Trajeto.NOME_TABLE   + 
                    "( "                + Trajeto.TABLE_ID     +
                    ", "                + Trajeto.TABLE_ID_DES + 
                    ", "                + Trajeto.TABLE_ID_ORG + 
                    " ) VALUES (" + org.getId() + "," + org.getDestino().getId() + "," + org.getOrigem().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(TrajetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Trajeto.NOME_TABLE + " WHERE " + Trajeto.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(TrajetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
    }   
}
