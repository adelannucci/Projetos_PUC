/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.bean.login;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
 
    private static final long serialVersionUID = 1520318172495977648L;
 
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
     
    /**
     * Redirect to info page.
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to info page.
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/secured/welcome.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/secured/welcome.xhtml";
    }
    
    public String redirectToCadastrarCaronas()
    {
        return "/secured/cadastros/anuncio/cadastrar_carona.xhtml";
    }
    
    public String redirectToTodasCaronas()
    {
        return "/secured/cadastros/anuncio/todas_caronas.xhtml";
    }
    
     public String redirectToMinhasCaronas()
    {
        return "/secured/cadastros/anuncio/minhas_caronas.xhtml";
    }
     
     public String redirectToMinhasPassagens()
    {
        return "/secured/cadastros/passagens/minhas_passagens.xhtml";
    }
     
}
