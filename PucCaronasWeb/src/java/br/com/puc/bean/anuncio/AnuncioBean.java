/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.bean.anuncio;

import br.com.puc.controll.EnderecoControll;
import br.com.puc.model.dao.AnuncioDAO;
import br.com.puc.model.dao.DestinoDAO;
import br.com.puc.model.dao.OrigemDAO;
import br.com.puc.model.dao.PassagemDAO;
import br.com.puc.model.dao.TrajetoDAO;
import br.com.puc.model.dao.table.Anuncio;
import br.com.puc.model.dao.table.Destino;
import br.com.puc.model.dao.table.Endereco;
import br.com.puc.model.dao.table.Origem;
import br.com.puc.model.dao.table.Passagem;
import br.com.puc.model.dao.table.Trajeto;
import br.com.puc.model.dao.table.Usuario;
import br.com.puc.util.DateUtil;
import br.com.puc.util.Util;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author 12647202
 */
@ManagedBean(name = "anuncio")
@ViewScoped
public class AnuncioBean implements Serializable{

    private String cepDes, cepOrg;
    private String cidadeDes, cidadeOrg;
    private String bairroDes, bairroOrg;
    private String ruaDes, ruaOrg;
    private int vagas;
    private String numDes, numOrg;
    private Date dataDes, dataOrg;
    private double preco;
    
    private boolean gerarRota;
    private boolean confimar;
    
    private Usuario usu;
    private Anuncio anc;

    public AnuncioBean() {
        cepDes = "";
        cepOrg = "";
        cidadeDes = "";
        cidadeOrg = "";
        bairroDes = "";
        bairroOrg = "";
        ruaDes = "";
        ruaOrg = "";
        numDes = "";
        numOrg = "";
        vagas = 1;
        preco = new Double(0);
        dataDes = new Date();
        dataOrg = new Date();
        
        gerarRota = true;
        confimar = false;
        
        usu = Util.usuarioToSessionRequest();
    }

    public String getCepDes() {
        return cepDes;
    }

    public void setCepDes(String cepDes) {
        this.cepDes = cepDes;
    }

    public String getCepOrg() {
        return cepOrg;
    }

    public void setCepOrg(String cepOrg) {
        this.cepOrg = cepOrg;
    }

    public String getCidadeDes() {
        return cidadeDes;
    }

    public void setCidadeDes(String cidadeDes) {
        this.cidadeDes = cidadeDes;
    }

    public String getCidadeOrg() {
        return cidadeOrg;
    }

    public void setCidadeOrg(String cidadeOrg) {
        this.cidadeOrg = cidadeOrg;
    }

    public String getBairroDes() {
        return bairroDes;
    }

    public void setBairroDes(String bairroDes) {
        this.bairroDes = bairroDes;
    }

    public String getBairroOrg() {
        return bairroOrg;
    }

    public void setBairroOrg(String bairroOrg) {
        this.bairroOrg = bairroOrg;
    }

    public String getRuaDes() {
        return ruaDes;
    }

    public void setRuaDes(String ruaDes) {
        this.ruaDes = ruaDes;
    }

    public String getRuaOrg() {
        return ruaOrg;
    }

    public void setRuaOrg(String ruaOrg) {
        this.ruaOrg = ruaOrg;
    }

    public String getNumDes() {
        return numDes;
    }

    public void setNumDes(String numDes) {
        this.numDes = numDes;
    }

    public String getNumOrg() {
        return numOrg;
    }

    public void setNumOrg(String numOrg) {
        this.numOrg = numOrg;
    }

    public Date getDataDes() {
        return dataDes;
    }

    public void setDataDes(Date dataDes) {
        this.dataDes = dataDes;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public Date getDataOrg() {
        return dataOrg;
    }

    public void setDataOrg(Date dataOrg) {
        this.dataOrg = dataOrg;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public boolean isGerarRota() {
        return gerarRota;
    }

    public boolean isConfimar() {
        return confimar;
    }

    public void gerarRota(ActionEvent actionEvent) {
        EnderecoControll endCtr = new EnderecoControll();
        int numero = Integer.parseInt(numDes);
        Endereco des = endCtr.gerarEnderecoCompleto(numero, ruaDes, cidadeDes);
        numero = Integer.parseInt(numOrg);
        Endereco org = endCtr.gerarEnderecoCompleto(numero, ruaOrg, cidadeOrg);
        
        Destino d = new Destino(-1, DateUtil.dateToDateStr(this.dataDes), DateUtil.dateToHourMinuteStr(this.dataDes), des);
        DestinoDAO daoDes = new DestinoDAO();
        daoDes.insert(d);

        Origem  o = new  Origem(-1, DateUtil.dateToDateStr(this.dataOrg), DateUtil.dateToHourMinuteStr(this.dataOrg), org);
        OrigemDAO daoOrg = new OrigemDAO();
        daoOrg.insert(o);
        
        Trajeto t = new Trajeto(-1, d, o);
        TrajetoDAO daoTra = new TrajetoDAO();
        daoTra.insert(t);
        
        this.refresh(org, des);
        
        //int qtd = Integer.parseInt(vagas);
        anc = new Anuncio(-1, vagas, usu, t);
        confimar = true;
        gerarRota = false;
    }
    
    public void gerarAnuncio(ActionEvent actionEvent)
    {
        AnuncioDAO daoAnc = new AnuncioDAO();
        PassagemDAO daoPsg = new PassagemDAO();
        confimar = false;
        gerarRota = true;
        boolean ok = daoAnc.insert(anc);
        
        if (ok) {
            for(int i = 0; i < anc.getQuantidade(); i++)
            {
                Passagem passagem = new Passagem(-1, preco, anc, usu);
                ok = ok & daoPsg.insert(passagem);
            }  
        }
        
        if(ok){
            Util.getFacesContext().addMessage(null, new FacesMessage("Carona criada :)", "Sucesso"));
        } else {
            Util.getFacesContext().addMessage(null, new FacesMessage("Não foi Possivel Criar a Carona :(", "Erro"));
        }
    }
    
    private void refresh(Endereco org, Endereco des)
    {
        this.bairroDes = des.getRua().getBairro().getNomeBai();
        this.bairroOrg = org.getRua().getBairro().getNomeBai();
        this.cepDes = des.getRua().getCep();
        this.cepOrg = org.getRua().getCep();
        this.cidadeDes = des.getRua().getBairro().getCidade().getNomeCidade();
        this.cidadeOrg = org.getRua().getBairro().getCidade().getNomeCidade();
        this.numDes = des.getNumero()+"";
        this.numOrg = org.getNumero()+"";
        this.ruaDes = des.getRua().getNome();
        this.ruaOrg = org.getRua().getNome();
    }
}
