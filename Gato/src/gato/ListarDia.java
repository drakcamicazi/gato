/*
 * Copyright (C) 2019 guscc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gato;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JPanel;

/**
 *
 * @author guscc
 */
public class ListarDia extends javax.swing.JFrame {
    Integer ano, dia, mes;
    
    /**
     * Creates new form ListarDia
     * @param dia
     * @param mes
     * @param ano
     */
    public ListarDia(Integer dia, Integer mes, Integer ano) {
        initComponents();
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        diaAtual.setText("   Data: " +dia + "/" + mes + "/" + ano);
        preencherListaEventos();
    }
    
    private void preencherListaEventos()
    {
        String url = "jdbc:mysql://localhost/gato?useSSL=false", usuario = "root", senha = "root";
        Connection conexao;
        PreparedStatement pstm;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeletarSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            pstm = conexao.prepareStatement("select pk_evento, titulo, descricao, hora_inicio, hora_fim, atividade, feriado, favorito from evento where dia = ? order by hora_inicio asc");
            pstm.setString(1, ano +"-"+ mes +"-"+ dia);
            
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                int cod = rs.getInt("pk_evento");
                
                JLabel l1 = new JLabel("(" + cod + ") "+rs.getString("titulo") + ", das " +rs.getString("hora_inicio") + " às " + rs.getString("hora_fim"));
                l1.setFont(l1.getFont().deriveFont(18.0f));
                JLabel l2 = new JLabel("    "+rs.getString("descricao"));
                l2.setFont(l2.getFont().deriveFont(14.0f));
                
                l1.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        deletar(cod);
                    }
                });
                listaEventos.add(l1);   
                listaEventos.add(l2);                
            }
            pstm.close();
            conexao.close();
        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar do banco.");
            System.err.println(excp);
        }
    }    
    
    private void deletar(int cod) {
        int op = JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar o evento " + cod + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (op == YES_OPTION) {
            String url = "jdbc:mysql://localhost/gato?useSSL=false", usuario = "root", senha = "root";
            Connection conexao;
            PreparedStatement pstm;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeletarSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                conexao = DriverManager.getConnection(url, usuario, senha);
                pstm = conexao.prepareStatement("delete from Evento where pk_evento = ?");

                pstm.setInt(1, cod);

                pstm.execute();
                pstm.close();
                conexao.close();

                JOptionPane.showMessageDialog(null, "Evento " + cod + " deletado com sucesso!");
            } catch (HeadlessException | SQLException excp) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar.");
                System.err.println(excp);
            }

            ListarDia.this.dispose();
            ListarDia ld = new ListarDia(dia, mes, ano);
            ld.setVisible(true);
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

        diaAtual = new javax.swing.JLabel();
        infoDeletar = new JLabel("Clique em um evento se desejar apagá-lo.");
        Fechar = new javax.swing.JButton();
        scrollEventos = new javax.swing.JScrollPane();
        listaEventos = new javax.swing.JPanel();
        divisorTitulos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        divisorTitulos.setLayout(new java.awt.GridLayout(1, 2));
        diaAtual.setFont(new java.awt.Font("Tahoma", 0, 24));
        infoDeletar.setFont(new java.awt.Font("Tahoma", 0, 20)); 
        diaAtual.setText("Dia");
        diaAtual.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        divisorTitulos.add(diaAtual);
        divisorTitulos.add(infoDeletar);
        
        getContentPane().add(divisorTitulos, java.awt.BorderLayout.NORTH);

        Fechar.setText("Fechar");
        Fechar.addActionListener((java.awt.event.ActionEvent evt) -> {
            FecharActionPerformed(evt);
        });
        getContentPane().add(Fechar, java.awt.BorderLayout.SOUTH);

        scrollEventos.setMinimumSize(new java.awt.Dimension(150, 350));
        scrollEventos.setPreferredSize(new java.awt.Dimension(1000, 350));

        listaEventos.setMaximumSize(new java.awt.Dimension(10000, 10000));
        listaEventos.setMinimumSize(new java.awt.Dimension(100, 100));
        listaEventos.setName(""); // NOI18N
        listaEventos.setPreferredSize(new java.awt.Dimension(900, 800));
        listaEventos.setLayout(new javax.swing.BoxLayout(listaEventos, javax.swing.BoxLayout.PAGE_AXIS));
        scrollEventos.setViewportView(listaEventos);

        getContentPane().add(scrollEventos, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharActionPerformed
        ListarDia.this.dispose();
    }//GEN-LAST:event_FecharActionPerformed

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
            java.util.logging.Logger.getLogger(ListarDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ListarDia(23, 11, 2019).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Fechar;
    private javax.swing.JLabel diaAtual;
    private javax.swing.JPanel listaEventos;
    private javax.swing.JScrollPane scrollEventos;
    private JPanel divisorTitulos;
    private JLabel infoDeletar;

    // End of variables declaration//GEN-END:variables
}