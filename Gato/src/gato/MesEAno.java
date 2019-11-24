package gato;

public class MesEAno {
    private final int mes;
    private final int ano;
    
    public MesEAno(int mes, int ano){
        this.ano = ano;
        this.mes = mes;
    }

    public int Dia1(){
        double A, B, C, D, E, F, G, H, I;
        int R;
        A = Math.floor((12 - mes) / 10);
        B = ano - A;
        C = mes + (12 * A);
        D = Math.floor(B / 100);
        E = Math.floor(D / 4);
        F = E + 2 - D;
        G = Math.floor(365.25 * B);
        H = Math.floor(30.6001 * (C + 1));
        I = F + G + H + 1 + 5;
        R = (int) I % 7;
        if(R == 0)
            return 7;
        else
            return R;
    }
    
    public int QuantidadeDeDias(){
        switch (mes) {
            case 1:
                return 31;
            case 2:
                if(ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0))
                    return 29;
                else
                    return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            default:
                return 31;
        }
    }
}
