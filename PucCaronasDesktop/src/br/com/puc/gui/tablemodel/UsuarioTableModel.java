/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui.tablemodel;

import br.com.puc.controll.UsuarioControll;
import br.com.puc.model.dao.table.Perfil;
import br.com.puc.model.dao.table.Usuario;
import java.util.List;

/**
 *
 * @author 12647202
 */
public class UsuarioTableModel extends TableModel<Usuario> {

    public UsuarioTableModel()
    {
        colunas = new String[]{Usuario.ID, Usuario.LOGIN, Usuario.PASS, Usuario.ID_PERFIL};
        control = new UsuarioControll();
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
                return Perfil.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Usuario usu = control.getLista().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return usu.getId();
            case 1:
                return usu.getLogin();
            case 2:
                return usu.getPass();
            case 3:
                return usu.getPerfil();
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

    @Override
    public boolean excluir(int id) {
        return control.excluir(id);
    }

    @Override
    public Usuario pesquisar(int id) {
        return control.pesquisar(id);
    }

    @Override
    public boolean adicionar(Usuario obj) {
        return control.adicionar(obj);
    }

    @Override
    public boolean atualizar(Usuario obj) {
        return control.atualizar(obj);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return control.buscarTodos();
    }
    
}
