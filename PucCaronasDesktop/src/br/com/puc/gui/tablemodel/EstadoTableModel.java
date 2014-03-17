/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui.tablemodel;

import br.com.puc.controll.EstadoControll;
import br.com.puc.model.dao.table.Estado;

/**
 *
 * @author 12647202
 */
public class EstadoTableModel extends TableModel<Estado> {

    public EstadoTableModel() {
        colunas = new String[]{Estado.ID, Estado.NOME, Estado.SIGLA};
        control = new EstadoControll();
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
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Estado estado = control.getLista().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return estado.getId();
            case 1:
                return estado.getNomeEstado();
            case 2:
                return estado.getSiglaEstado();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    } 

}
