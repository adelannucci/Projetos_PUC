/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao.table;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author adelannucci
 */
public class Anuncio implements Serializable{
    
    public static final String NOME_TABLE = "ANUNCIO";
    public static final String TABLE_ID = "ID_ANC";
    public static final String TABLE_QTD_ANC = "QTD_ANC";
    public static final String TABLE_ID_USU = "ID_USU";
    public static final String TABLE_ID_TRA = "ID_TRA";
    
    public static final String ID = "ID";
    public static final String QTD_ANC = "QUANTIDADE";
    public static final String ID_USU = "USUARIO";
    public static final String ID_TRA = "TRAJETO";
    
    private int id;
    private int quantidade;
    private Usuario usuario;
    private Trajeto trajeto;

    public Anuncio(int id, int quantidade, Usuario usuario, Trajeto trajeto) {
        this.id = id;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.trajeto = trajeto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Trajeto getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(Trajeto trajeto) {
        this.trajeto = trajeto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.quantidade;
        hash = 29 * hash + Objects.hashCode(this.usuario);
        hash = 29 * hash + Objects.hashCode(this.trajeto);
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
        final Anuncio other = (Anuncio) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.trajeto, other.trajeto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Anuncio{" + "id=" + id + ", quantidade=" + quantidade + ", usuario=" + usuario + ", trajeto=" + trajeto + '}';
    }
  
}
