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
public class Trajeto implements Serializable{
    
    public static final String NOME_TABLE = "TRAJETO";
    public static final String TABLE_ID = "ID_TRA";
    public static final String TABLE_ID_DES = "ID_DES";
    public static final String TABLE_ID_ORG = "ID_ORG";
    
    public static final String ID = "ID";
    public static final String ID_DES = "DESTINO";
    public static final String ID_ORG = "ORIGEM";
    
    private int id;
    private Destino destino;
    private Origem origem;

    public Trajeto(int id, Destino destino, Origem origem) {
        this.id = id;
        this.destino = destino;
        this.origem = origem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.destino);
        hash = 53 * hash + Objects.hashCode(this.origem);
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
        final Trajeto other = (Trajeto) obj;
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trajeto{" + "id=" + id + ", destino=" + destino + ", origem=" + origem + '}';
    }
    
    
    
    
}
