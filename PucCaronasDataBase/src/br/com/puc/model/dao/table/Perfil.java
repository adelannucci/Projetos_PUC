/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao.table;

import java.io.Serializable;

/**
 *
 * @author 12647202
 */
public class Perfil implements Serializable{
    
    public static final String NOME_TABLE = "PERFIL";
    public static final String TABLE_ID = "ID_PER"; 
    public static final String TABLE_TIPO_PER = "TIPO_PER"; 
    
    public static final String ID = "ID"; 
    public static final String TIPO = "PERFIL"; 
    
    protected int id;
    protected int tipo;

    public Perfil(int id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    public Perfil(int tipo) {
        this(-1, tipo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String tipoUsuario()
    {
        String out = "";
        if(tipo == 1) {
            out = "Administrador";
        }
        else if(tipo == 0) {
            out = "Usuario";
        }
        
        return out;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.tipo;
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
        final Perfil other = (Perfil) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Perfil{" + "id=" + id + ", tipo=" + tipo + '}';
    }
    
}
