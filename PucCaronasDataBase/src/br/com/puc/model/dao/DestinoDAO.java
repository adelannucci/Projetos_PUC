/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Destino;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class DestinoDAO extends DAO<Destino> {

       @Override
    public Destino findById(int id) {
        Destino org = null;

        try {
            bd = new BD();
            EnderecoDAO end = new EnderecoDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Destino.NOME_TABLE + " where " + Destino.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                org = new Destino(rs.getInt(Destino.TABLE_ID), rs.getString(Destino.TABLE_DATA_DES), rs.getString(Destino.TABLE_HORA_DES) ,end.findById(rs.getInt(Destino.TABLE_ID_END)));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(DestinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return org;
    }

    @Override
    public boolean update(Destino obj) {
        boolean out = false;
        try {
            bd = new BD();
            Destino org = obj;
            String qry = "UPDATE " + Destino.NOME_TABLE       + 
                    " set "        + Destino.TABLE_DATA_DES   + " = '" + org.getData()             + 
                    "', "          + Destino.TABLE_HORA_DES   + " = '" + org.getHora()             + 
                    "', "          + Destino.TABLE_ID_END     + " = "  + org.getEndereco().getId() + 
                    " where "      + Destino.TABLE_ID         + " = "  + org.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(DestinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Destino> findAll() {
        List<Destino> l = new ArrayList<>();
        try {
            bd = new BD();
            EnderecoDAO end = new EnderecoDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Destino.NOME_TABLE);

            while (rs.next()) {
                Destino org = new Destino(rs.getInt(Destino.TABLE_ID), rs.getString(Destino.TABLE_DATA_DES), rs.getString(Destino.TABLE_HORA_DES) ,end.findById(rs.getInt(Destino.TABLE_ID_END)));
                l.add(org);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(DestinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Destino obj) {
       boolean out = false;
        try {
            Destino org = obj;
            bd = new BD();
            int id = super.findEnd(Destino.NOME_TABLE, Destino.TABLE_ID);
            org.setId(id + 1);
            String qry = "INSERT INTO " + Destino.NOME_TABLE     + 
                    "( "                + Destino.TABLE_ID       +
                    ", "                + Destino.TABLE_DATA_DES + 
                    ", "                + Destino.TABLE_HORA_DES + 
                    ", "                + Destino.TABLE_ID_END   + 
                    " ) VALUES (" + org.getId() + ",'" + org.getData() + "','" + org.getHora() + "'," + org.getEndereco().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(DestinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Destino.NOME_TABLE + " WHERE " + Destino.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(DestinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
    }
    
}
