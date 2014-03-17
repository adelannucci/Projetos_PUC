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
public class Bairro implements Serializable{
    
    public static final String NOME_TABLE = "BAIRRO";
    public static final String TABLE_ID = "ID_BAI"; 
    public static final String TABLE_NOME_BAI = "NOME_BAI";
    public static final String TABLE_ID_CID = "ID_CID";

    public static final String ID = "ID"; 
    public static final String BAIRRO = "BAIRRO"; 
    public static final String CIDADE = "CIDADE";


    protected int id;
    protected String nomeBai;
    protected Cidade cidade;

    public Bairro(int id, String nomeBai, Cidade cidade) {
        this.id = id;
        this.nomeBai = nomeBai;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeBai() {
        return nomeBai;
    }

    public void setNomeBai(String nomeBai) {
        this.nomeBai = nomeBai;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nomeBai);
        hash = 89 * hash + Objects.hashCode(this.cidade);
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
        final Bairro other = (Bairro) obj;
        if (!Objects.equals(this.nomeBai, other.nomeBai)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bairro{" + "id=" + id + ", nomeBai=" + nomeBai + ", cidade=" + cidade + '}';
    }
}
