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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author guscc
 */

public class FeriadosMoveis {
    
    public int diapascoa, mespascoa, diacarnaval, mescarnaval, diasextafeirasanta, messextafeirasanta, diacorpuschrist, mescorpuschrist;
  
    public FeriadosMoveis(int ano){
        
        this.diapascoa = diaPascoa(ano);
        this.mespascoa = mesPascoa(ano);
        this.diacarnaval = diaCarnaval(ano);
        this.mescarnaval = mesCarnaval(ano);
        this.diasextafeirasanta = diaSextaFeiraSanta(ano);
        this.messextafeirasanta = mesSextaFeiraSanta(ano);
        this.diacorpuschrist = diaCorpusChrist(ano);
        this.mescorpuschrist = mesCorpusChrist(ano);
    }
    
    public static int mesPascoa(int ano){
	
        int[] data = new int[2];
	int a = ano % 19;
	int b = ano / 100;
	int c = ano % 100;
	int d = b / 4;
	int e = b % 4;
	int f = (b + 8) / 25;
	int g = (b - f + 1) / 3;
	int h = (19 * a + b - d - g + 15) % 30;
	int i = c / 4;
	int k = c % 4;
	int l = (32 + 2 * e + 2 * i - h - k) % 7;
	int m = (a + 11 * h + 22 * l) / 451;
	int mes = (h + l - 7 * m + 114) / 31;
        return mes;
    }
    
     public static int diaPascoa(int ano){
	
        int[] data = new int[2];
	int a = ano % 19;
	int b = ano / 100;
	int c = ano % 100;
	int d = b / 4;
	int e = b % 4;
	int f = (b + 8) / 25;
	int g = (b - f + 1) / 3;
	int h = (19 * a + b - d - g + 15) % 30;
	int i = c / 4;
	int k = c % 4;
	int l = (32 + 2 * e + 2 * i - h - k) % 7;
	int m = (a + 11 * h + 22 * l) / 451;
	int dia = ((h + l - 7 * m + 114) % 31) + 1;

        return dia;
    }
     
    public static int diaCarnaval(int ano){
        int quant = 47;
        int mes;
        int dia;
        int diapascoa = FeriadosMoveis.diaPascoa(ano);
        int mespascoa = FeriadosMoveis.mesPascoa(ano);
        
        MesEAno var = new MesEAno(mespascoa - 1, ano);
        quant = quant - diapascoa;
        mes = mespascoa - 1;
        dia = var.QuantidadeDeDias();
        if(dia > quant) {
            dia = dia - quant;
        }
        else {
            quant = quant - dia;
            var = new MesEAno(mespascoa - 2, ano);
            dia = var.QuantidadeDeDias() - quant;
        }
        return dia;
    }
    
   public static int mesCarnaval(int ano){
        int quant = 47;
        int mes;
        int dia;
        int diapascoa = FeriadosMoveis.diaPascoa(ano);
        int mespascoa = FeriadosMoveis.mesPascoa(ano);
        
        MesEAno var = new MesEAno(mespascoa - 1, ano);
        quant = quant - diapascoa;
        mes = mespascoa - 1;
        dia = var.QuantidadeDeDias();
        if(! (dia > quant))
            mes--;
        return mes;
    }
   
   public static int mesSextaFeiraSanta(int ano){
        int mes;
        int diapascoa = FeriadosMoveis.diaPascoa(ano);
        int mespascoa = FeriadosMoveis.mesPascoa(ano);
        switch(diapascoa){
            case 2:
                return mespascoa - 1;
            case 1:
                return mespascoa - 1;
            default:
                return mespascoa;
        }
        
   }
   
   public static int diaSextaFeiraSanta(int ano){
        int dia;
        int diapascoa = FeriadosMoveis.diaPascoa(ano);
        int mespascoa = FeriadosMoveis.mesPascoa(ano);
        MesEAno var = new MesEAno(mespascoa - 1, ano);
        switch(diapascoa){
            case 2:
                return var.QuantidadeDeDias();
            case 1:
                return var.QuantidadeDeDias() - 1;
            default:
                return diapascoa - 2;
        }
        
   }
   
   public static int diaCorpusChrist(int ano){
        int quant = 60;
        int dia;
        int diapascoa = FeriadosMoveis.diaPascoa(ano);
        int mespascoa = FeriadosMoveis.mesPascoa(ano);
        
        MesEAno var = new MesEAno(mespascoa, ano);
        quant = quant - (var.QuantidadeDeDias() - diapascoa + 1);
        dia = 1;
        var = new MesEAno((mespascoa + 1), ano);
        if(quant < var.QuantidadeDeDias()) {
            dia = dia + quant;
        }
        else {
            quant = quant - var.QuantidadeDeDias() + 1;
            dia = quant;
        }
        return dia;
    }
    
   public static int mesCorpusChrist(int ano){
        int quant = 60;
        int mes;
        int diapascoa = FeriadosMoveis.diaPascoa(ano);
        int mespascoa = FeriadosMoveis.mesPascoa(ano);
        mes = mespascoa;
        MesEAno var = new MesEAno(mespascoa, ano);
        quant = quant - (var.QuantidadeDeDias() - diapascoa + 1);
        mes++;
        var = new MesEAno(mespascoa + 1, ano);
        if(!(quant < var.QuantidadeDeDias())) 
            mes++;
       
        return mes;  
   }    
}
