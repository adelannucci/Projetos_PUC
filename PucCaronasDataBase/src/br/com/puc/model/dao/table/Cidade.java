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
public class Cidade implements Serializable{
    
    public static final String NOME_TABLE = "CIDADE";
    public static final String TABLE_ID = "ID_CID"; 
    public static final String TABLE_NOME_CID = "NOME_CID"; 
    public static final String TABLE_ID_EST = "ID_EST";
    
    public static final String ID = "ID"; 
    public static final String NOME = "NOME"; 
    public static final String ID_EST = "ESTADO";
    
    protected int id;
    protected String nomeCidade;
    protected Estado estado;

    public Cidade(int id, String nomeCidade, Estado estado) {
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nomeCidade);
        hash = 67 * hash + Objects.hashCode(this.estado);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.nomeCidade, other.nomeCidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Cidade{" + "id=" + id + ", nomeCidade=" + nomeCidade + ", estado=" + estado + '}';
    }
    
    
}
