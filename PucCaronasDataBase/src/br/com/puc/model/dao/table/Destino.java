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
public class Destino implements Serializable{
    
    public static final String NOME_TABLE = "DESTINO";
    public static final String TABLE_ID = "ID_DES";
    public static final String TABLE_HORA_DES = "HORA_DES";
    public static final String TABLE_DATA_DES = "DATA_DES";
    public static final String TABLE_ID_END = "ID_END";
    
    public static final String ID = "ID";
    public static final String HORA_DES = "HORA";
    public static final String DATA_DES = "DATA";
    public static final String ID_END = "ENDERECO";
    
    private int id;
    private String data;
    private String hora;
    private Endereco endereco;

    public Destino(int id, String data, String hora, Endereco endereco) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.data);
        hash = 97 * hash + Objects.hashCode(this.hora);
        hash = 97 * hash + Objects.hashCode(this.endereco);
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
        final Destino other = (Destino) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Destino{" + "id=" + id + ", data=" + data + ", hora=" + hora + ", endereco=" + endereco + '}';
    }
}
