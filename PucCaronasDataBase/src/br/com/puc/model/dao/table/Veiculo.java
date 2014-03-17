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
public class Veiculo implements Serializable{
    public static final String NOME_TABLE = "VEICULO";
    public static final String TABLE_ID = "ID_VEL"; 
    public static final String TABLE_MARCA_VEL = "MARCA_VEL"; 
    public static final String TABLE_PLACA_VEL = "PLACA_VEL";
    public static final String TABLE_MODELO_VEL = "MODELO_VEL"; 
    public static final String TABLE_ID_USU = "ID_USU";
    
    public static final String ID = "ID"; 
    public static final String MARCA_VEL = "MARCA"; 
    public static final String PLACA_VEL = "PLACA";
    public static final String MODELO_VEL = "MODELO"; 
    public static final String ID_USU = "USUARIO";
    
    private int id;
    private String marca;
    private String placa;
    private String modelo;
    private Usuario usuario;

    public Veiculo(int id, String marca, String placa, String modelo, Usuario usuario) {
        this.id = id;
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
        hash = 67 * hash + Objects.hashCode(this.marca);
        hash = 67 * hash + Objects.hashCode(this.placa);
        hash = 67 * hash + Objects.hashCode(this.modelo);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "id=" + id + ", marca=" + marca + ", placa=" + placa + ", modelo=" + modelo + ", usuario=" + usuario + '}';
    }
}
