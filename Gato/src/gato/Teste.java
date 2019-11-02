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

import java.sql.SQLException;

/**
 *
 * @author drakcamicazi
 */
public class Teste {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Database db = new Database();        
        db.conectar();
        db.setStm(db.getConexao().createStatement());
        
        //"comando" a ser enviado para o SGBD:
        db.setRs(db.getStm().executeQuery("select * from Evento_Semanal order by dia_semana asc, hora_inicio asc; ")); 
        
        //printa todas as entradas da coluna 2 (título, no caso)
        while(db.getRs().next()){
            System.out.println(db.getRs().getString(2));
        }
    }
    
}

