/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalendarioRotina;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**Calendario de Rotinas 
 *
 * @author USER
 */
public class CalendarioRotina extends javax.swing.JFrame {
    private int[] quantidade_eventos = new int[8];
    private String[][] infos = new String[25][8];
    private int[][] duracao = new int[25][8];
    private int[][] codigoHoras = new int[25][8];
    private String[][] queHorasComeca = new String[25][8];
    
    public void setQuantidadeEventos( int[] qntd ){
        quantidade_eventos = qntd;
    }
    
    public void setInfos( String[][] infos ){
        this.infos = infos;
    }
    
    public void setDuracao( int[][] duracao ){
        this.duracao = duracao;
    }
    
    public void setQueHorasComeca( String[][] queHorasComeca ){
        this.queHorasComeca = queHorasComeca;
    }
    
    public int[] getQuantidadeEventos(){
        return quantidade_eventos;
    }
    
    public String[][] getInfos(){
        return infos;
    }
    
    public int[][] getDuracao(){
        return duracao;
    }
    
    public int[][] getCodigoHoras(){
        return codigoHoras;
    }
    
    public String[][] getQueHorasComeca(){
        return queHorasComeca;
    }
    /**
     * Creates new form CalendarioRotina
     */
    public CalendarioRotina() { // Preciso de quantos eventos vao ser adicionados em cada dia, um vetor com os nomes destes(em ordem de horario das 00:00 ate 24:00), um vetor de qual hora esse evento ocorre e qual dia( ex: Segunda )
        
        initComponents();
    }
    
    public int QueHoras( String queHorasComeca ){
        int hora = 0;
        String horario = queHorasComeca;
        
        if(horario == null){
            horario = "";
        }
        
        switch( horario ){
            case "00:00": hora = 0;
                          break;
            case "01:00": hora = 1;
                          break;
            case "02:00": hora = 2;
                          break;
            case "03:00": hora = 3;
                          break;
            case "04:00": hora = 4;
                          break;
            case "05:00": hora = 5;
                          break;
            case "06:00": hora = 6;
                          break;
            case "07:00": hora = 7;
                          break;
            case "08:00": hora = 8;
                          break;
            case "09:00": hora = 9;
                          break;
            case "10:00": hora = 10;
                          break;
            case "11:00": hora = 11;
                          break;
            case "12:00": hora = 12;
                          break;
            case "13:00": hora = 13;
                          break;
            case "14:00": hora = 14;
                          break;
            case "15:00": hora = 15;
                          break;
            case "16:00": hora = 16;
                          break;
            case "17:00": hora = 17;
                          break;
            case "18:00": hora = 18;
                          break;
            case "19:00": hora = 19;
                          break;
            case "20:00": hora = 20;
                          break;
            case "21:00": hora = 21;
                          break;
            case "22:00": hora = 22;
                          break;
            case "23:00": hora = 23;
                          break;
            case "24:00": hora = 24;
                          break;
            default: hora = -1;
                     break;
        } 
        return hora;
    }
    
    public void CodigosHoras( String[][] queHorasComeca ){
        for(int i = 1; i < 8; i++){
            for(int j = 0; j < 25; j++){   
               codigoHoras[j][i] = QueHoras(queHorasComeca[j][i]);
            }
        }
    }
    
    public class Horas{
        private String horario;
    
        public Horas(String Horario){
        this.horario = Horario;
        }
    }
    
    public class Info{
        private String informacao;
        
        public Info(String Informacao){
            this.informacao = Informacao;
        }
    }   
    
    public ArrayList ListHoras(){
        ArrayList<Horas> listH = new ArrayList<>();
        Horas h00 = new Horas("00:00");
        Horas h01 = new Horas("01:00");
        Horas h02 = new Horas("02:00");
        Horas h03 = new Horas("03:00");
        Horas h04 = new Horas("04:00");
        Horas h05 = new Horas("05:00");
        Horas h06 = new Horas("06:00");
        Horas h07 = new Horas("07:00");
        Horas h08 = new Horas("08:00");
        Horas h09 = new Horas("09:00");
        Horas h10 = new Horas("10:00");
        Horas h11 = new Horas("11:00");
        Horas h12 = new Horas("12:00");
        Horas h13 = new Horas("13:00");
        Horas h14 = new Horas("14:00");
        Horas h15 = new Horas("15:00");
        Horas h16 = new Horas("16:00");
        Horas h17 = new Horas("17:00");
        Horas h18 = new Horas("18:00");
        Horas h19 = new Horas("19:00");
        Horas h20 = new Horas("20:00");
        Horas h21 = new Horas("21:00");
        Horas h22 = new Horas("22:00");
        Horas h23 = new Horas("23:00");
        Horas h24 = new Horas("24:00");
        listH.add(h00);
        listH.add(h01);
        listH.add(h02);
        listH.add(h03);
        listH.add(h04);
        listH.add(h05);
        listH.add(h06);
        listH.add(h07);
        listH.add(h08);
        listH.add(h09);
        listH.add(h10);
        listH.add(h11);
        listH.add(h12);
        listH.add(h13);
        listH.add(h14);
        listH.add(h15);
        listH.add(h16);
        listH.add(h17);
        listH.add(h18);
        listH.add(h19);
        listH.add(h20);
        listH.add(h21);
        listH.add(h22);
        listH.add(h23);
        listH.add(h24);
        
        return listH;
    }
        
    public void addRowToJTable( int[] quantidade_eventos, String[][] infos, String[][] queHorasComeca, int[][] duracao){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<Horas> listH = ListHoras();
        Object rowData[] = new Object[1];
        for(int i = 0; i < listH.size(); i++){
            rowData[0] = listH.get(i).horario;
            model.addRow(rowData);
        }
        
        CodigosHoras(queHorasComeca);
        
        int k = 0;
        for(int i = 1; i < 8; i++){
            if( quantidade_eventos[i] > 0 ){
                for(int j = 0; j < 25; j++){
                    if( codigoHoras[j][i] != -1 ){
                        if(duracao[j][i] > 1){
                            for(k = 0; k < duracao[j][i]; k++){
                                model.setValueAt(infos[j][i], j + k, i);
                            }
                            j = j + k;

                        }else{
                            if(duracao[j][i] == 1){
                                model.setValueAt(infos[j][i], j, i);
                            }
                        }
                    }else{
                        model.setValueAt("", j, i);
                    }
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CalendarioRotina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarioRotina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarioRotina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarioRotina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalendarioRotina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
