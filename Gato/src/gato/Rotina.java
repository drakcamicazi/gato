/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**Classe que cria um objeto do tipo Calendario De Rotina.
 *@
 * @author USER
 */
public class Rotina {
    
    public static void main( String args[] ){
        CalendarioRotina cr;
        int[][] duracao = new int[25][8];
        String[][] infos = new String[25][8];
        int[] quantidade_eventos = new int[8];
        String[][] horarios = new String[25][8];//armazena a partir do 1 todos menos hora (00:00 = cod 0)
        
        //Os dias da semana comecam no domingo com codigo 1 e vao ate sabado codigo 7;
        //Os codigos do horario comecam da meia-noite "00:00" com codigo 0 até "24:00" com codigo 24;
        //Exemplos: duracao[i][j], i é o horario do dia em que o evento ocorre, j é o codigo do dia da semana;
        //o mesmo é válido para os outros parametros da classe (duracao, infos e horarios), somente quantidade de eventos que
        // precisa apenas do codigo do dia da semana.
        
        duracao[7][4] = 3;
        duracao[2][6] = 4;
        
        infos[7][4] = "Almoco";
        infos[2][6] = "Aula de LPP";
        
        quantidade_eventos[4] = 1;
        quantidade_eventos[6] = 1;
        
        horarios[7][4] = "12:00";
        horarios[2][6] = "11:00";
        
        cr = new CalendarioRotina();
        cr.setDuracao(duracao);
        cr.setInfos(infos);
        cr.setQuantidadeEventos(quantidade_eventos);
        cr.setQueHorasComeca(horarios);
        cr.addRowToJTable( quantidade_eventos, infos, horarios, duracao );
        cr.setVisible(true);
    }
}
