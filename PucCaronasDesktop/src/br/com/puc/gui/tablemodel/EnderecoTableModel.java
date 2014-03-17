/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui.tablemodel;

import br.com.puc.controll.EnderecoControll;
import br.com.puc.model.dao.table.Cidade;
import br.com.puc.model.dao.table.Endereco;
import br.com.puc.model.dao.table.Estado;

/**
 *
 * @author adelannucci
 */
public class EnderecoTableModel extends TableModel<Endereco> {

    public EnderecoTableModel() {
        colunas = new String[]{Endereco.ID, Endereco.NOME_TABLE, Cidade.NOME_TABLE, Estado.NOME_TABLE};
        control = new EnderecoControll();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return control.getLista().size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Endereco endereco = control.getLista().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return endereco.getId();
            case 1:
                return endereco.getEnderecoFormatado();
            case 2:
                return endereco.getRua().getBairro().getCidade().getNomeCidade();
            case 3:
                return endereco.getRua().getBairro().getCidade().getEstado().getNomeEstado();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public Endereco pesquisarEndereco(int numero, String rua, String cidade)
    {
        EnderecoControll ctr = (EnderecoControll) control;
        return ctr.gerarEnderecoCompleto(numero, rua, cidade);
        
    }
}
