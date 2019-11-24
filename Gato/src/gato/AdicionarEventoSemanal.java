/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class AdicionarEventoSemanal extends javax.swing.JFrame {

    /**
     * Creates new form AdicionarEventoSemanal
     */
    public AdicionarEventoSemanal() {
        initComponents();
    }
    
    private void salvar(){
        String url = "jdbc:mysql://localhost/gato?useSSL=false", usuario = "root", senha = "root";
        Connection conexao;
        PreparedStatement pstm;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdicionarEventoSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String titulo, horaIni, horaFin, minIni, minFin, diaSemana;
        titulo = TextTitulo.getText();
        diaSemana = BoxDiaDaSemana.getSelectedItem().toString();
        horaIni = BoxHoraDeInicio.getSelectedItem().toString();
        minIni = BoxMinDeInicio.getSelectedItem().toString();
        horaFin = BoxHoraDeFim.getSelectedItem().toString();
        minFin = BoxMinDeFim.getSelectedItem().toString();
        
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            pstm = conexao.prepareStatement("insert into Evento_Semanal values (null, ?, ?, ?, ?)");

            pstm.setString(1, titulo);
            pstm.setString(2, diaSemana);
            pstm.setString(3, horaIni +":"+ minIni);
            pstm.setString(4, horaFin +":"+ minFin);
            
            pstm.execute();
            pstm.close();
            conexao.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar.");
            System.err.println(excp);
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

        TextTitulo = new javax.swing.JTextField();
        LabelTitulo = new javax.swing.JLabel();
        BoxDiaDaSemana = new javax.swing.JComboBox<>();
        BoxHoraDeInicio = new javax.swing.JComboBox<>();
        BoxMinDeInicio = new javax.swing.JComboBox<>();
        BoxHoraDeFim = new javax.swing.JComboBox<>();
        BoxMinDeFim = new javax.swing.JComboBox<>();
        LabelHorarioDeInicio = new javax.swing.JLabel();
        LabelHorarioDeFim = new javax.swing.JLabel();
        BotaoCancelar = new javax.swing.JButton();
        BotaoAvancar = new javax.swing.JButton();
        Label2PontosHI = new javax.swing.JLabel();
        Label2PontosHF = new javax.swing.JLabel();
        LabelDiaDaSemana = new javax.swing.JLabel();
        BotaoApagarEvento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Evento Semanal");
        setPreferredSize(new java.awt.Dimension(640, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(TextTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 38, 567, 27));

        LabelTitulo.setText("Título");
        getContentPane().add(LabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 18, -1, -1));

        BoxDiaDaSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" }));
        getContentPane().add(BoxDiaDaSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 140, -1));

        BoxHoraDeInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        getContentPane().add(BoxHoraDeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 58, -1));

        BoxMinDeInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00" }));
        BoxMinDeInicio.setEnabled(false);
        BoxMinDeInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxMinDeInicioActionPerformed(evt);
            }
        });
        getContentPane().add(BoxMinDeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 60, -1));

        BoxHoraDeFim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        BoxHoraDeFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxHoraDeFimActionPerformed(evt);
            }
        });
        getContentPane().add(BoxHoraDeFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 58, -1));

        BoxMinDeFim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00" }));
        BoxMinDeFim.setEnabled(false);
        BoxMinDeFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxMinDeFimActionPerformed(evt);
            }
        });
        getContentPane().add(BoxMinDeFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 60, -1));

        LabelHorarioDeInicio.setText("Horário de inicio");
        getContentPane().add(LabelHorarioDeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        LabelHorarioDeFim.setText("Horário de fim");
        getContentPane().add(LabelHorarioDeFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        BotaoCancelar.setText("Cancelar");
        BotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(BotaoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, -1));

        BotaoAvancar.setText("Avançar");
        BotaoAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAvancarActionPerformed(evt);
            }
        });
        getContentPane().add(BotaoAvancar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 100, -1));

        Label2PontosHI.setText(":");
        getContentPane().add(Label2PontosHI, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        Label2PontosHF.setText(":");
        getContentPane().add(Label2PontosHF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        LabelDiaDaSemana.setText("Dia da semana");
        getContentPane().add(LabelDiaDaSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        BotaoApagarEvento.setText("Apagar Evento");
        BotaoApagarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoApagarEventoActionPerformed(evt);
            }
        });
        getContentPane().add(BotaoApagarEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** 
     * Fechar apenas a janela atual
     */
    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
        AdicionarEventoSemanal.this.dispose(); 
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void BoxHoraDeFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxHoraDeFimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxHoraDeFimActionPerformed

    private void BoxMinDeInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxMinDeInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxMinDeInicioActionPerformed

    /** 
     * Gatilho do botão Avançar para chamar a função salvar()
     */
    private void BotaoAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAvancarActionPerformed
        salvar();        
    }//GEN-LAST:event_BotaoAvancarActionPerformed

    private void BotaoApagarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApagarEventoActionPerformed
        DeletarSemanal ds = new DeletarSemanal();
        ds.setVisible(true);
    }//GEN-LAST:event_BotaoApagarEventoActionPerformed

    private void BoxMinDeFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxMinDeFimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxMinDeFimActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdicionarEventoSemanal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AdicionarEventoSemanal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoApagarEvento;
    private javax.swing.JButton BotaoAvancar;
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JComboBox<String> BoxDiaDaSemana;
    private javax.swing.JComboBox<String> BoxHoraDeFim;
    private javax.swing.JComboBox<String> BoxHoraDeInicio;
    private javax.swing.JComboBox<String> BoxMinDeFim;
    private javax.swing.JComboBox<String> BoxMinDeInicio;
    private javax.swing.JLabel Label2PontosHF;
    private javax.swing.JLabel Label2PontosHI;
    private javax.swing.JLabel LabelDiaDaSemana;
    private javax.swing.JLabel LabelHorarioDeFim;
    private javax.swing.JLabel LabelHorarioDeInicio;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JTextField TextTitulo;
    // End of variables declaration//GEN-END:variables
}
