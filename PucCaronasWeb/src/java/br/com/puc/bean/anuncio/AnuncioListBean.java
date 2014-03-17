/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.bean.anuncio;

import br.com.puc.model.dao.table.Anuncio;
import br.com.puc.util.DateUtil;
import br.com.puc.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author adelannucci
 */
@ManagedBean(name = "ancList")
@ViewScoped
public class AnuncioListBean extends AnuncioList {

    protected DataModel<Anuncio> anuncios;
    
    public AnuncioListBean() {
        super();
        anuncios = new ListDataModel(filterList(ancDAO.findAll()));
    }

    private List<Anuncio> filterList(List<Anuncio> list) {

        List<Anuncio> filterList = new ArrayList<Anuncio>();

        String dataOrg, horaOrg;
        int idAncUsu;
        int idUsu = Util.usuarioToSessionRequest().getId();

        for (Anuncio a : list) {

            dataOrg = a.getTrajeto().getOrigem().getData();
            horaOrg = a.getTrajeto().getOrigem().getHora();
            idAncUsu = a.getUsuario().getId();
            //Filtra a lista para nao mostrar as caronas do proprio usuario, as ja reservadas
            //e as com datas que ja passaram.
            if ((idAncUsu != idUsu) && (DateUtil.dateBefore(dataOrg, horaOrg))) 
            {
                if(!psgDAO.isPassagemForAnuncioAndUsuario(a.getId(), idUsu)) 
                {
                    filterList.add(a);
                }
            }
        }
        return filterList;
    }

    public DataModel<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void destinoDetail(ActionEvent actionEvent) {
        ancSelected = (this.anuncios.getRowData());
        org = ancSelected.getTrajeto().getOrigem().getEndereco();
        des = ancSelected.getTrajeto().getDestino().getEndereco();
        endSelected = des;
    }

    public void origemDetail(ActionEvent actionEvent) {
        ancSelected = (this.anuncios.getRowData());
        org = ancSelected.getTrajeto().getOrigem().getEndereco();
        des = ancSelected.getTrajeto().getDestino().getEndereco();
        endSelected = org;
    }

    public void buscarPassagem(ActionEvent actionEvent) {
        if (!psgDAO.isPassagemForAnuncioAndUsuario(ancSelected.getId(), usu.getId())) {
            psgSelected = psgDAO.buscarPorAnuncioPsgDisponivel(ancSelected.getId());
            if (psgSelected == null) {
                Util.getFacesContext().addMessage(null, new FacesMessage("Não existem mais Passagens :(", ":("));
            }
        }
        else{
            Util.hideDialog("reservaPsg");
            Util.getFacesContext().addMessage(null, new FacesMessage("Você já reservou uma passagem ^.^", ":P"));
        }
    }

    public void confirmarPassagem(ActionEvent actionEvent) {
        int qtd = ancDAO.findById(ancSelected.getId()).getQuantidade();
        if (qtd > 0) {
            qtd--;
            ancSelected.setQuantidade(qtd);
            ancDAO.update(ancSelected);
            psgSelected.setUsuario(usu);
            psgDAO.update(psgSelected);
            anuncios = new ListDataModel(filterList(ancDAO.findAll()));
        }
    }
}
