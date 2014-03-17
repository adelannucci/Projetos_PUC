/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao.table;

import br.com.puc.tools.maps.GMapsObject;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class Endereco implements Serializable{

    public static final String NOME_TABLE = "ENDERECO";
    public static final String TABLE_ID = "ID_END";
    public static final String TABLE_NUM_END = "NUM_END";
    public static final String TABLE_LATITUDE_END = "LATITUDE_END";
    public static final String TABLE_LONGITUDE_END = "LONGITUDE_END";
    public static final String TABLE_ID_RUA = "ID_RUA";
    public static final String ID = "ID";
    public static final String NUM = "NUMERO";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String ID_RUA = "RUA";
    private int id;
    private int numero;
    private double latitude;
    private double longitude;
    private Rua rua;

    public Endereco(int id, int numero, double latitude, double longitude, Rua rua) {
        this.id = id;
        this.numero = numero;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rua = rua;
    }

    public Endereco(int id, int numero, Rua rua) {
        try {
            this.id = id;
            this.numero = numero;
            this.rua = rua;
            GMapsObject maps = new GMapsObject(getEnderecoFormatado());
            this.latitude = maps.getLatitude();
            this.longitude = maps.getLongititude();
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        GMapsObject maps = new GMapsObject(getEnderecoFormatado());
        this.latitude = maps.getLatitude();
        this.longitude = maps.getLongititude();

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longirude) {
        this.longitude = longirude;
    }

    public Rua getRua() {
        return rua;
    }

    public void setRua(Rua rua) {
        this.rua = rua;
    }

    public String getEnderecoFormatado() {
        String out = "";
        if(this.rua.getNome() != null && !this.rua.getNome().equals("")) 
        {
            out += this.rua.getNome() + ", ";
        }
        if(this.getNumero() > 0)
        {
            out += this.getNumero() + ", ";
        }
        if(this.getRua().getBairro().getNomeBai() != null && !this.getRua().getBairro().getNomeBai().equals(""))
        {
            out += this.getRua().getBairro().getNomeBai() + ", ";
        }
        if(this.getRua().getCep() != null && !this.getRua().getCep().equals(""))
        {
            out += this.getRua().getCep();
        }

        return out;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.numero;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.rua);
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
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", numero=" + numero + ", latitude=" + latitude + ", longirude=" + longitude + ", rua=" + rua + '}';
    }
    
}
