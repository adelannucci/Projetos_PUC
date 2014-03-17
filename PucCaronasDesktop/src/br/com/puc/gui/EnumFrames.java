/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui;

/**
 *
 * @author 12647202
 */
public enum EnumFrames {

    Login("Login"),
    FindEstado("FindEstado"),
    Estado("Estado");
    
    private String frmName;
    
    EnumFrames(String name)
    {
        frmName = name;
    }
    
    public String getFrmName()
    {
        return frmName;
    }
}
