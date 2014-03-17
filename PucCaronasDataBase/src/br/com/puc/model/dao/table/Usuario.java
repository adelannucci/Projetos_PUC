/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao.table;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 12647202
 */
public class Usuario implements Serializable{
    public static final String NOME_TABLE = "USUARIO";
    public static final String TABLE_ID = "ID_USU"; 
    public static final String TABLE_LOGIN_USU = "LOGIN_USU"; 
    public static final String TABLE_PASS_USU = "PASS_USU";
    public static final String TABLE_NOME_USU = "NOME_USU"; 
    public static final String TABLE_FACULDADE_USU = "FACULDADE_USU";
    public static final String TABLE_TELEFONE_USU = "TELEFONE_USU"; 
    public static final String TABLE_STATUS_USU = "STATUS_USU";
    public static final String TABLE_RA_USU = "RA_USU";
    public static final String TABLE_ID_PER = "ID_PER";
    
    public static final String ID = "ID"; 
    public static final String LOGIN = "LOGIN"; 
    public static final String PASS = "PASSWORD";
    public static final String NOME_USU = "NOME"; 
    public static final String FACULDADE_USU = "FACULDADE";
    public static final String TELEFONE_USU = "TELEFONE"; 
    public static final String STATUS_USU = "STATUS";
    public static final String RA_USU = "RA";
    public static final String ID_PERFIL = "PERFIL";  
    
    private int id;
    private String login;
    private String pass;
    private String nome;
    private String faculdade;
    private String telefone;
    private int status;
    private int ra;
    private Perfil perfil;

    public Usuario(int id, String login, String pass, String nome, String faculdade, String telefone, int ra, int status, Perfil perfil) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.nome = nome;
        this.faculdade = faculdade;
        this.telefone = telefone;
        this.ra = ra;
        this.status = status;
        this.perfil = perfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.login);
        hash = 47 * hash + Objects.hashCode(this.pass);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.faculdade);
        hash = 47 * hash + Objects.hashCode(this.telefone);
        hash = 47 * hash + this.status;
        hash = 47 * hash + this.ra;
        hash = 47 * hash + Objects.hashCode(this.perfil);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.faculdade, other.faculdade)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.ra != other.ra) {
            return false;
        }
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", login=" + login + ", pass=" + pass + ", nome=" + nome + ", faculdade=" + faculdade + ", telefone=" + telefone + ", status=" + status + ", ra=" + ra + ", perfil=" + perfil + '}';
    }

}
