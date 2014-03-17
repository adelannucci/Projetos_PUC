/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.bean.passagens;

import br.com.puc.bean.anuncio.AnuncioList;
import br.com.puc.model.dao.PassagemDAO;
import br.com.puc.model.dao.table.Anuncio;
import br.com.puc.model.dao.table.Passagem;
import br.com.puc.util.DateUtil;
import br.com.puc.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author 12647202
 */
@ManagedBean(name = "minhasPsg")
@ViewScoped
public class MinhasPassagensBean implements Serializable{

    protected DataModel<Passagem> passagens;
    protected PassagemDAO psgDAO;
    protected MapModel simpleModel;
    protected Marker marker;
    private String coordenada = "0,0";

    public MinhasPassagensBean() {
        psgDAO = new PassagemDAO();
        simpleModel = new DefaultMapModel();
        passagens = new ListDataModel(filterList(psgDAO.findAll()));
    }

    private List<Passagem> filterList(List<Passagem> list) {

        List<Passagem> filterList = new ArrayList<Passagem>();

        String dataOrg, horaOrg;
        int idAncUsu;
        int idUsu = Util.usuarioToSessionRequest().getId();

        for (Passagem a : list) {

            dataOrg = a.getAnuncio().getTrajeto().getOrigem().getData();
            horaOrg = a.getAnuncio().getTrajeto().getOrigem().getHora();
            idAncUsu = a.getAnuncio().getUsuario().getId();
            //Filtra a lista para nao mostrar as caronas do proprio usuario, as ja reservadas
            //e as com datas que ja passaram.
            if ((a.getUsuario().getId() == idUsu) && (idAncUsu != idUsu) && (DateUtil.dateBefore(dataOrg, horaOrg))) {
                filterList.add(a);
            }
        }
        return filterList;
    }

    public DataModel<Passagem> getPassagens() {
        return passagens;
    }

    public void mapDetail(ActionEvent actionEvent) {
        Anuncio ancSelected = (this.passagens.getRowData()).getAnuncio();
        if (ancSelected != null) {
            coordenada = ancSelected.getTrajeto().getOrigem().getEndereco().getLatitude() + ","
                    + ancSelected.getTrajeto().getOrigem().getEndereco().getLongitude();
            simpleModel = new DefaultMapModel();
            LatLng desCoord = new LatLng(ancSelected.getTrajeto().getDestino().getEndereco().getLatitude(), ancSelected.getTrajeto().getDestino().getEndereco().getLongitude());
            LatLng orgCoord = new LatLng(ancSelected.getTrajeto().getOrigem().getEndereco().getLatitude(), ancSelected.getTrajeto().getOrigem().getEndereco().getLongitude());
            simpleModel.addOverlay(new Marker(orgCoord, "Origem: \n" + ancSelected.getTrajeto().getOrigem().getEndereco().getRua().getNome()));
            simpleModel.addOverlay(new Marker(desCoord, "Destino: \n" + ancSelected.getTrajeto().getDestino().getEndereco().getRua().getNome()));
        }
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();

        Util.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço", marker.getTitle()));

    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public String getCoordenada() {
        return coordenada;
    }
}
