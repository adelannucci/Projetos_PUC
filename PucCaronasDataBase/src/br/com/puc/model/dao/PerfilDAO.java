/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Estado;
import br.com.puc.model.dao.table.Perfil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class PerfilDAO extends DAO<Perfil> {

    @Override
    public Perfil findById(int id) {
        Perfil per = null;
        try {
            bd = new BD();
            String qry = "SELECT * FROM " + Perfil.NOME_TABLE + " where " + Perfil.TABLE_ID + " = " + id + " ";
            ResultSet rs = bd.execConsulta(qry);
            while (rs.next()) {// mesma coisa da data
                per = new Perfil(rs.getInt(Perfil.TABLE_ID), rs.getInt(Perfil.TABLE_TIPO_PER));
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return per;
    }

    @Override
    public boolean update(Perfil obj) {
        boolean out = false;
        try {

            bd = new BD();
            Perfil per = obj;
            String qry = "UPDATE " + Perfil.NOME_TABLE + " set " + Perfil.TABLE_TIPO_PER + " = " + per.getTipo() + " where " + Perfil.TABLE_ID + " = " + per.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Perfil> findAll() {
        List<Perfil> l = new ArrayList<>();
        try {
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Perfil.NOME_TABLE);

            while (rs.next()) {
                Perfil per = new Perfil(rs.getInt(Perfil.TABLE_ID), rs.getInt(Perfil.TABLE_TIPO_PER));
                l.add(per);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Perfil obj) {
        boolean out = false;
        try {
            Perfil per = obj;
            bd = new BD();
            int id = super.findEnd(Perfil.NOME_TABLE, Perfil.TABLE_ID);
            per.setId(id + 1);
            String qry = "INSERT INTO " + Perfil.NOME_TABLE + "(" + Perfil.TABLE_ID + "," + Perfil.TABLE_TIPO_PER + ") VALUES (" + per.getId() + "," + per.getTipo() + ")";
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
            String qry = "DELETE FROM " + Perfil.NOME_TABLE + " WHERE " + Perfil.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
}
