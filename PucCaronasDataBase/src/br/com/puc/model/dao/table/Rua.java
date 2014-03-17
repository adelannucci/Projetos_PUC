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
public class Rua implements Serializable{
    
    public static final String NOME_TABLE = "RUA";
    public static final String TABLE_ID = "ID_RUA";
    public static final String TABLE_NOME_RUA = "NOME_RUA";
    public static final String TABLE_CEP_RUA = "CEP_RUA";
    public static final String TABLE_ID_BAI = "ID_BAI";
    
    public static final String ID = "ID";
    public static final String RUA = "RUA";
    public static final String CEP = "CEP";
    public static final String ID_BAI = "BAIRRO";
    
    private int id;
    private String nome;
    private String cep;
    private Bairro bairro;
    
    public Rua(int id, String nome, String cep, Bairro bairro) {
        this.id = id;
        this.nome = nome;
        this.cep = cep;
        this.bairro = bairro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.cep);
        hash = 29 * hash + Objects.hashCode(this.bairro);
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
        final Rua other = (Rua) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!this.cep.equals(other.cep)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Rua{" + "id=" + id + ", nome=" + nome + ", cep=" + cep + ", bairro=" + bairro + '}';
    }
    
    
}
