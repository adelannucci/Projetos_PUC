/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.util;

import br.com.puc.bean.login.LoginBean;
import br.com.puc.model.dao.table.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author adelannucci
 */
public class Util {
    
    public static Usuario usuarioToSessionRequest()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(true);
        return ((LoginBean)s.getAttribute("loginBean")).getUsu();  
    }
    
    public static FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }
    
    public static void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
    
    public static void hideDialog(String dialogId){
         RequestContext rc = RequestContext.getCurrentInstance();
         rc.execute(dialogId+".hide()");   
    }
    
    public static void updateForm(String frmId){
         RequestContext rc = RequestContext.getCurrentInstance();
         rc.update(frmId);   
    }
    
    
}
