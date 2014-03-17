/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.principal;

import br.com.puc.gui.FrmPrin;
import br.com.puc.model.dao.EstadoDAO;
import br.com.puc.model.dao.table.Estado;
import java.util.List;

/**
 *
 * @author 12560520
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //quando rodar na PUC descomentar a linha abaixo e colocar o RA e a SENHA de LOGIN do pc da PUC.
        //ProxyAuthenticator.setProxy("RA", "SENHA");
        FrmPrin frm = new FrmPrin();
        frm.setVisible(true);

    }

    public static void listAll(EstadoDAO est) {
        List<Estado> list = est.findAll();
        for (Estado s : list) {
            System.out.println(s);
        }
    }

}
