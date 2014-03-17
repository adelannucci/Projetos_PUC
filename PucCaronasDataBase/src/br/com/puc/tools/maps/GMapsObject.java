package br.com.puc.tools.maps;

import br.com.puc.controll.BairroContoll;
import br.com.puc.controll.CidadeControll;
import br.com.puc.controll.EstadoControll;
import br.com.puc.controll.RuaControll;
import br.com.puc.model.dao.table.Bairro;
import br.com.puc.model.dao.table.Cidade;
import br.com.puc.model.dao.table.Endereco;
import br.com.puc.model.dao.table.Rua;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author 12647202
 */
public class GMapsObject {

    private final static String URL_XML_GMAPS = "http://maps.google.com/maps/api/geocode/xml?sensor=false&";
    private final static String URL_APIIMG_GMAPS = "http://maps.googleapis.com/maps/api/staticmap?";
    private Document geocoderResultDocument;
    private Endereco end;
    private double latitude;
    private double longitude;

    /* code.google.com - busca e estudo sobre este codigo direto na api do google maps */
    public GMapsObject(String address) {
        try {
            String strUrl = URL_XML_GMAPS + "address=" + URLEncoder.encode(address, "UTF-8");
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress( strUrl, 8080));
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
            
            geocoderResultDocument = null;
            try {
                conn.connect();
            } finally {
                conn.disconnect();
            }
            InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());
            geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
            this.getGMapsCoords();
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            Logger.getLogger(GMapsObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getGMapsEnd() {
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList resultNodeList;
        String doc = "";
        try {
            resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
            for (int i = 0; i < resultNodeList.getLength(); ++i) {
                doc += resultNodeList.item(i).getTextContent() + ", ";
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GMapsObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    
//    public Endereco getEnderecoFormatado()
//    {
//        String dataEndereco[] = getGMapsEnd().split(",");
//
//        String nomeCidade = (dataEndereco[2].split("-"))[0].trim();
//        String nomeEstado = (dataEndereco[2].split("-"))[1].trim();
//        String nomeRua = dataEndereco[0].trim();
//        String cep = dataEndereco[3].substring(0, 6) + dataEndereco[3].substring(7);
//        int numero = Integer.parseInt((dataEndereco[1].split("-"))[0].trim());
//        String nomeBairro = (dataEndereco[1].split("-"))[1].trim();
//
//        
//        CidadeControll cidadeCtr = new CidadeControll();
//        Cidade cidade = cidadeCtr.buscarPorNomeCidade(nomeCidade);
//        if(cidade == null)
//        {
//            EstadoControll estadoCtr = new EstadoControll();
//            cidade = new Cidade(-1, nomeCidade, estadoCtr.buscarPorNomeEstado(nomeEstado) );
//            cidadeCtr.adicionar(cidade);
////            cidade = cidadeCtr.buscarPorNomeCidade(nomeCidade);
//        }
//
//        BairroContoll bairroCtr = new BairroContoll();
//        Bairro bairro = bairroCtr.buscarPorNomeBairro(nomeBairro);
//  
//        if (bairro == null) {
//            bairro = new Bairro(-1, nomeBairro, cidade);
//            bairroCtr.adicionar(bairro);
////            bairro = bairroCtr.buscarPorNomeBairro(nomeBairro);
//        }
//        
//        RuaControll ruaCtr = new RuaControll();
//        Rua rua = ruaCtr.buscarPorNomeRuaOuCep(nomeRua, cep);
//
//        if (rua == null) {
//            rua = new Rua(-1, nomeRua, cep, bairro);
//            ruaCtr.adicionar(rua);
////            rua = ruaCtr.buscarPorNomeRua(nomeRua);
//        }
//        
//        this.end.setRua(rua);
////        Endereco endereco = new Endereco(-1, numero, rua);
//
//        return this.end;
//    }

    private void getGMapsCoords() {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
            for (int i = 0; i < resultNodeList.getLength(); ++i) {
                Node node = resultNodeList.item(i);
                if ("lat".equals(node.getNodeName())) {
                    latitude = Double.parseDouble(node.getTextContent());
                }
                if ("lng".equals(node.getNodeName())) {
                    longitude = Double.parseDouble(node.getTextContent());
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GMapsObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //returns path where save the img
    public static String getGMapsRota(Endereco org, Endereco des) throws Exception {


        String strCoords = "";
        strCoords += org.getLatitude() + "," + org.getLongitude() + "," + des.getLatitude() + "," + des.getLongitude();
//                rota.getAeroOrigem().getCoordenadas().getLatitude()
//                + "," + rota.getAeroOrigem().getCoordenadas().getLongitude();

//        for (int i = 0; i < rota.getAeroEscala().size(); i++) {
//            strCoords += "|" + rota.getAeroEscala().get(i).getCoordenadas().getLatitude()
//                    + "," + rota.getAeroEscala().get(i).getCoordenadas().getLongitude();
//        }
        strCoords += des.getLatitude() + "," + des.getLongitude();
//                "|" + rota.getAeroDestino().getCoordenadas().getLatitude()
//                + "," + rota.getAeroDestino().getCoordenadas().getLongitude();

        String markers = "&markers=size:normal|";
        markers += "color:green|label:O|" + org.getLatitude() + "," + org.getLongitude() + "," + des.getLatitude() + "," + des.getLongitude();

//        for (int i = 0; i < rota.getAeroEscala().size(); i++) {
//            markers += "&markers=size:normal|color:yellow|label:" + (i + 1) + "|" + rota.getAeroEscala().get(i).getCoordenadas().getLatitude()
//                    + "," + rota.getAeroEscala().get(i).getCoordenadas().getLongitude();
//        }
//        

        String color = "ff0000";
        String mapsUrl = URL_APIIMG_GMAPS
                + "maptype=roadmap" +// roadmap, satellite, hybrid, and terrain;
                "&size=450x300&sensor=false&path=color:0x" + color
                + "|weight:3|" + strCoords + markers + "&sensor=false";

        //System.out.println(new URL(mapsUrl));
        //System.exit(-1);

        return Download(new URL(mapsUrl)); //Return o caminho da imagem  

    }

//    public static String getGMapsImgAero(Coordenada coordenada) throws Exception {
//
//
//        String strCoords = "";
//
//        strCoords += coordenada.getLatitude()
//                + "," + coordenada.getLongitude();
//
//        String mapsUrl = URL_APIIMG_GMAPS
//                + "maptype=satellite" +// roadmap, satellite, hybrid, and terrain;
//                "&center=" + strCoords
//                + "&zoom=14&"
//                + "size=420x240&maptype=roadmap&"
//                + "markers=color:red|label:*|" + strCoords
//                + "&sensor=false";
//
//        return Download(new URL(mapsUrl)); //Return o caminho da imagem  
//
//    }
    private static String Download(URL url) throws Exception {

        String nomeArquivoLocal = System.currentTimeMillis() + ".jpg",
                pathTmp = System.getProperty("java.io.tmpdir");
        FileOutputStream fos;
        try (InputStream is = url.openStream()) {
            fos = new FileOutputStream(pathTmp + nomeArquivoLocal);
            int umByte = 0;
            while ((umByte = is.read()) != -1) {
                fos.write(umByte);
            }
        }
        fos.close();

        return pathTmp + nomeArquivoLocal;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongititude() {
        return longitude;
    }
}
