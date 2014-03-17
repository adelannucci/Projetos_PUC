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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author 12647202
 */
@ManagedBean(name = "minhasCaronas")
@ViewScoped
public class MinhasCaronasBean extends AnuncioList{

    protected DataModel<Anuncio> anuncios;
    
    public MinhasCaronasBean() {
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
            if ((idAncUsu == idUsu) && (DateUtil.dateBefore(dataOrg, horaOrg))) 
            {
               filterList.add(a);
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
    
}
