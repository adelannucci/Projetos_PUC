/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Bairro;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class BairroDAO extends DAO<Bairro> {

    @Override
    public Bairro findById(int id) {
        Bairro bai = null;
        try {
            bd = new BD();
            CidadeDAO dao = new CidadeDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Bairro.NOME_TABLE + " where " + Bairro.TABLE_ID + " = " + id + " ");

            while (rs.next()) {// mesma coisa da data
                bai = new Bairro(rs.getInt(Bairro.TABLE_ID), rs.getString(Bairro.TABLE_NOME_BAI), dao.findById(rs.getInt(Bairro.TABLE_ID_CID)));
            }
            bd.fechar();
            return bai;
        } catch (Exception ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bai;
    }

    @Override
    public boolean update(Bairro obj) {
        boolean out = false;
        try {
            bd = new BD();
            Bairro bai = obj;
            String qry = "UPDATE " + Bairro.NOME_TABLE + " set " + Bairro.TABLE_NOME_BAI + " = '" + bai.getNomeBai() + "', " + Bairro.TABLE_ID_CID + " = " + bai.getCidade().getId() + " where " + Bairro.TABLE_ID + " = " + bai.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Bairro> findAll() {
        List<Bairro> l = new ArrayList<>();
        try {
            bd = new BD();
            CidadeDAO dao = new CidadeDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Bairro.NOME_TABLE);

            while (rs.next()) {
                Bairro bai = new Bairro(rs.getInt(Bairro.TABLE_ID), rs.getString(Bairro.TABLE_NOME_BAI), dao.findById(rs.getInt(Bairro.TABLE_ID_CID)));
                l.add(bai);
            }

            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Bairro obj) {
        boolean out = false;
        try {
            Bairro bai = obj;
            bd = new BD();
            int id = super.findEnd(Bairro.NOME_TABLE, Bairro.TABLE_ID);
            bai.setId(id + 1);
            String qry = "INSERT INTO " + Bairro.NOME_TABLE + "(" + Bairro.TABLE_ID + "," + Bairro.TABLE_NOME_BAI +","+ Bairro.TABLE_ID_CID + ") VALUES (" + bai.getId() + ",'" + bai.getNomeBai() + "'," + bai.getCidade().getId() + ")";
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
            String qry = "DELETE FROM " + Bairro.NOME_TABLE + " WHERE " + Bairro.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
}
