/*
 * Copyright (C) 2019 drakcamicazi
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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author drakcamicazi
 */

class Box extends JPanel{
    MouseListener m;
    public Box(Integer dia){
        m = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                Calendario c = new Calendario();
            }
        };
        setSize(50, 50);
        setBackground(Color.red);
        add(new Label(dia.toString()));
        addMouseListener(m);
        
    }
};
public class Calendario extends JFrame implements ActionListener{
    
    private JButton btnFechar = new JButton("Sair");
    private BorderLayout bl = new BorderLayout(50, 100);
    private DatePicker dp = new DatePicker();
    private JPanel pnlPrincipal;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Calendario(){
        pnlPrincipal = new JPanel(new GridLayout(6, 7));
        setVisible(true);
        setTitle("Calendario");
        setBounds(100, 100, 1400, 800);
 
        
        setLayout(bl);
        
        add(pnlPrincipal, BorderLayout.CENTER);
        add(new Label("Welcome!"), BorderLayout.NORTH);
        add(btnFechar, BorderLayout.SOUTH);
        
        btnFechar.addActionListener(this);
        
        
        pnlPrincipal.setMaximumSize(new Dimension(50, 50));
        pnlPrincipal.setBackground(java.awt.Color.blue);
        pnlPrincipal.add(new Box(1));
        pnlPrincipal.add(new Label("2"));
        pnlPrincipal.add(new Label("3"));
        pnlPrincipal.add(new Label("4"));
        pnlPrincipal.add(new Label("5"));
        pnlPrincipal.add(new Label("6"));
        pnlPrincipal.add(new Label("7"));
        pnlPrincipal.add(new Label("8"));
        pnlPrincipal.add(new Label("9"));
        pnlPrincipal.add(new Label("10"));
        pnlPrincipal.add(new Label("1"));
        pnlPrincipal.add(new Label("2"));
        pnlPrincipal.add(new Label("3"));
        pnlPrincipal.add(new Label("4"));
        pnlPrincipal.add(new Label("5"));
        pnlPrincipal.add(new Label("6"));
        pnlPrincipal.add(new Label("7"));
        pnlPrincipal.add(new Label("8"));
        pnlPrincipal.add(new Label("9"));
        pnlPrincipal.add(new Label("10"));
        pnlPrincipal.add(new Label("1"));
        pnlPrincipal.add(new Label("2"));
        pnlPrincipal.add(new Label("3"));
        pnlPrincipal.add(new Label("4"));
        pnlPrincipal.add(new Label("5"));
        pnlPrincipal.add(new Label("6"));
        pnlPrincipal.add(new Label("7"));
        pnlPrincipal.add(new Label("8"));
        pnlPrincipal.add(new Label("9"));
        pnlPrincipal.add(new Label("10"));
        pnlPrincipal.add(new Label("1"));
        pnlPrincipal.add(new Label("2"));
        pnlPrincipal.add(new Label("3"));
        pnlPrincipal.add(new Label("4"));
        pnlPrincipal.add(new Label("5"));
        pnlPrincipal.add(new Label("6"));
        pnlPrincipal.add(new Label("7"));
        pnlPrincipal.add(new Label("8"));
        pnlPrincipal.add(new Label("9"));
        pnlPrincipal.add(new Label("10"));
        pnlPrincipal.add(new Label("1"));
        pnlPrincipal.add(new Label("2"));
        pnlPrincipal.add(new Label("3"));
        pnlPrincipal.add(new Label("4"));
        pnlPrincipal.add(new Label("5"));
        pnlPrincipal.add(new Label("6"));
        pnlPrincipal.add(new Label("7"));
        pnlPrincipal.add(new Label("8"));
        pnlPrincipal.add(new Label("9"));
        pnlPrincipal.add(new Label("10"));
        pnlPrincipal.add(new Label("1"));
        pnlPrincipal.add(new Label("2"));
        pnlPrincipal.add(new Label("3"));
        pnlPrincipal.add(new Label("4"));
        pnlPrincipal.add(new Label("5"));
        pnlPrincipal.add(new Label("6"));
        pnlPrincipal.add(new Label("7"));
        pnlPrincipal.add(new Label("8"));
        pnlPrincipal.add(new Label("9"));
        pnlPrincipal.add(new Label("10"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnFechar)){
            System.exit(0);
        }
        
    }
        
    public static void main (String[] args){
        Calendario c = new Calendario();
    }
}
