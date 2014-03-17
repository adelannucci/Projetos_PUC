/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.UsuarioDAO;
import br.com.puc.model.dao.table.Perfil;
import br.com.puc.model.dao.table.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public class UsuarioControll extends Controll<Usuario> {

    public UsuarioControll() {
        dao = new UsuarioDAO();
        lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Usuario e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Usuario pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Usuario obj) {
        boolean out = false;
        obj.setPass(this.sha1(obj.getPass()));
        out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Usuario obj) {

//        obj.setPass(this.sha1(obj.getPass()));
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Usuario> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public Usuario logar(String login, String pass) {

        String password = (this.sha1(pass));
        Usuario out = null;
        //select PASS_USU, id_per from Usuario where LOGIN_USU = 'adm';
        String qry = "SELECT * FROM "
                + Usuario.NOME_TABLE + " WHERE "
                + Usuario.TABLE_LOGIN_USU + " = '"
                + login + "'";

        try {
            ResultSet rs = dao.query(qry);
            if (rs.next()) {
                if (password.equals(rs.getString(Usuario.TABLE_PASS_USU))) {
                    int id = rs.getInt(Usuario.TABLE_ID);
                    String log = rs.getString(Usuario.TABLE_LOGIN_USU);
                    String nome = rs.getString(Usuario.TABLE_NOME_USU);
                    String faculdade = rs.getString(Usuario.TABLE_FACULDADE_USU);
                    String telefone = rs.getString(Usuario.TABLE_TELEFONE_USU);
                    int ra = rs.getInt(Usuario.TABLE_RA_USU);
                    int status = rs.getInt(Usuario.TABLE_STATUS_USU);
                    Perfil perfil = (new PerfilControll()).pesquisar(rs.getInt(Usuario.TABLE_ID_PER));
                    out = new Usuario(id, log, "", nome, faculdade, telefone, ra, status, perfil);
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    private String sha1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            return br.com.puc.tools.crypto.CryptoUtil.ByteArrayToHexStr(md.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void teste() {
        PerfilControll ctr = new PerfilControll();
        Usuario usu = new Usuario(-1, "ad", "adm", "adm", "Faculdade", "(19)-9999-9999", 123456, 0, ctr.pesquisar(1));
        this.adicionar(usu);
        usu = this.pesquisar(1);
        usu.setLogin("adm");
        this.atualizar(usu);
        this.adicionar(new Usuario(-1, "lari@puc.edu.br", "lari", "Larissa Coelho", "Puc Campinas", "(19)-9999-9999", 12000000, 0, ctr.pesquisar(2)));
        this.adicionar(new Usuario(-1, "andre", "andre", "andre", "Puc Campinas", "(19)-9999-9999", 12000000, 0, ctr.pesquisar(2)));
        for (Usuario e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Usuario e : buscarTodos()) {
            System.out.println(e);
        }
    }
}