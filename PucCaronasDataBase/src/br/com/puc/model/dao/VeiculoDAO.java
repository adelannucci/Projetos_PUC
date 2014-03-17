/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Veiculo;
import br.com.puc.model.dao.table.Veiculo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class VeiculoDAO extends DAO<Veiculo> {
    
    @Override
    public Veiculo findById(int id) {
        Veiculo vel = null;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            bd = new BD();
            vel = null;
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Veiculo.NOME_TABLE + " WHERE " + Veiculo.TABLE_ID + " = " + id + " ");
            while (rs.next()) {
                int cod = rs.getInt(Veiculo.TABLE_ID);
                String marca = rs.getString(Veiculo.TABLE_MARCA_VEL);
                String modelo = rs.getString(Veiculo.TABLE_MODELO_VEL);
                String placa = rs.getString(Veiculo.TABLE_PLACA_VEL);
                int id_usu = rs.getInt(Veiculo.TABLE_ID_USU);
                vel = new Veiculo(cod, marca, placa, modelo, dao.findById(id_usu));
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vel;
    }

    @Override
    public boolean update(Veiculo obj) {
        boolean out = false;
        try {
            bd = new BD();
            Veiculo vel = obj;
            String qry = "UPDATE " + Veiculo.NOME_TABLE + " set " + 
                    Veiculo.TABLE_MARCA_VEL  + "='" + vel.getMarca()  + "',"  + 
                    Veiculo.TABLE_MODELO_VEL + "='" + vel.getModelo() + "',"  + 
                    Veiculo.TABLE_PLACA_VEL  + "='" + vel.getPlaca()  + "',"  + 
                    Veiculo.TABLE_ID_USU     + "=" + vel.getUsuario().getId() + " where " + 
                    Veiculo.TABLE_ID + " = " + vel.getId();
            out = bd.execComando(qry);
            bd.fechar();
            return out;
        } catch (Exception ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Veiculo> findAll() {
        List<Veiculo> l = new ArrayList<>();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Veiculo.NOME_TABLE);
            while (rs.next()) {
                int cod = rs.getInt(Veiculo.TABLE_ID);
                String marca = rs.getString(Veiculo.TABLE_MARCA_VEL);
                String modelo = rs.getString(Veiculo.TABLE_MODELO_VEL);
                String placa = rs.getString(Veiculo.TABLE_PLACA_VEL);
                int id_usu = rs.getInt(Veiculo.TABLE_ID_USU);
                Veiculo vel = new Veiculo(cod, marca, placa, modelo, dao.findById(id_usu));
                l.add(vel);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Veiculo obj) {
        boolean out = false;
        try {
            Veiculo vel = obj;
            bd = new BD();
            int id = super.findEnd(Veiculo.NOME_TABLE, Veiculo.TABLE_ID);
            vel.setId(id + 1);
            String qry = "INSERT INTO " + Veiculo.NOME_TABLE +
                    "(" + Veiculo.TABLE_ID + 
                    "," + Veiculo.TABLE_MARCA_VEL +
                    "," + Veiculo.TABLE_MODELO_VEL +
                    "," + Veiculo.TABLE_PLACA_VEL +
                    "," + Veiculo.TABLE_ID_USU +
                    ") VALUES (" 
                    + vel.getId() + ",'" + vel.getMarca() + "','" + vel.getModelo() + "','" 
                    + vel.getPlaca() + "'," + vel.getUsuario().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Veiculo.NOME_TABLE + " WHERE " + Veiculo.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
}
