/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui.tablemodel;

import br.com.puc.controll.Controll;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 12647202
 */
public  abstract class TableModel<T> extends AbstractTableModel{
    
    protected String[] colunas;
    protected Controll<T> control;
    
    public boolean excluir(int id) {
        return control.excluir(id);
    }

    public T pesquisar(int id) {
        return control.pesquisar(id);
    }
    
    public boolean adicionar(T e) {
        return control.adicionar(e);
    }

    public boolean atualizar(T e) {
        return control.atualizar(e);
    }

    public List<T> buscarTodos() {
        return control.buscarTodos();
    }
}
