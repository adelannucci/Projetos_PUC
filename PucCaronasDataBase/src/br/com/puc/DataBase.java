/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc;

import br.com.puc.controll.AnuncioControll;
import br.com.puc.controll.BairroContoll;
import br.com.puc.controll.CidadeControll;
import br.com.puc.controll.DestinoControll;
import br.com.puc.controll.EnderecoControll;
import br.com.puc.controll.EstadoControll;
import br.com.puc.controll.MensagemControll;
import br.com.puc.controll.OrigemControll;
import br.com.puc.controll.PassagemControll;
import br.com.puc.controll.PerfilControll;
import br.com.puc.controll.RuaControll;
import br.com.puc.controll.TrajetoControll;
import br.com.puc.controll.UsuarioControll;
import br.com.puc.controll.VeiculoControll;
import br.com.puc.tools.database.BD;
import br.com.puc.tools.proxy.ProxyAuthenticator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelannucci
 */
public class DataBase {
    
    private static void dropTables()
    {
        try {
            String qrys[] = {
                "drop table anuncio cascade constraints PURGE",
                "drop table bairro cascade constraints PURGE",
                "drop table cidade cascade constraints PURGE",
                "drop table endereco cascade constraints PURGE",
                "drop table estado cascade constraints PURGE",
                "drop table mensagem cascade constraints PURGE",
                "drop table passagem cascade constraints PURGE",
                "drop table perfil cascade constraints PURGE",
                "drop table usuario cascade constraints PURGE",
                "drop table veiculo cascade constraints PURGE",
                "drop table DESTINO cascade constraints PURGE",
                "drop table ORIGEM cascade constraints PURGE",
                "drop table TRAJETO cascade constraints PURGE",
                "drop table RUA cascade constraints PURGE"};
            BD bd = new BD();
            
            for (String qry : qrys) {
                bd.execComando(qry);
            }
        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
        //quando rodar na PUC descomentar a linha abaixo e colocar o RA e a SENHA de LOGIN do pc da PUC.
        //ProxyAuthenticator.setProxy("RA", "SENHA");
        PerfilControll per = new PerfilControll();
        per.teste();
        UsuarioControll usr = new UsuarioControll();
        usr.teste();
        EstadoControll est = new EstadoControll();
        est.teste();
        CidadeControll cid = new CidadeControll();
        cid.teste();
        BairroContoll bai = new BairroContoll();
        bai.teste();
        RuaControll rua = new RuaControll();
        rua.teste();
        EnderecoControll end = new EnderecoControll();
        end.teste();
        OrigemControll org = new OrigemControll();
        org.teste();
        DestinoControll des = new DestinoControll();
        des.teste();
        TrajetoControll tra = new TrajetoControll();
        tra.teste();
        AnuncioControll anc = new AnuncioControll();
        anc.teste();
        PassagemControll psg = new PassagemControll();
        psg.teste();
        VeiculoControll vel = new VeiculoControll();
        vel.teste();
        MensagemControll msg = new MensagemControll();
        msg.teste();
    }
}
