/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalendarioRotina;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class CalendarioRotina extends javax.swing.JFrame {
    private int num = 0;
    private String dia = "Segunda";
    
    /**
     * Creates new form CalendarioRotina
     */
    public CalendarioRotina( int num, String dia, String[] infos, String[] queHorasComeca, String[] queHorasTermina ) { // Preciso de quantos eventos vao ser adicionados em um dia, um vetor com os nomes destes(em ordem de horario das 00:00 ate 24:00), um vetor de qual hora esse evento ocorre e qual dia( ex: Segunda )
        this.num = num;
        this.dia = dia;
        infos = new String[num];
        for(int i = 0; i <= num; i++){
            int[i] horasInicio = QueHoras(String queHorasComeca);
            int[i] horasFim = QueHoras(String queHorasTermina);
        }
        
        initComponents();
        addRowToJTable( num, dia, infos, horasInicio, horasFim );
    }
    
    public int QueHoras( String queHoras ){
        int hora = 0;
        
        switch( queHoras ){
            case "00:00": hora = 0;
            case "01:00": hora = 1;
            case "02:00": hora = 2;
            case "03:00": hora = 3;
            case "04:00": hora = 4;
            case "05:00": hora = 5;
            case "06:00": hora = 6;
            case "07:00": hora = 7;
            case "08:00": hora = 8;
            case "09:00": hora = 9;
            case "10:00": hora = 10;
            case "11:00": hora = 11;
            case "12:00": hora = 12;
            case "13:00": hora = 13;
            case "14:00": hora = 14;
            case "15:00": hora = 15;
            case "16:00": hora = 16;
            case "17:00": hora = 17;
            case "18:00": hora = 18;
            case "19:00": hora = 19;
            case "20:00": hora = 20;
            case "21:00": hora = 21;
            case "22:00": hora = 22;
            case "23:00": hora = 23;
            case "24:00": hora = 24;
        } 
        return hora;
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
    
    // Le a string do dia (ex: segunda) em o numero de acordo na tabela
    public int AddToTabela(String dia){ 
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int dia_num = 0;
        
        switch(dia){
            case "Domingo":
            case "domingo": dia_num = 1;
            
            case "Segunda":
            case "segunda": dia_num = 2;
            
            case "Terça":
            case "terça": 
            case "Terca":
            case "terca":  dia_num = 3;
            
            case "Quarta":
            case "quarta": dia_num = 4;
            
            case "Quinta":
            case "quinta": dia_num = 5;
            
            case "Sexta":
            case "sexta": dia_num = 6;
            
            case "Sábado":
            case "sábado": 
            case "Sabado":
            case "sabado": dia_num = 7;
        }
        return dia_num;
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
        
    public void addRowToJTable( int num, String dia, String[] infos, int[] horasInicio, int[] horasFim){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<Horas> listH = ListHoras();
        Object rowData[] = new Object[ num + 1];
        for(int i = 0; i < listH.size(); i++){
            rowData[0] = listH.get(i).horario;
            model.addRow(rowData);
        }
            
        for( int i = 0; i <= num; i++ ){
            if( i == horasInicio[]){
                model.setValueAt(infos[i], i, AddToTabela(dia));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
