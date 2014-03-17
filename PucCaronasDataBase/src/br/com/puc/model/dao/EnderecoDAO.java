/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Endereco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class EnderecoDAO extends DAO<Endereco> {

    @Override
    public Endereco findById(int id) {
        Endereco end = null;

        try {
            bd = new BD();
            RuaDAO rua = new RuaDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Endereco.NOME_TABLE + " where " + Endereco.TABLE_ID + " = " + id + " ");

            while (rs.next()) {
                int cod = rs.getInt(Endereco.TABLE_ID);
                int num = rs.getInt(Endereco.TABLE_NUM_END);
                double latitude = rs.getDouble(Endereco.TABLE_LATITUDE_END);
                double longitude = rs.getDouble(Endereco.TABLE_LONGITUDE_END);
                int id_rua = rs.getInt(Endereco.TABLE_ID_RUA);     
                end = new Endereco(cod , num, latitude, longitude, rua.findById(id_rua));
            }
            bd.fechar();

        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return end;
    }

    @Override
    public boolean update(Endereco obj) {
        boolean out = false;
        try {
            bd = new BD();
            Endereco end = obj;
            String qry = "UPDATE " + Endereco.NOME_TABLE            + 
                    " set "        + Endereco.TABLE_NUM_END         + " = " + end.getNumero()      + 
                    ", "           + Endereco.TABLE_LATITUDE_END    + " = " + end.getLatitude()    +
                    ", "           + Endereco.TABLE_LONGITUDE_END   + " = " + end.getLongitude()   +
                    ", "           + Endereco.TABLE_ID_RUA          + " = " + end.getRua().getId() + 
                    " where "      + Endereco.TABLE_ID              + " = " + end.getId();
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Endereco> findAll() {
        List<Endereco> l = new ArrayList<>();
        try {
            bd = new BD();
            RuaDAO rua = new RuaDAO();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Endereco.NOME_TABLE);

            while (rs.next()) {
                int cod = rs.getInt(Endereco.TABLE_ID);
                int num = rs.getInt(Endereco.TABLE_NUM_END);
                double latitude = rs.getDouble(Endereco.TABLE_LATITUDE_END);
                double longitude = rs.getDouble(Endereco.TABLE_LONGITUDE_END);
                int id_rua = rs.getInt(Endereco.TABLE_ID_RUA);
                Endereco end = new Endereco(cod , num, latitude, longitude, rua.findById(id_rua));
                l.add(end);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Endereco obj) {
       boolean out = false;
        try {
            Endereco end = obj;
            bd = new BD();
            int id = super.findEnd(Endereco.NOME_TABLE, Endereco.TABLE_ID);
            end.setId(id + 1);
            String qry = "INSERT INTO " + Endereco.NOME_TABLE          + 
                    "( "                + Endereco.TABLE_ID            +
                    ", "                + Endereco.TABLE_NUM_END       + 
                    ", "                + Endereco.TABLE_LATITUDE_END  +
                    ", "                + Endereco.TABLE_LONGITUDE_END +
                    ", "                + Endereco.TABLE_ID_RUA        + 
                    " ) VALUES (" + end.getId()          + "," 
                                  + end.getNumero()      + "," 
                                  + end.getLatitude()    +  "," 
                                  + end.getLongitude()   + "," 
                                  + end.getRua().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Endereco.NOME_TABLE + " WHERE " + Endereco.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return out;
    }
}
