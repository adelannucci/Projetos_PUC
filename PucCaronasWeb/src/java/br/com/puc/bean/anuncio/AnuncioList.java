/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.bean.anuncio;

import br.com.puc.model.dao.AnuncioDAO;
import br.com.puc.model.dao.PassagemDAO;
import br.com.puc.model.dao.table.Anuncio;
import br.com.puc.model.dao.table.Endereco;
import br.com.puc.model.dao.table.Passagem;
import br.com.puc.model.dao.table.Usuario;
import br.com.puc.util.Util;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author 12647202
 */
public class AnuncioList implements Serializable {
    
    protected Anuncio ancSelected;
    protected PassagemDAO psgDAO;
    protected Passagem psgSelected;
    protected Endereco endSelected;
    protected Endereco des;
    protected Endereco org;
    protected MapModel simpleModel;
    protected Marker marker;
    protected Usuario usu;
    protected AnuncioDAO ancDAO;
    
    public AnuncioList()
    {
        ancDAO = new AnuncioDAO();
        psgDAO = new PassagemDAO();
        simpleModel = new DefaultMapModel();
        usu = Util.usuarioToSessionRequest();
    }
    
    public Anuncio getAncSelected() {
        return ancSelected;
    }

    public Passagem getPsgSelected() {
        return psgSelected;
    }

    public Endereco getEndSelected() {
        return endSelected;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();

        Util.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço", marker.getTitle()));

    }

    public String cordenadas() {
        String coordStr = 0 + "," + 0;

        if (ancSelected != null) {
            coordStr = endSelected.getLatitude() + "," + endSelected.getLongitude();
            simpleModel = new DefaultMapModel();
            LatLng desCoord = new LatLng(this.des.getLatitude(), this.des.getLongitude());
            LatLng orgCoord = new LatLng(this.org.getLatitude(), this.org.getLongitude());
            simpleModel.addOverlay(new Marker(orgCoord, "Origem: \n" + this.org.getRua().getNome()));
            simpleModel.addOverlay(new Marker(desCoord, "Destino: \n" + this.des.getRua().getNome()));
        }

        return coordStr;
    }
    
}
