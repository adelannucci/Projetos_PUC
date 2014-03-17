/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import br.com.puc.model.dao.table.Mensagem;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class MensagemDAO extends DAO<Mensagem> {
    
     @Override
    public Mensagem findById(int id) {
        Mensagem msg = null;
        try {
            UsuarioDAO usu = new UsuarioDAO();
            AnuncioDAO anc = new AnuncioDAO();
            bd = new BD();
            msg = null;
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Mensagem.NOME_TABLE + " WHERE " + Mensagem.TABLE_ID + " = " + id + " ");
            while (rs.next()) {
                int cod = rs.getInt(Mensagem.TABLE_ID);
                String text = rs.getString(Mensagem.TABLE_TEXT_MSG);
                int tipo = rs.getInt(Mensagem.TABLE_TIPO_MSG);
                int id_anc = rs.getInt(Mensagem.TABLE_ID_ANC);
                int id_usu = rs.getInt(Mensagem.TABLE_ID_USU);
                msg = new Mensagem(cod, text, tipo, anc.findById(id_anc), usu.findById(id_usu));
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @Override
    public boolean update(Mensagem obj) {
        boolean out = false;
        try {
            bd = new BD();
            Mensagem msg = obj;
            String qry = "UPDATE " + Mensagem.NOME_TABLE + " set "              + 
                    Mensagem.TABLE_TEXT_MSG   + "='" + msg.getText()            + "',"      + 
                    Mensagem.TABLE_TIPO_MSG   + "="  + msg.getTipo()            + ","       + 
                    Mensagem.TABLE_ID_ANC     + "="  + msg.getAnuncio().getId() + ","       + 
                    Mensagem.TABLE_ID_USU     + "="  + msg.getUsuario().getId() + " where " + 
                    Mensagem.TABLE_ID + " = " + msg.getId();
            out = bd.execComando(qry);
            bd.fechar();
            return out;
        } catch (Exception ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    @Override
    public List<Mensagem> findAll() {
        List<Mensagem> l = new ArrayList<>();
        try {
            UsuarioDAO usu = new UsuarioDAO();
            AnuncioDAO anc = new AnuncioDAO();
            bd = new BD();
            ResultSet rs = bd.execConsulta("SELECT * FROM " + Mensagem.NOME_TABLE);
            while (rs.next()) {
                int cod = rs.getInt(Mensagem.TABLE_ID);
                String text = rs.getString(Mensagem.TABLE_TEXT_MSG);
                int tipo = rs.getInt(Mensagem.TABLE_TIPO_MSG);
                int id_anc = rs.getInt(Mensagem.TABLE_ID_ANC);
                int id_usu = rs.getInt(Mensagem.TABLE_ID_USU);
                Mensagem msg = new Mensagem(cod, text, tipo, anc.findById(id_anc), usu.findById(id_usu));
                l.add(msg);
            }
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean insert(Mensagem obj) {
        boolean out = false;
        try {
            Mensagem msg = obj;
            bd = new BD();
            int id = super.findEnd(Mensagem.NOME_TABLE, Mensagem.TABLE_ID);
            msg.setId(id + 1);
            String qry = "INSERT INTO " + Mensagem.NOME_TABLE +
                    "(" + Mensagem.TABLE_ID       + 
                    "," + Mensagem.TABLE_TEXT_MSG +
                    "," + Mensagem.TABLE_TIPO_MSG +
                    "," + Mensagem.TABLE_ID_ANC   +
                    "," + Mensagem.TABLE_ID_USU   +
                    ") VALUES (" 
                    + msg.getId() + ",'" + msg.getText() + "'," + msg.getTipo() + "," 
                    + msg.getAnuncio().getId() + "," + msg.getUsuario().getId() + ")";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    @Override
    public boolean delete(int id) {
        boolean out = false;
        try {
            bd = new BD();
            String qry = "DELETE FROM " + Mensagem.NOME_TABLE + " WHERE " + Mensagem.TABLE_ID + " = " + id + "";
            out = bd.execComando(qry);
            bd.fechar();
        } catch (Exception ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
    
}
