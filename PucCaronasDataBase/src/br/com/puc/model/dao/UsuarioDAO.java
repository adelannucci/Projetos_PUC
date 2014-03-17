/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Estado;
import br.com.puc.model.dao.table.Perfil;
import br.com.puc.model.dao.table.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class UsuarioDAO extends DAO<Usuario> {

    @Override
    public Usuario findById(int id) {
        Usuario usu = null;
        try {
            PerfilDAO dao = new PerfilDAO();
            bd = new BD();
            usu = null;
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Usuario.NOME_TABLE + " WHERE " + Usuario.TABLE_ID + " = " + id + " ");
            while (rs.next()) {
                int cod = rs.getInt(Usuario.TABLE_ID);
                String login = rs.getString(Usuario.TABLE_LOGIN_USU);
                String pass = rs.getString(Usuario.TABLE_PASS_USU);
                String nome = rs.getString(Usuario.TABLE_NOME_USU);
                String faculdade = rs.getString(Usuario.TABLE_FACULDADE_USU);
                String telefone = rs.getString(Usuario.TABLE_TELEFONE_USU);
                int status = rs.getInt(Usuario.TABLE_STATUS_USU); 
                int ra = rs.getInt(Usuario.TABLE_RA_USU); 
                Perfil perfil = dao.findById(rs.getInt(Usuario.TABLE_ID_PER));
                usu = new Usuario(cod, login, pass, nome, faculdade, telefone, ra, status, perfil);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }

    @Override
    public boolean update(Usuario obj) {
        boolean out = false;
        try {
            bd = new BD();
            Usuario usu = obj;
            String qry = "UPDATE " + Usuario.NOME_TABLE + " set " + 
                    Usuario.TABLE_LOGIN_USU     + "='" + usu.getLogin()     + "',"      + 
                    Usuario.TABLE_PASS_USU      + "='" + usu.getPass()      + "',"      + 
                    Usuario.TABLE_NOME_USU      + "='" + usu.getNome()      + "',"      + 
                    Usuario.TABLE_FACULDADE_USU + "='" + usu.getFaculdade() + "',"      +
                    Usuario.TABLE_TELEFONE_USU  + "='" + usu.getTelefone()  + "',"      +
                    Usuario.TABLE_RA_USU        + "="  + usu.getRa()         + ", " + 
                    Usuario.TABLE_STATUS_USU    + "="  + usu.getStatus()     + " where " +
                    Usuario.TABLE_ID + " = " + usu.getId();
            out = bd.execComando(qry);
            bd.fechar();
            return out;
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> l = new ArrayList<>();
        try {
            PerfilDAO dao = new PerfilDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Usuario.NOME_TABLE);
            while (rs.next()) {
                int cod = rs.getInt(Usuario.TABLE_ID);
                String login = rs.getString(Usuario.TABLE_LOGIN_USU);
                String pass = rs.getString(Usuario.TABLE_PASS_USU);
                String nome = rs.getString(Usuario.TABLE_NOME_USU);
                String faculdade = rs.getString(Usuario.TABLE_FACULDADE_USU);
                String telefone = rs.getString(Usuario.TABLE_TELEFONE_USU);
                int ra = rs.getInt(Usuario.TABLE_RA_USU); 
                int status = rs.getInt(Usuario.TABLE_STATUS_USU); 
                Perfil perfil = dao.findById(rs.getInt(Usuario.TABLE_ID_PER));
                Usuario usu = new Usuario(cod, login, pass, nome, faculdade, telefone, ra, status, perfil);
                l.add(usu);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Usuario obj) {
        boolean out = false;
        try {
            Usuario usu = obj;
            bd = new BD();
            int id = super.findEnd(Usuario.NOME_TABLE, Usuario.TABLE_ID);
            usu.setId(id + 1);
            String qry = "INSERT INTO " + Usuario.NOME_TABLE +
                    "(" + Usuario.TABLE_ID + 
                    "," + Usuario.TABLE_LOGIN_USU +
                    "," + Usuario.TABLE_PASS_USU +
                    "," + Usuario.TABLE_NOME_USU +
                    "," + Usuario.TABLE_FACULDADE_USU +
                    "," + Usuario.TABLE_TELEFONE_USU +
                    "," + Usuario.TABLE_RA_USU + 
                    "," + Usuario.TABLE_STATUS_USU + 
                    "," + Usuario.TABLE_ID_PER +
                    ") VALUES (" 
                    + usu.getId() + ",'" + usu.getLogin() + "','" + usu.getPass() + "','" 
                    + usu.getNome() + "','" + usu.getFaculdade() + "','" + usu.getTelefone()   
                    + "'," + usu.getRa() + "," + usu.getStatus() + "," + usu.getPerfil().getId() + ")";
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
            String qry = "DELETE FROM " + Usuario.NOME_TABLE + " WHERE " + Usuario.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
}
