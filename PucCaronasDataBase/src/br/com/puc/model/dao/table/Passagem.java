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
public class Passagem implements Serializable{
    
    public static final String NOME_TABLE = "PASSAGEM";
    public static final String TABLE_ID = "ID_PSG";
    public static final String TABLE_PRECO_PSG = "PRECO_PSG";
    public static final String TABLE_ID_ANC = "ID_ANC";
    public static final String TABLE_ID_USU = "ID_USU";
    
    public static final String ID = "ID";
    public static final String PRECO_PSG = "PRECO";
    public static final String ID_ANC = "ANUNCIO";
    public static final String ID_USU = "USUARIO";
    
    private int id;
    private double preco;
    private Anuncio anuncio;
    private Usuario usuario;

    public Passagem(int id, double preco, Anuncio anuncio, Usuario usuario) {
        this.id = id;
        this.preco = preco;
        this.anuncio = anuncio;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.preco) ^ (Double.doubleToLongBits(this.preco) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.anuncio);
        hash = 67 * hash + Objects.hashCode(this.usuario);
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
        final Passagem other = (Passagem) obj;
        if (Double.doubleToLongBits(this.preco) != Double.doubleToLongBits(other.preco)) {
            return false;
        }
        if (!Objects.equals(this.anuncio, other.anuncio)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Passagem{" + "id=" + id + ", preco=" + preco + ", anuncio=" + anuncio + ", usuario=" + usuario + '}';
    }
    
    

}
