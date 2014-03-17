/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Estado;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class EstadoDAO extends DAO<Estado> {

    @Override
    public Estado findById(int id) {
        Estado est = null;

        try {
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Estado.NOME_TABLE + " where " + Estado.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                est = new Estado(rs.getInt(Estado.TABLE_ID), rs.getString(Estado.TABLE_NOME_EST), rs.getString(Estado.TABLE_SIGLA_EST));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return est;
    }

    @Override
    public boolean update(Estado obj) {
        boolean out = false;
        try {
            bd = new BD();
            Estado est = obj;
            String qry = "UPDATE " + Estado.NOME_TABLE + " set " + Estado.TABLE_NOME_EST + " = '" + est.getNomeEstado() + "' , " + Estado.TABLE_SIGLA_EST + " ='" + est.getSiglaEstado() + "' where " + Estado.TABLE_ID + " = " + est.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Estado> findAll() {
        List<Estado> l = new ArrayList<>();
        try {
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Estado.NOME_TABLE);

            while (rs.next()) {
                Estado est = new Estado(rs.getInt(Estado.TABLE_ID), rs.getString(Estado.TABLE_NOME_EST), rs.getString(Estado.TABLE_SIGLA_EST));
                l.add(est);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Estado obj) {
       boolean out = false;
        try {
            Estado est = obj;
            bd = new BD();
            int id = super.findEnd(Estado.NOME_TABLE, Estado.TABLE_ID);
            est.setId(id + 1);
            String qry = "INSERT INTO " + Estado.NOME_TABLE + "(" + Estado.TABLE_ID + "," + Estado.TABLE_NOME_EST + "," + Estado.TABLE_SIGLA_EST + ") VALUES (" + est.getId() + ",'" + est.getNomeEstado() + "','" + est.getSiglaEstado() + "')";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Estado.NOME_TABLE + " WHERE " + Estado.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
    }
}
