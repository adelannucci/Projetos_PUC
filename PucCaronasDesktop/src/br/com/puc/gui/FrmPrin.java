/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui;

import br.com.puc.controll.UsuarioControll;
import br.com.puc.gui.consultas.FrmEnderecoConsultaFiltrada;
import br.com.puc.gui.consultas.FrmEstadosConsultas;
import br.com.puc.model.dao.table.Usuario;

/**
 *
 * @author adelannucci
 */
public class FrmPrin extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrin
     */
    public FrmPrin() {
        initComponents();
        logado(0);

    }

    private void logado(int logado) {
        boolean log = false;
        if (logado == 1) {
            log = false;
            this.btnEntrar.setVisible(log);
            this.lblSenha.setVisible(log);
            this.lblUsuario.setVisible(log);
            this.txtSenha.setVisible(log);
            this.txtUsuario.setVisible(log);
            
            this.btnSair.setEnabled(!log);
            this.btnSair.setVisible(!log);
            this.btnEstados.setVisible(!log);
            this.btnEstados.setEnabled(!log);
            this.btnEndereco.setVisible(!log);
            this.btnEndereco.setEnabled(!log);
        } else {
            log = true;
            this.btnEntrar.setVisible(log);
            this.lblSenha.setVisible(log);
            this.lblUsuario.setVisible(log);
            this.txtSenha.setVisible(log);
            this.txtUsuario.setVisible(log);
            
            this.btnSair.setEnabled(!log);
            this.btnSair.setVisible(!log);
            this.btnEstados.setVisible(!log);
            this.btnEstados.setEnabled(!log);
            this.btnEndereco.setVisible(!log);
            this.btnEndereco.setEnabled(!log);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEstados = new javax.swing.JButton();
        lblSenha = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnSair = new javax.swing.JButton();
        btnEndereco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnEstados.setText("Estados");
        btnEstados.setEnabled(false);
        btnEstados.setPreferredSize(new java.awt.Dimension(158, 25));
        btnEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadosActionPerformed(evt);
            }
        });

        lblSenha.setText("Senha:");

        txtUsuario.setName("txtUsuario"); // NOI18N

        btnEntrar.setText("Entrar");
        btnEntrar.setPreferredSize(new java.awt.Dimension(158, 25));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        lblUsuario.setText("Usuário:");

        btnSair.setText("Sair");
        btnSair.setPreferredSize(new java.awt.Dimension(158, 25));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnEndereco.setText("Endereços");
        btnEndereco.setEnabled(false);
        btnEndereco.setPreferredSize(new java.awt.Dimension(158, 25));
        btnEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnderecoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuario))
                            .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSenha)))
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadosActionPerformed
        FrmEstadosConsultas est = new FrmEstadosConsultas(this);
        est.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEstadosActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        UsuarioControll usr = new UsuarioControll();
        Usuario login = usr.logar(txtUsuario.getText(), new String(txtSenha.getPassword()));
        logado(login.getPerfil().getTipo());
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        logado(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnderecoActionPerformed
        FrmEnderecoConsultaFiltrada end;
        end = new FrmEnderecoConsultaFiltrada(this);
        end.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEnderecoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEndereco;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnEstados;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
