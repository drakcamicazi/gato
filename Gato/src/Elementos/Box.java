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
package Elementos;

import gato.Calendario;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author guscc
 */
public class Box extends JPanel{
    MouseListener m;
    public Box(){
        m = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                Calendario c = new Calendario();
            }
        };
        
        setBackground(Color.red);
        add(new Label("0"));
        addMouseListener(m);
    }
    public Box(Integer dia){
        m = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                Calendario c = new Calendario();
            }
        };
        
        setBackground(Color.red);
        add(new Label(dia.toString()));
        addMouseListener(m);
        
    }
};
