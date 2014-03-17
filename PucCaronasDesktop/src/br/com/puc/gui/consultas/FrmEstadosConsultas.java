/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui.consultas;

import br.com.puc.gui.EnumMsg;
import br.com.puc.gui.consultas.cadastros.FrmEstado;
import br.com.puc.gui.tablemodel.DimensionaTabela;
import br.com.puc.gui.tablemodel.EstadoTableModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author adelannucci
 */
public class FrmEstadosConsultas extends javax.swing.JFrame {

    protected JFrame pai;
    protected EstadoTableModel tableModel;

    /**
     * Creates new form FrmEstados
     */
    public FrmEstadosConsultas(JFrame pai) {
        initComponents();
        DimensionaTabela.dimensionaTabela(this.tableEstados);
        this.tableModel = (EstadoTableModel) tableEstados.getModel();
        this.pai = pai;
    }

    public JFrame getPai() {
        return pai;
    }

    public void refresh() {
        this.lblSelecionado.setText("");
        this.tableEstados.setModel(tableModel);
        this.tableEstados.repaint();
        this.repaint();
    }

    private int getIdSelectedInTable() {
        return (int) tableEstados.getValueAt(tableEstados.getSelectedRow(), 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstados = new javax.swing.JTable();
        lblEstadoSelecionadoInfo = new javax.swing.JLabel();
        lblSelecionado = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblNome.setText("Estados");

        tableEstados.setModel(new EstadoTableModel());
        tableEstados.setColumnSelectionAllowed(true);
        tableEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEstadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEstados);
        tableEstados.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        lblEstadoSelecionadoInfo.setText("Estado Selecionado:");

        lblSelecionado.setText("Estado");
        lblSelecionado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(lblNome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEstadoSelecionadoInfo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCadastrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoSelecionadoInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecionado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnCadastrar))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int id;
        id = getIdSelectedInTable();

        Object[] options = {"Sim", "Não"};
        int excluir = JOptionPane.showOptionDialog(null,
                EnumMsg.ConfirmarExclusao, EnumMsg.Excluir.getmsg(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);

        if (excluir == 0) {
            if (tableModel.excluir(id)) {
                JOptionPane.showOptionDialog(this, EnumMsg.ExcluirSucesso.getmsg(),
                        EnumMsg.Excluir.getmsg(), JOptionPane.CLOSED_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
            } else {
                JOptionPane.showOptionDialog(this, EnumMsg.ExcluirErro.getmsg(),
                        EnumMsg.Excluir.getmsg(), JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, null, null);
            }
        }
        this.refresh();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int id;
        id = getIdSelectedInTable();
        FrmEstado frm = new FrmEstado(this, tableModel, tableModel.pesquisar(id), EnumMsg.Alterar.getmsg());
        this.setVisible(false);
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        FrmEstado frm = new FrmEstado(this, tableModel, null, EnumMsg.Cadastrar.getmsg());
        this.setVisible(false);
        frm.setVisible(true);
        this.refresh();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
        this.pai.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void tableEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEstadosMouseClicked
        String sigla = (String) tableEstados.getValueAt(tableEstados.getSelectedRow(), 2);
        this.lblSelecionado.setText(sigla + "");
    }//GEN-LAST:event_tableEstadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstadoSelecionadoInfo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSelecionado;
    private javax.swing.JTable tableEstados;
    // End of variables declaration//GEN-END:variables
}
