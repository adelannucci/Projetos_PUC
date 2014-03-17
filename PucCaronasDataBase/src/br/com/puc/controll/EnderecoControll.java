/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.controll;

import br.com.puc.model.dao.EnderecoDAO;
import br.com.puc.model.dao.table.Bairro;
import br.com.puc.model.dao.table.Cidade;
import br.com.puc.model.dao.table.Endereco;
import br.com.puc.model.dao.table.Rua;
import br.com.puc.tools.maps.GMapsObject;
import java.util.List;

/**
 *
 * @author adelannucci
 */
public class EnderecoControll extends Controll<Endereco> {

    public EnderecoControll() {
        dao = new EnderecoDAO();
        this.lista = dao.findAll();
    }

    @Override
    public boolean excluir(int id) {
        boolean out = dao.delete(id);
        if (out) {
            for (Endereco e : lista) {
                if (id == e.getId()) {
                    lista.remove(e);
                    break;
                }
            }
        }
        return out;
    }

    @Override
    public Endereco pesquisar(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean adicionar(Endereco obj) {
        boolean out = dao.insert(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public boolean atualizar(Endereco obj) {
        boolean out = dao.update(obj);
        this.lista = dao.findAll();
        return out;
    }

    @Override
    public List<Endereco> buscarTodos() {
        this.lista = dao.findAll();
        return lista;
    }

    public Endereco buscarPorEndereco(Endereco obj) {
        for (Endereco e : lista) {
            if (e.equals(obj)) {
                return e;
            }
        }
        return null;
    }

    public Endereco gerarEnderecoCompleto(int numero, String rua, String cidade) {
        Endereco end;// = new Endereco(-1, numero, new Rua(-1, rua, "", new Bairro(-1, "", new Cidade(-1, cidade, new Estado(-1, "", "")))));
        end = gerarEnderecoUsandoGMaps(rua + "," + numero + "," + cidade);
        if (lista.contains(end)) {
            end = buscarPorEndereco(end);
        }
        else
        {
            this.adicionar(end);
        }

        return end;
    }

    private Endereco gerarEnderecoUsandoGMaps(String endereco) {
        GMapsObject maps = new GMapsObject(endereco);
        String dataEndereco[] = null;
        dataEndereco = maps.getGMapsEnd().split(",");

        String nomeCidade = (dataEndereco[2].split("-"))[0].trim();
        String nomeEstado = (dataEndereco[2].split("-"))[1].trim();
        String nomeRua = dataEndereco[0].trim();
        String cep = dataEndereco[3].substring(0, 6) + dataEndereco[3].substring(7);
        int numero = Integer.parseInt((dataEndereco[1].split("-"))[0].trim());
        String nomeBairro;
        try{
            nomeBairro = (dataEndereco[1].split("-"))[1].trim();
        }
        catch (Exception e)
        {
            nomeBairro = "";
        }

        CidadeControll cidadeCtr = new CidadeControll();
        Cidade cidade = cidadeCtr.buscarPorNomeCidade(nomeCidade);
        if (cidade == null) {
            EstadoControll estadoCtr = new EstadoControll();
            cidade = new Cidade(-1, nomeCidade, estadoCtr.buscarPorNomeEstado(nomeEstado));
            cidadeCtr.adicionar(cidade);
//            cidade = cidadeCtr.buscarPorNomeCidade(nomeCidade);
        }

        BairroContoll bairroCtr = new BairroContoll();
        Bairro bairro = bairroCtr.buscarPorNomeBairro(nomeBairro);

        if (bairro == null) {
            bairro = new Bairro(-1, nomeBairro, cidade);
            bairroCtr.adicionar(bairro);
//            bairro = bairroCtr.buscarPorNomeBairro(nomeBairro);
        }

        RuaControll ruaCtr = new RuaControll();
        Rua rua = ruaCtr.buscarPorNomeRuaOuCep(nomeRua, cep);

        if (rua == null) {
            rua = new Rua(-1, nomeRua, cep, bairro);
            ruaCtr.adicionar(rua);
//            rua = ruaCtr.buscarPorNomeRua(nomeRua);
        }

        return new Endereco(-1, numero, maps.getLatitude(), maps.getLongititude(), rua);
    }

    public void teste() {
        RuaControll rua = new RuaControll();
        Endereco obj = new Endereco(-1, 1108, rua.pesquisar(1));
        this.adicionar(obj);
        obj = this.pesquisar(1);
        obj.setNumero(1107);
        this.atualizar(obj);
        this.adicionar(new Endereco(-1, 115, rua.pesquisar(2)));
        this.adicionar(new Endereco(-1, 1108, rua.pesquisar(2)));
        for (Endereco e : buscarTodos()) {
            System.out.println(e);
        }
        this.excluir(3);
        for (Endereco e : buscarTodos()) {
            System.out.println(e);
        }
    }
}
