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
public class Mensagem implements Serializable{
    public static final String NOME_TABLE = "MENSAGEM";
    public static final String TABLE_ID = "ID_MSG"; 
    public static final String TABLE_TEXT_MSG = "TEXT_MSG"; 
    public static final String TABLE_TIPO_MSG = "TIPO_MSG";
    public static final String TABLE_ID_ANC = "ID_ANC"; 
    public static final String TABLE_ID_USU = "ID_USU";  
    
    public static final String ID = "ID"; 
    public static final String TEXT_MSG = "MENSAGEM"; 
    public static final String TIPO_MSG = "TIPO";
    public static final String ID_ANC = "ANUNCIO"; 
    public static final String ID_USU = "USUARIO";
    
    private int id;
    private String text;
    private int tipo;
    private Anuncio anuncio;
    private Usuario usuario;

    public Mensagem(int id, String text, int tipo, Anuncio anuncio, Usuario usuario) {
        this.id = id;
        this.text = text;
        this.tipo = tipo;
        this.anuncio = anuncio;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.text);
        hash = 23 * hash + this.tipo;
        hash = 23 * hash + Objects.hashCode(this.anuncio);
        hash = 23 * hash + Objects.hashCode(this.usuario);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (this.tipo != other.tipo) {
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
        return "Mensagem{" + "id=" + id + ", text=" + text + ", tipo=" + tipo + ", anuncio=" + anuncio + ", usuario=" + usuario + '}';
    }
    
    
}
