/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Cidade;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class CidadeDAO extends DAO<Cidade> {

    @Override
    public Cidade findById(int id) {
        Cidade cid = null;
        try {
            bd = new BD();
            EstadoDAO est = new EstadoDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Cidade.NOME_TABLE + " where " + Cidade.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                cid = new Cidade(rs.getInt(Cidade.TABLE_ID), rs.getString(Cidade.TABLE_NOME_CID), est.findById(rs.getInt(Cidade.TABLE_ID_EST)));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cid;


    }

    @Override
    public boolean update(Cidade obj) {
        boolean out = false;
        try {
            bd = new BD();
            Cidade cid = obj;
            String qry = "UPDATE " + Cidade.NOME_TABLE + " set " + Cidade.TABLE_NOME_CID + " = '" + cid.getNomeCidade() + "' , " + Cidade.TABLE_ID_EST + " = " + cid.getEstado().getId() + " where " + Cidade.TABLE_ID + " = " + cid.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Cidade> findAll() {
        List<Cidade> l = new ArrayList<>();
        try {
            bd = new BD();
            EstadoDAO est = new EstadoDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Cidade.NOME_TABLE);

            while (rs.next()) {
                Cidade cid = new Cidade(rs.getInt(Cidade.TABLE_ID), rs.getString(Cidade.TABLE_NOME_CID), est.findById(rs.getInt(Cidade.TABLE_ID_EST)));
                l.add(cid);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;

    }

    @Override
    public boolean insert(Cidade obj) {
        boolean out  = false;
        try {        
            Cidade cid = obj;
            bd = new BD();
            int id = super.findEnd(Cidade.NOME_TABLE, Cidade.TABLE_ID);
            cid.setId(id + 1);
            String qry = "INSERT INTO " + Cidade.NOME_TABLE + "(" + Cidade.TABLE_ID + "," + Cidade.TABLE_NOME_CID + "," + Cidade.TABLE_ID_EST + ") VALUES (" + cid.getId() + ",'" + cid.getNomeCidade() + "'," + cid.getEstado().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;

    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Cidade.NOME_TABLE + " WHERE " + Cidade.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
}
