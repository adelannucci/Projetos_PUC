/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.bean;

import br.com.puc.controll.PerfilControll;
import br.com.puc.controll.UsuarioControll;
import br.com.puc.model.dao.table.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author adelannucci
 */
@ManagedBean(name = "usuario")
@SessionScoped
public class UsuarioBean implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String login;
    private String pass;
    private String nome;
    private String faculdade;
    private String telefone;
    private String ra;
    private Usuario usuario;
    private UsuarioControll ctr;

    public UsuarioBean() {
        this.login = "";
        this.pass = "";
        this.nome = "";
        this.faculdade = "";
        this.telefone = "";
        this.ra = "";
        this.usuario = new Usuario(-1, null, null, null, null, null, 0, 0, null);
        this.ctr = new UsuarioControll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioControll getCtr() {
        return ctr;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void cadastrarUsuario() {
        PerfilControll per = new PerfilControll();
        this.usuario = new Usuario(-1, login, pass, nome, faculdade, telefone, Integer.parseInt(ra), 0, per.pesquisar(2));
        FacesContext context = FacesContext.getCurrentInstance();
        if (ctr.adicionar(usuario)) {
            context.addMessage(null, new FacesMessage("Usuário Cadastrado", usuario.getNome()));
        } else {
            context.addMessage(null, new FacesMessage("Erro ao cadastrar", "Não foi Possivel Cadastrar"));
        }
    }
}
