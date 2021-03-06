/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui.consultas;

import br.com.puc.gui.EnumMsg;
import br.com.puc.gui.consultas.cadastros.FrmEndereco;
import br.com.puc.gui.tablemodel.DimensionaTabela;
import br.com.puc.gui.tablemodel.EnderecoTableModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author adelannucci
 */
public class FrmEnderecoConsultaFiltrada extends javax.swing.JFrame {

    protected JFrame pai;
    protected EnderecoTableModel tableModel;

    /**
     * Creates new form FrmEnderecoConsulta
     */
    public FrmEnderecoConsultaFiltrada(JFrame pai) {
        initComponents();
        DimensionaTabela.dimensionaTabela(this.tableEndereco);
        this.tableModel = (EnderecoTableModel) tableEndereco.getModel();
        this.pai = pai;
    }

    public JFrame getPai() {
        return pai;
    }
    
        public void refresh() {
        this.lblSelecionado.setText("");
        this.tableEndereco.setModel(tableModel);
        this.tableEndereco.repaint();
        this.repaint();
    }
    
    private int getIdSelectedInTable()
    {
        return (int) tableEndereco.getValueAt(tableEndereco.getSelectedRow(), 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableEndereco = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblEnderecoSelecionadoInfo = new javax.swing.JLabel();
        lblSelecionado = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Endereços");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        tableEndereco.setModel(new EnderecoTableModel());
        tableEndereco.setColumnSelectionAllowed(true);
        tableEndereco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEnderecoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableEndereco);
        tableEndereco.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        lblEnderecoSelecionadoInfo.setText("Endereço Selecionado:");

        lblSelecionado.setText("Endereço");
        lblSelecionado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEnderecoSelecionadoInfo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEnderecoSelecionadoInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelecionado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnCadastrar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
        this.pai.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void tableEnderecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEnderecoMouseClicked
        String sigla = (String) tableEndereco.getValueAt(tableEndereco.getSelectedRow(), 1);
        this.lblSelecionado.setText(sigla+"");
    }//GEN-LAST:event_tableEnderecoMouseClicked

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        FrmEndereco frm = new FrmEndereco(this, tableModel, null, EnumMsg.Cadastrar.getmsg());
        this.setVisible(false);
        frm.setVisible(true);
        this.refresh();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int id;
        id = getIdSelectedInTable();
        FrmEndereco frm = new FrmEndereco(this, tableModel, tableModel.pesquisar(id), EnumMsg.Alterar.getmsg());
        this.setVisible(false);
        frm.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnAlterarActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblEnderecoSelecionadoInfo;
    private javax.swing.JLabel lblSelecionado;
    private javax.swing.JTable tableEndereco;
    // End of variables declaration//GEN-END:variables
}
