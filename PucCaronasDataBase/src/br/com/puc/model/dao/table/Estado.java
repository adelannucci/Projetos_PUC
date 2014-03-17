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
public class Estado implements Serializable{
    public static final String NOME_TABLE = "ESTADO";
    public static final String TABLE_ID = "ID_EST"; 
    public static final String TABLE_NOME_EST = "NOME_EST"; 
    public static final String TABLE_SIGLA_EST = "SIGLA_EST";
    
    public static final String ID = "ID"; 
    public static final String NOME = "NOME"; 
    public static final String SIGLA = "SIGLA";

    protected int id;
    protected String nomeEstado;
    protected String siglaEstado;

    public Estado(int id, String nomeEstado, String siglaEstado) {
        super();
        this.id = id;
        this.nomeEstado = nomeEstado;
        this.siglaEstado = siglaEstado;
    }
    
    public Estado(String nomeEstado, String siglaEstado) {
        this(-1, nomeEstado, siglaEstado);
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.nomeEstado);
        hash = 71 * hash + Objects.hashCode(this.siglaEstado);
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.nomeEstado, other.nomeEstado)) {
            return false;
        }
        if (!Objects.equals(this.siglaEstado, other.siglaEstado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", nomeEstado=" + nomeEstado + ", siglaEstado=" + siglaEstado + '}';
    }

    
}
