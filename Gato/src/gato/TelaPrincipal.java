/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Admin
 */
public class TelaPrincipal extends javax.swing.JFrame {

    LocalDateTime hoje;
    int mesExibido, anoExibido;

    /**
     * Creates new form TelaDeInicio
     */
    public TelaPrincipal() {
        this.setExtendedState(this.getExtendedState() | TelaPrincipal.MAXIMIZED_BOTH);
        this.setTitle("G.A.T.O. - Aplicação de Gestão de Atividades, Trabalhos e Obrigações");
        inserirFeriadosMoveis();
        initComponents();
        hoje = LocalDateTime.now();
        mesExibido = hoje.getMonth().getValue();
        anoExibido = hoje.getYear();
        preencherCalendario(mesExibido, anoExibido);
        preencherListaEventosFav();
        preencherListaEventosAtiv();
        preencherRotina();
    }

    //inserirFeriadosMoveis insere todos os feriados de data não-fixa, se já não estiverem no banco
    public void inserirFeriadosMoveis() {
        String url = "jdbc:mysql://localhost/gato?useSSL=false", usuario = "root", senha = "root";
        Connection conexao;
        PreparedStatement pstm;
        FeriadosMoveis fm;
        ResultSet rs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdicionarEventoSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conexao = DriverManager.getConnection(url, usuario, senha);

            pstm = conexao.prepareStatement("select * from evento where descricao = 'Feriado Católico'");
            rs = pstm.executeQuery();
            if (rs.next() == false) {
                pstm.close();
                //adicionar feriados de 2013 até 2025
                for (int ano = 2013; ano <= 2025; ano++) {
                    fm = new FeriadosMoveis(ano);

                    //bloco para registar Carnaval
                    pstm = conexao.prepareStatement("insert into Evento values (null, ?, ?, '00:00', '23:00', ?, 0, 1, 0)");
                    pstm.setString(1, "Carnaval");
                    pstm.setString(2, ano + "-" + fm.mescarnaval + "-" + fm.diacarnaval);
                    pstm.setString(3, "Feriado Católico");
                    pstm.execute();
                    pstm.close();

                    //bloco para registar Corpus Christ
                    pstm = conexao.prepareStatement("insert into Evento values (null, ?, ?, '00:00', '23:00', ?, 0, 1, 0)");
                    pstm.setString(1, "Corpus Christ");
                    pstm.setString(2, ano + "-" + fm.mescorpuschrist + "-" + fm.diacorpuschrist);
                    pstm.setString(3, "Feriado Católico");
                    pstm.execute();
                    pstm.close();

                    //bloco para registar Pascoa
                    pstm = conexao.prepareStatement("insert into Evento values (null, ?, ?, '00:00', '23:00', ?, 0, 1, 0)");
                    pstm.setString(1, "Páscoa");
                    pstm.setString(2, ano + "-" + fm.mespascoa + "-" + fm.diapascoa);
                    pstm.setString(3, "Feriado Católico");
                    pstm.execute();
                    pstm.close();

                    //bloco para registar Sexta-Feira santa
                    pstm = conexao.prepareStatement("insert into Evento values (null, ?, ?, '00:00', '23:00', ?, 0, 1, 0)");
                    pstm.setString(1, "Sexta-Feira Santa");
                    pstm.setString(2, ano + "-" + fm.messextafeirasanta + "-" + fm.diasextafeirasanta);
                    pstm.setString(3, "Feriado Católico");
                    pstm.execute();
                    pstm.close();
                }
            }
            pstm.close();
            conexao.close();

        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar feriados.");
            System.err.println(excp);
        }

    }

    private void preencherRotina() {
        domingoPanel.removeAll();
        domingoPanel.revalidate();
        domingoPanel.repaint();

        //terminar de limpar pra todo os dis de semana-------------------------------------
        String[] l1 = new String[25], l2 = new String[25], l3 = new String[25], l4 = new String[25], l5 = new String[25], l6 = new String[25], l7 = new String[25];
        Arrays.fill(l1, " "); Arrays.fill(l2, " "); Arrays.fill(l3, " "); Arrays.fill(l4, " "); Arrays.fill(l5, " "); Arrays.fill(l6, " "); Arrays.fill(l7, " ");
        String url = "jdbc:mysql://localhost/gato?useSSL=false", usuario = "root", senha = "root";
        Connection conexao;
        PreparedStatement pstm1, pstm2, pstm3, pstm4, pstm5, pstm6, pstm7;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeletarSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            
            pstm1 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Domingo' order by hora_inicio asc");

            pstm1.execute();
            rs = pstm1.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l1[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l1[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l1[i]);
                JLabel lbl = new JLabel(l1[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                domingoPanel.add(lbl);
            }
            pstm1.close();
            System.err.println("Recuperou dia 1");
            
            pstm2 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Segunda' order by hora_inicio asc");

            pstm2.execute();
            rs = pstm2.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l2[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l2[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l2[i]);
                JLabel lbl = new JLabel(l2[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                segundaPanel.add(lbl);
            }
            pstm2.close();
            System.err.println("Recuperou dia 2");
            
            pstm3 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Terça' order by hora_inicio asc");

            pstm3.execute();
            rs = pstm3.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l3[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l3[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l3[i]);
                JLabel lbl = new JLabel(l3[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                tercaPanel.add(lbl);
            }
            pstm3.close();
            System.err.println("Recuperou dia 3");
            
            pstm4 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Quarta' order by hora_inicio asc");

            pstm4.execute();
            rs = pstm4.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l4[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l4[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l4[i]);
                JLabel lbl = new JLabel(l4[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                quartaPanel.add(lbl);
            }
            pstm4.close();
            System.err.println("Recuperou dia 4");
            
            
            pstm5 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Quinta' order by hora_inicio asc");

            pstm5.execute();
            rs = pstm5.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l5[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l5[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l5[i]);
                JLabel lbl = new JLabel(l5[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                quintaPanel.add(lbl);
            }
            pstm5.close();
            System.err.println("Recuperou dia 5");
            
            pstm6 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Sexta' order by hora_inicio asc");

            pstm6.execute();
            rs = pstm6.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l6[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l6[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l6[i]);
                JLabel lbl = new JLabel(l6[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                sextaPanel.add(lbl);
            }
            pstm6.close();
            System.err.println("Recuperou dia 1");
            
            pstm7 = conexao.prepareStatement("select titulo, hora_inicio, TIMEDIFF(hora_fim, hora_inicio) as duracao from evento_semanal where dia_semana = 'Sábado' order by hora_inicio asc");

            pstm7.execute();
            rs = pstm7.getResultSet();

            while (rs.next()) {
                int duracao = rs.getInt("duracao");
                int indice = rs.getTime("hora_inicio").toLocalTime().getHour();
                l7[indice] = rs.getString("titulo");
                while (duracao > 0) {
                    l7[indice + duracao - 1] = rs.getString("titulo");
                    duracao--;
                }
            }
            for (int i = 0; i <= 24; i++) {
                System.out.println(i + ": " + l7[i]);
                JLabel lbl = new JLabel(l7[i]);
                lbl.setAlignmentX((float)0.5);
                lbl.setAlignmentY((float)0.5);
                lbl.setFont(lbl.getFont().deriveFont(14.0f));            
                sabadoPanel.add(lbl);
            }
            pstm7.close();    
            System.err.println("Recuperou dia 7");       
            
            conexao.close();
        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar do banco.");
            System.err.println(excp);
        }

    }

    private void preencherCalendario(int mes, int ano) {
        MesEAno mea = new MesEAno(mes, ano);
        int i, j, numDia, desabilitados;

        TextoMes.setText(nomeDoMes(mes) + " " + ano);
        limparCalendario();

        for (desabilitados = 1; desabilitados < mea.Dia1(); desabilitados++) {
            desabilitarDia(1, desabilitados);
        }

        for (i = mea.Dia1(), j = 1, numDia = 1; i < mea.Dia1() + mea.QuantidadeDeDias(); i++, numDia++) {
            switch (i % 7) {
                case 0://se é sabado
                    preencherDia(numDia, j, 7);
                    break;
                case 1://se não é sabado
                    if (i != 1)//se o mes não começa em um domingo
                    {
                        j++;  //mudar a semana
                    }
                    preencherDia(numDia, j, i % 7);
                    break;
                default:
                    preencherDia(numDia, j, i % 7);
                    break;
            }
            if (numDia == hoje.getDayOfMonth() && mes == hoje.getMonth().getValue() && ano == hoje.getYear()) {
                getPanel(j, i % 7).setBackground(new Color(183, 247, 197));
            }
        }

        for (i = mea.QuantidadeDeDias() + desabilitados; i <= 42; i++) {
            switch (i % 7) {
                case 0://se é sabado
                    desabilitarDia(j, 7);
                    break;
                case 1://se não é sabado
                    j++;  //mudar a semana
                    desabilitarDia(j, i % 7);
                    break;
                default:
                    desabilitarDia(j, i % 7);
                    break;
            }
        }
    }

    private void desabilitarDia(int x, int y) {
        MouseListener[] ml = getPanel(x, y).getMouseListeners();
        getPanel(x, y).setBackground(new Color(180, 180, 180));
        for (MouseListener ml1 : ml) {
            getPanel(x, y).removeMouseListener(ml1);
        }
    }

    private void habilitarDia(int x, int y) {
        getPanel(x, y).setBackground(new Color(214, 217, 223));//cores default
        getPanel(x, y).addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
        });
    }

    private void preencherDia(Integer dia, int x, int y) {
        JPanel panelAtual = getPanel(x, y);
        panelAtual.setLayout(new BoxLayout(panelAtual, BoxLayout.PAGE_AXIS));
        JLabel aux = new JLabel(dia.toString());
        aux.setFont(aux.getFont().deriveFont(14.0f));
        panelAtual.add(aux);

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
            pstm = conexao.prepareStatement("select titulo, feriado from evento where dia = ? order by dia asc, hora_inicio asc");
            pstm.setString(1, anoExibido + "-" + mesExibido + "-" + dia);

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                JLabel l = new JLabel(rs.getString("titulo"));
                panelAtual.add(l);
                if (rs.getInt("feriado") == 1) {
                    panelAtual.setBackground(new Color(242, 249, 255));
                }
            }
            pstm.close();
            conexao.close();
        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar feriados no banco.");
            System.err.println(excp);
        }
        panelAtual.removeMouseListener(panelAtual.getMouseListeners()[0]);
        panelAtual.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirListarDia(dia, mesExibido, anoExibido);
            }
        });
    }

    private void limparCalendario() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 7; j++) {
                desabilitarDia(i, j);
                getPanel(i, j).removeAll();
                getPanel(i, j).revalidate();
                getPanel(i, j).repaint();
                habilitarDia(i, j);
            }
        }
    }

    private void abrirListarDia(Integer dia, Integer mes, Integer ano) {
        new ListarDia(dia, mes, ano).setVisible(true);
    }

    private String nomeDoMes(int mes) {
        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return null;
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

        PainelCalendario = new javax.swing.JPanel();
        Dias = new javax.swing.JPanel();
        diaPanel11 = new javax.swing.JPanel();
        diaPanel12 = new javax.swing.JPanel();
        diaPanel13 = new javax.swing.JPanel();
        diaPanel14 = new javax.swing.JPanel();
        diaPanel15 = new javax.swing.JPanel();
        diaPanel16 = new javax.swing.JPanel();
        diaPanel17 = new javax.swing.JPanel();
        diaPanel21 = new javax.swing.JPanel();
        diaPanel22 = new javax.swing.JPanel();
        diaPanel23 = new javax.swing.JPanel();
        diaPanel24 = new javax.swing.JPanel();
        diaPanel25 = new javax.swing.JPanel();
        diaPanel26 = new javax.swing.JPanel();
        diaPanel27 = new javax.swing.JPanel();
        diaPanel31 = new javax.swing.JPanel();
        diaPanel32 = new javax.swing.JPanel();
        diaPanel33 = new javax.swing.JPanel();
        diaPanel34 = new javax.swing.JPanel();
        diaPanel35 = new javax.swing.JPanel();
        diaPanel36 = new javax.swing.JPanel();
        diaPanel37 = new javax.swing.JPanel();
        diaPanel41 = new javax.swing.JPanel();
        diaPanel42 = new javax.swing.JPanel();
        diaPanel43 = new javax.swing.JPanel();
        diaPanel44 = new javax.swing.JPanel();
        diaPanel45 = new javax.swing.JPanel();
        diaPanel46 = new javax.swing.JPanel();
        diaPanel47 = new javax.swing.JPanel();
        diaPanel51 = new javax.swing.JPanel();
        diaPanel52 = new javax.swing.JPanel();
        diaPanel53 = new javax.swing.JPanel();
        diaPanel54 = new javax.swing.JPanel();
        diaPanel55 = new javax.swing.JPanel();
        diaPanel56 = new javax.swing.JPanel();
        diaPanel57 = new javax.swing.JPanel();
        diaPanel61 = new javax.swing.JPanel();
        diaPanel62 = new javax.swing.JPanel();
        diaPanel63 = new javax.swing.JPanel();
        diaPanel64 = new javax.swing.JPanel();
        diaPanel65 = new javax.swing.JPanel();
        diaPanel66 = new javax.swing.JPanel();
        diaPanel67 = new javax.swing.JPanel();
        Titulo = new javax.swing.JPanel();
        DiasSemana = new javax.swing.JPanel();
        Domingo = new javax.swing.JLabel();
        Segunda = new javax.swing.JLabel();
        Terça = new javax.swing.JLabel();
        Quarta = new javax.swing.JLabel();
        Quinta = new javax.swing.JLabel();
        Sexta = new javax.swing.JLabel();
        Sábado = new javax.swing.JLabel();
        TextoMes = new javax.swing.JLabel();
        arrowL = new javax.swing.JButton();
        arrowR = new javax.swing.JButton();
        InformacoesExtras = new javax.swing.JPanel();
        PainelBotoes = new javax.swing.JPanel();
        BotaoAtualizar = new javax.swing.JButton();
        BotaoAddEventoSemanal = new javax.swing.JButton();
        BotaoAddEvento = new javax.swing.JButton();
        BotaoRemoverSemanal = new javax.swing.JButton();
        BotaoRemoverEvento = new javax.swing.JButton();
        BotaoExpandirListas = new javax.swing.JButton();
        PainelTabelas = new javax.swing.JPanel();
        tabelaFavorito = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        textTitulo = new javax.swing.JLabel();
        scrollEventos = new javax.swing.JScrollPane();
        listaEventosFav = new javax.swing.JPanel();
        tabelaAtividades = new javax.swing.JPanel();
        scrollEventos2 = new javax.swing.JScrollPane();
        listaEventosAtiv = new javax.swing.JPanel();
        panelTitulo2 = new javax.swing.JPanel();
        textTitulo2 = new javax.swing.JLabel();
        painelRotina = new javax.swing.JPanel();
        rotinaScroll = new javax.swing.JScrollPane();
        rotinaPanel = new javax.swing.JPanel();
        tituloPanel = new javax.swing.JPanel();
        diasSemana = new javax.swing.JPanel();
        horariosTitulo = new javax.swing.JLabel();
        domingoTitulo = new javax.swing.JLabel();
        segundaTitulo = new javax.swing.JLabel();
        tercaTitulo = new javax.swing.JLabel();
        quartaTitulo = new javax.swing.JLabel();
        quintaTitulo = new javax.swing.JLabel();
        sextaTitulo = new javax.swing.JLabel();
        sabadoTitulo = new javax.swing.JLabel();
        diasSemana1 = new javax.swing.JPanel();
        horariosPanel = new javax.swing.JPanel();
        time0 = new javax.swing.JLabel();
        time1 = new javax.swing.JLabel();
        time2 = new javax.swing.JLabel();
        time3 = new javax.swing.JLabel();
        time4 = new javax.swing.JLabel();
        time5 = new javax.swing.JLabel();
        time6 = new javax.swing.JLabel();
        time7 = new javax.swing.JLabel();
        time8 = new javax.swing.JLabel();
        time9 = new javax.swing.JLabel();
        time10 = new javax.swing.JLabel();
        time11 = new javax.swing.JLabel();
        time12 = new javax.swing.JLabel();
        time13 = new javax.swing.JLabel();
        time14 = new javax.swing.JLabel();
        time15 = new javax.swing.JLabel();
        time16 = new javax.swing.JLabel();
        time17 = new javax.swing.JLabel();
        time18 = new javax.swing.JLabel();
        time19 = new javax.swing.JLabel();
        time20 = new javax.swing.JLabel();
        time21 = new javax.swing.JLabel();
        time22 = new javax.swing.JLabel();
        time23 = new javax.swing.JLabel();
        time24 = new javax.swing.JLabel();
        domingoPanel = new javax.swing.JPanel();
        segundaPanel = new javax.swing.JPanel();
        tercaPanel = new javax.swing.JPanel();
        quartaPanel = new javax.swing.JPanel();
        quintaPanel = new javax.swing.JPanel();
        sextaPanel = new javax.swing.JPanel();
        sabadoPanel = new javax.swing.JPanel();
        tituloLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PainelCalendario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PainelCalendario.setMinimumSize(new java.awt.Dimension(800, 800));
        PainelCalendario.setPreferredSize(new java.awt.Dimension(800, 800));
        PainelCalendario.setLayout(new java.awt.BorderLayout());

        Dias.setLayout(new java.awt.GridLayout(6, 7));

        diaPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel11Layout = new javax.swing.GroupLayout(diaPanel11);
        diaPanel11.setLayout(diaPanel11Layout);
        diaPanel11Layout.setHorizontalGroup(
            diaPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel11Layout.setVerticalGroup(
            diaPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel11);

        diaPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel12Layout = new javax.swing.GroupLayout(diaPanel12);
        diaPanel12.setLayout(diaPanel12Layout);
        diaPanel12Layout.setHorizontalGroup(
            diaPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel12Layout.setVerticalGroup(
            diaPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel12);

        diaPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel13Layout = new javax.swing.GroupLayout(diaPanel13);
        diaPanel13.setLayout(diaPanel13Layout);
        diaPanel13Layout.setHorizontalGroup(
            diaPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel13Layout.setVerticalGroup(
            diaPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel13);

        diaPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel14Layout = new javax.swing.GroupLayout(diaPanel14);
        diaPanel14.setLayout(diaPanel14Layout);
        diaPanel14Layout.setHorizontalGroup(
            diaPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel14Layout.setVerticalGroup(
            diaPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel14);

        diaPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel15Layout = new javax.swing.GroupLayout(diaPanel15);
        diaPanel15.setLayout(diaPanel15Layout);
        diaPanel15Layout.setHorizontalGroup(
            diaPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel15Layout.setVerticalGroup(
            diaPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel15);

        diaPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel16Layout = new javax.swing.GroupLayout(diaPanel16);
        diaPanel16.setLayout(diaPanel16Layout);
        diaPanel16Layout.setHorizontalGroup(
            diaPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel16Layout.setVerticalGroup(
            diaPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel16);

        diaPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel17Layout = new javax.swing.GroupLayout(diaPanel17);
        diaPanel17.setLayout(diaPanel17Layout);
        diaPanel17Layout.setHorizontalGroup(
            diaPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel17Layout.setVerticalGroup(
            diaPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel17);

        diaPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel21Layout = new javax.swing.GroupLayout(diaPanel21);
        diaPanel21.setLayout(diaPanel21Layout);
        diaPanel21Layout.setHorizontalGroup(
            diaPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel21Layout.setVerticalGroup(
            diaPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel21);

        diaPanel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel22Layout = new javax.swing.GroupLayout(diaPanel22);
        diaPanel22.setLayout(diaPanel22Layout);
        diaPanel22Layout.setHorizontalGroup(
            diaPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel22Layout.setVerticalGroup(
            diaPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel22);

        diaPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel23MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel23Layout = new javax.swing.GroupLayout(diaPanel23);
        diaPanel23.setLayout(diaPanel23Layout);
        diaPanel23Layout.setHorizontalGroup(
            diaPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel23Layout.setVerticalGroup(
            diaPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel23);

        diaPanel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel24Layout = new javax.swing.GroupLayout(diaPanel24);
        diaPanel24.setLayout(diaPanel24Layout);
        diaPanel24Layout.setHorizontalGroup(
            diaPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel24Layout.setVerticalGroup(
            diaPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel24);

        diaPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel25Layout = new javax.swing.GroupLayout(diaPanel25);
        diaPanel25.setLayout(diaPanel25Layout);
        diaPanel25Layout.setHorizontalGroup(
            diaPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel25Layout.setVerticalGroup(
            diaPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel25);

        diaPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel26MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel26Layout = new javax.swing.GroupLayout(diaPanel26);
        diaPanel26.setLayout(diaPanel26Layout);
        diaPanel26Layout.setHorizontalGroup(
            diaPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel26Layout.setVerticalGroup(
            diaPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel26);

        diaPanel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel27MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel27Layout = new javax.swing.GroupLayout(diaPanel27);
        diaPanel27.setLayout(diaPanel27Layout);
        diaPanel27Layout.setHorizontalGroup(
            diaPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel27Layout.setVerticalGroup(
            diaPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel27);

        diaPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel31MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel31Layout = new javax.swing.GroupLayout(diaPanel31);
        diaPanel31.setLayout(diaPanel31Layout);
        diaPanel31Layout.setHorizontalGroup(
            diaPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel31Layout.setVerticalGroup(
            diaPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel31);

        diaPanel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel32MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel32Layout = new javax.swing.GroupLayout(diaPanel32);
        diaPanel32.setLayout(diaPanel32Layout);
        diaPanel32Layout.setHorizontalGroup(
            diaPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel32Layout.setVerticalGroup(
            diaPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel32);

        diaPanel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel33MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel33Layout = new javax.swing.GroupLayout(diaPanel33);
        diaPanel33.setLayout(diaPanel33Layout);
        diaPanel33Layout.setHorizontalGroup(
            diaPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel33Layout.setVerticalGroup(
            diaPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel33);

        diaPanel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel34Layout = new javax.swing.GroupLayout(diaPanel34);
        diaPanel34.setLayout(diaPanel34Layout);
        diaPanel34Layout.setHorizontalGroup(
            diaPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel34Layout.setVerticalGroup(
            diaPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel34);

        diaPanel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel35Layout = new javax.swing.GroupLayout(diaPanel35);
        diaPanel35.setLayout(diaPanel35Layout);
        diaPanel35Layout.setHorizontalGroup(
            diaPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel35Layout.setVerticalGroup(
            diaPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel35);

        diaPanel36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel36Layout = new javax.swing.GroupLayout(diaPanel36);
        diaPanel36.setLayout(diaPanel36Layout);
        diaPanel36Layout.setHorizontalGroup(
            diaPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel36Layout.setVerticalGroup(
            diaPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel36);

        diaPanel37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel37MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel37Layout = new javax.swing.GroupLayout(diaPanel37);
        diaPanel37.setLayout(diaPanel37Layout);
        diaPanel37Layout.setHorizontalGroup(
            diaPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel37Layout.setVerticalGroup(
            diaPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel37);

        diaPanel41.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel41MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel41Layout = new javax.swing.GroupLayout(diaPanel41);
        diaPanel41.setLayout(diaPanel41Layout);
        diaPanel41Layout.setHorizontalGroup(
            diaPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel41Layout.setVerticalGroup(
            diaPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel41);

        diaPanel42.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel42MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel42Layout = new javax.swing.GroupLayout(diaPanel42);
        diaPanel42.setLayout(diaPanel42Layout);
        diaPanel42Layout.setHorizontalGroup(
            diaPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel42Layout.setVerticalGroup(
            diaPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel42);

        diaPanel43.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel43MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel43Layout = new javax.swing.GroupLayout(diaPanel43);
        diaPanel43.setLayout(diaPanel43Layout);
        diaPanel43Layout.setHorizontalGroup(
            diaPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel43Layout.setVerticalGroup(
            diaPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel43);

        diaPanel44.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel44MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel44Layout = new javax.swing.GroupLayout(diaPanel44);
        diaPanel44.setLayout(diaPanel44Layout);
        diaPanel44Layout.setHorizontalGroup(
            diaPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel44Layout.setVerticalGroup(
            diaPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel44);

        diaPanel45.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel45MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel45Layout = new javax.swing.GroupLayout(diaPanel45);
        diaPanel45.setLayout(diaPanel45Layout);
        diaPanel45Layout.setHorizontalGroup(
            diaPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel45Layout.setVerticalGroup(
            diaPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel45);

        diaPanel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel46MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel46Layout = new javax.swing.GroupLayout(diaPanel46);
        diaPanel46.setLayout(diaPanel46Layout);
        diaPanel46Layout.setHorizontalGroup(
            diaPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel46Layout.setVerticalGroup(
            diaPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel46);

        diaPanel47.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel47MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel47Layout = new javax.swing.GroupLayout(diaPanel47);
        diaPanel47.setLayout(diaPanel47Layout);
        diaPanel47Layout.setHorizontalGroup(
            diaPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel47Layout.setVerticalGroup(
            diaPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel47);

        diaPanel51.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel51MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel51Layout = new javax.swing.GroupLayout(diaPanel51);
        diaPanel51.setLayout(diaPanel51Layout);
        diaPanel51Layout.setHorizontalGroup(
            diaPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel51Layout.setVerticalGroup(
            diaPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel51);

        diaPanel52.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel52MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel52Layout = new javax.swing.GroupLayout(diaPanel52);
        diaPanel52.setLayout(diaPanel52Layout);
        diaPanel52Layout.setHorizontalGroup(
            diaPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel52Layout.setVerticalGroup(
            diaPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel52);

        diaPanel53.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel53MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel53Layout = new javax.swing.GroupLayout(diaPanel53);
        diaPanel53.setLayout(diaPanel53Layout);
        diaPanel53Layout.setHorizontalGroup(
            diaPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel53Layout.setVerticalGroup(
            diaPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel53);

        diaPanel54.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel54MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel54Layout = new javax.swing.GroupLayout(diaPanel54);
        diaPanel54.setLayout(diaPanel54Layout);
        diaPanel54Layout.setHorizontalGroup(
            diaPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel54Layout.setVerticalGroup(
            diaPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel54);

        diaPanel55.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel55MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel55Layout = new javax.swing.GroupLayout(diaPanel55);
        diaPanel55.setLayout(diaPanel55Layout);
        diaPanel55Layout.setHorizontalGroup(
            diaPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel55Layout.setVerticalGroup(
            diaPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel55);

        diaPanel56.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel56MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel56Layout = new javax.swing.GroupLayout(diaPanel56);
        diaPanel56.setLayout(diaPanel56Layout);
        diaPanel56Layout.setHorizontalGroup(
            diaPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel56Layout.setVerticalGroup(
            diaPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel56);

        diaPanel57.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel57MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel57Layout = new javax.swing.GroupLayout(diaPanel57);
        diaPanel57.setLayout(diaPanel57Layout);
        diaPanel57Layout.setHorizontalGroup(
            diaPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel57Layout.setVerticalGroup(
            diaPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel57);

        diaPanel61.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel61MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel61Layout = new javax.swing.GroupLayout(diaPanel61);
        diaPanel61.setLayout(diaPanel61Layout);
        diaPanel61Layout.setHorizontalGroup(
            diaPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel61Layout.setVerticalGroup(
            diaPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel61);

        diaPanel62.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel62MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel62Layout = new javax.swing.GroupLayout(diaPanel62);
        diaPanel62.setLayout(diaPanel62Layout);
        diaPanel62Layout.setHorizontalGroup(
            diaPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel62Layout.setVerticalGroup(
            diaPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel62);

        diaPanel63.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel63MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel63Layout = new javax.swing.GroupLayout(diaPanel63);
        diaPanel63.setLayout(diaPanel63Layout);
        diaPanel63Layout.setHorizontalGroup(
            diaPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel63Layout.setVerticalGroup(
            diaPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel63);

        diaPanel64.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel64MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel64Layout = new javax.swing.GroupLayout(diaPanel64);
        diaPanel64.setLayout(diaPanel64Layout);
        diaPanel64Layout.setHorizontalGroup(
            diaPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel64Layout.setVerticalGroup(
            diaPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel64);

        diaPanel65.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel65MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel65Layout = new javax.swing.GroupLayout(diaPanel65);
        diaPanel65.setLayout(diaPanel65Layout);
        diaPanel65Layout.setHorizontalGroup(
            diaPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel65Layout.setVerticalGroup(
            diaPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel65);

        diaPanel66.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel66MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel66Layout = new javax.swing.GroupLayout(diaPanel66);
        diaPanel66.setLayout(diaPanel66Layout);
        diaPanel66Layout.setHorizontalGroup(
            diaPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel66Layout.setVerticalGroup(
            diaPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel66);

        diaPanel67.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diaPanel67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaPanel67MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout diaPanel67Layout = new javax.swing.GroupLayout(diaPanel67);
        diaPanel67.setLayout(diaPanel67Layout);
        diaPanel67Layout.setHorizontalGroup(
            diaPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        diaPanel67Layout.setVerticalGroup(
            diaPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Dias.add(diaPanel67);

        PainelCalendario.add(Dias, java.awt.BorderLayout.CENTER);

        Titulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Titulo.setLayout(new java.awt.BorderLayout());

        DiasSemana.setBackground(new java.awt.Color(200, 200, 200));
        DiasSemana.setLayout(new java.awt.GridLayout(1, 0));

        Domingo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Domingo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Domingo.setText("Domingo");
        Domingo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Domingo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Domingo);

        Segunda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Segunda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Segunda.setText("Segunda");
        Segunda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Segunda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Segunda);

        Terça.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Terça.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Terça.setText("Terça");
        Terça.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Terça.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Terça);

        Quarta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Quarta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Quarta.setText("Quinta");
        Quarta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Quarta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Quarta);

        Quinta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Quinta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Quinta.setText("Quarta");
        Quinta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Quinta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Quinta);

        Sexta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Sexta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sexta.setText("Sexta");
        Sexta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Sexta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Sexta);

        Sábado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Sábado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sábado.setText("Sábado");
        Sábado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Sábado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DiasSemana.add(Sábado);

        Titulo.add(DiasSemana, java.awt.BorderLayout.SOUTH);

        TextoMes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TextoMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextoMes.setText("Mês");
        Titulo.add(TextoMes, java.awt.BorderLayout.CENTER);

        arrowL.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        arrowL.setText(" ← ");
        arrowL.setBorder(null);
        arrowL.setOpaque(false);
        arrowL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowLActionPerformed(evt);
            }
        });
        Titulo.add(arrowL, java.awt.BorderLayout.WEST);

        arrowR.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        arrowR.setText(" → ");
        arrowR.setBorder(null);
        arrowR.setOpaque(false);
        arrowR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowRActionPerformed(evt);
            }
        });
        Titulo.add(arrowR, java.awt.BorderLayout.LINE_END);

        PainelCalendario.add(Titulo, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(PainelCalendario, java.awt.BorderLayout.CENTER);

        InformacoesExtras.setMinimumSize(new java.awt.Dimension(389, 450));
        InformacoesExtras.setPreferredSize(new java.awt.Dimension(389, 450));
        InformacoesExtras.setLayout(new java.awt.GridLayout(3, 0));

        PainelBotoes.setLayout(new java.awt.GridLayout(6, 1));

        BotaoAtualizar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotaoAtualizar.setText("Atualizar");
        BotaoAtualizar.setToolTipText("");
        BotaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAtualizarActionPerformed(evt);
            }
        });
        PainelBotoes.add(BotaoAtualizar);

        BotaoAddEventoSemanal.setText("Adicionar Evento Semanal");
        BotaoAddEventoSemanal.setDefaultCapable(false);
        BotaoAddEventoSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAddEventoSemanalActionPerformed(evt);
            }
        });
        PainelBotoes.add(BotaoAddEventoSemanal);

        BotaoAddEvento.setText("Adicionar Evento");
        BotaoAddEvento.setDefaultCapable(false);
        BotaoAddEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAddEventoActionPerformed(evt);
            }
        });
        PainelBotoes.add(BotaoAddEvento);

        BotaoRemoverSemanal.setText("Remover Evento Semanal");
        BotaoRemoverSemanal.setToolTipText("");
        BotaoRemoverSemanal.setDefaultCapable(false);
        BotaoRemoverSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverSemanalActionPerformed(evt);
            }
        });
        PainelBotoes.add(BotaoRemoverSemanal);

        BotaoRemoverEvento.setText("Remover Evento do Calendário");
        BotaoRemoverEvento.setToolTipText("");
        BotaoRemoverEvento.setDefaultCapable(false);
        BotaoRemoverEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverEventoActionPerformed(evt);
            }
        });
        PainelBotoes.add(BotaoRemoverEvento);

        BotaoExpandirListas.setText("Expandir Favoritos / Atividades");
        BotaoExpandirListas.setToolTipText("");
        BotaoExpandirListas.setDefaultCapable(false);
        BotaoExpandirListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExpandirListasActionPerformed(evt);
            }
        });
        PainelBotoes.add(BotaoExpandirListas);

        InformacoesExtras.add(PainelBotoes);

        PainelTabelas.setBackground(new java.awt.Color(150, 150, 150));
        PainelTabelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PainelTabelas.setMinimumSize(new java.awt.Dimension(150, 150));
        PainelTabelas.setPreferredSize(new java.awt.Dimension(150, 150));
        PainelTabelas.setLayout(new java.awt.GridLayout(1, 0));

        tabelaFavorito.setBackground(new java.awt.Color(150, 150, 150));
        tabelaFavorito.setPreferredSize(new java.awt.Dimension(150, 264));
        tabelaFavorito.setLayout(new java.awt.BorderLayout());

        panelTitulo.setBackground(new java.awt.Color(200, 200, 200));
        panelTitulo.setLayout(new java.awt.BorderLayout());

        textTitulo.setBackground(new java.awt.Color(200, 200, 200));
        textTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        textTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textTitulo.setText("Favoritos");
        textTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelTitulo.add(textTitulo, java.awt.BorderLayout.CENTER);

        tabelaFavorito.add(panelTitulo, java.awt.BorderLayout.NORTH);

        scrollEventos.setMinimumSize(new java.awt.Dimension(150, 350));
        scrollEventos.setPreferredSize(new java.awt.Dimension(150, 350));

        listaEventosFav.setMaximumSize(new java.awt.Dimension(10000, 10000));
        listaEventosFav.setMinimumSize(new java.awt.Dimension(100, 100));
        listaEventosFav.setPreferredSize(new java.awt.Dimension(150, 800));
        listaEventosFav.setLayout(new javax.swing.BoxLayout(listaEventosFav, javax.swing.BoxLayout.PAGE_AXIS));
        scrollEventos.setViewportView(listaEventosFav);

        tabelaFavorito.add(scrollEventos, java.awt.BorderLayout.CENTER);

        PainelTabelas.add(tabelaFavorito);

        tabelaAtividades.setMinimumSize(new java.awt.Dimension(150, 394));
        tabelaAtividades.setLayout(new java.awt.BorderLayout());

        scrollEventos2.setMinimumSize(new java.awt.Dimension(150, 350));
        scrollEventos2.setPreferredSize(new java.awt.Dimension(150, 350));

        listaEventosAtiv.setMaximumSize(new java.awt.Dimension(10000, 10000));
        listaEventosAtiv.setMinimumSize(new java.awt.Dimension(100, 100));
        listaEventosAtiv.setPreferredSize(new java.awt.Dimension(150, 800));
        listaEventosAtiv.setLayout(new javax.swing.BoxLayout(listaEventosAtiv, javax.swing.BoxLayout.PAGE_AXIS));
        scrollEventos2.setViewportView(listaEventosAtiv);

        tabelaAtividades.add(scrollEventos2, java.awt.BorderLayout.CENTER);

        panelTitulo2.setBackground(new java.awt.Color(200, 200, 200));
        panelTitulo2.setLayout(new java.awt.BorderLayout());

        textTitulo2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        textTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textTitulo2.setText("Atividades");
        textTitulo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelTitulo2.add(textTitulo2, java.awt.BorderLayout.CENTER);

        tabelaAtividades.add(panelTitulo2, java.awt.BorderLayout.NORTH);

        PainelTabelas.add(tabelaAtividades);

        InformacoesExtras.add(PainelTabelas);

        painelRotina.setBackground(new java.awt.Color(200, 200, 200));
        painelRotina.setLayout(new java.awt.BorderLayout());

        rotinaScroll.setPreferredSize(new java.awt.Dimension(103, 1000));

        rotinaPanel.setMinimumSize(new java.awt.Dimension(100, 674));
        rotinaPanel.setPreferredSize(new java.awt.Dimension(1000, 475));
        rotinaPanel.setLayout(new java.awt.BorderLayout());

        tituloPanel.setMinimumSize(new java.awt.Dimension(892, 746));
        tituloPanel.setLayout(new java.awt.BorderLayout());

        diasSemana.setLayout(new java.awt.GridLayout());

        horariosTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        horariosTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        horariosTitulo.setText("Horários");
        diasSemana.add(horariosTitulo);

        domingoTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        domingoTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        domingoTitulo.setText("Domingo");
        diasSemana.add(domingoTitulo);

        segundaTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        segundaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        segundaTitulo.setText("Segunda");
        diasSemana.add(segundaTitulo);

        tercaTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tercaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tercaTitulo.setText("Terça");
        diasSemana.add(tercaTitulo);

        quartaTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        quartaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quartaTitulo.setText("Quarta");
        diasSemana.add(quartaTitulo);

        quintaTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        quintaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quintaTitulo.setText("Quinta");
        diasSemana.add(quintaTitulo);

        sextaTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sextaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sextaTitulo.setText("Sexta");
        diasSemana.add(sextaTitulo);

        sabadoTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sabadoTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sabadoTitulo.setText("Sábado");
        diasSemana.add(sabadoTitulo);

        tituloPanel.add(diasSemana, java.awt.BorderLayout.CENTER);

        rotinaPanel.add(tituloPanel, java.awt.BorderLayout.NORTH);

        diasSemana1.setPreferredSize(new java.awt.Dimension(800, 470));
        diasSemana1.setLayout(new java.awt.GridLayout());

        horariosPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        horariosPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        horariosPanel.setLayout(new javax.swing.BoxLayout(horariosPanel, javax.swing.BoxLayout.PAGE_AXIS));

        time0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time0.setText("00:00");
        time0.setAlignmentX(0.5F);
        horariosPanel.add(time0);

        time1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time1.setText("01:00");
        time1.setAlignmentX(0.5F);
        horariosPanel.add(time1);

        time2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time2.setText("02:00");
        time2.setAlignmentX(0.5F);
        horariosPanel.add(time2);

        time3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time3.setText("03:00");
        time3.setAlignmentX(0.5F);
        horariosPanel.add(time3);

        time4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time4.setText("04:00");
        time4.setAlignmentX(0.5F);
        horariosPanel.add(time4);

        time5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time5.setText("05:00");
        time5.setAlignmentX(0.5F);
        horariosPanel.add(time5);

        time6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time6.setText("06:00");
        time6.setAlignmentX(0.5F);
        horariosPanel.add(time6);

        time7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time7.setText("07:00");
        time7.setAlignmentX(0.5F);
        horariosPanel.add(time7);

        time8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time8.setText("08:00");
        time8.setAlignmentX(0.5F);
        horariosPanel.add(time8);

        time9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time9.setText("09:00");
        time9.setAlignmentX(0.5F);
        horariosPanel.add(time9);

        time10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time10.setText("10:00");
        time10.setAlignmentX(0.5F);
        horariosPanel.add(time10);

        time11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time11.setText("11:00");
        time11.setAlignmentX(0.5F);
        horariosPanel.add(time11);

        time12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time12.setText("12:00");
        time12.setAlignmentX(0.5F);
        horariosPanel.add(time12);

        time13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time13.setText("13:00");
        time13.setAlignmentX(0.5F);
        horariosPanel.add(time13);

        time14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time14.setText("14:00");
        time14.setAlignmentX(0.5F);
        horariosPanel.add(time14);

        time15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time15.setText("15:00");
        time15.setAlignmentX(0.5F);
        horariosPanel.add(time15);

        time16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time16.setText("16:00");
        time16.setAlignmentX(0.5F);
        horariosPanel.add(time16);

        time17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time17.setText("17:00");
        time17.setAlignmentX(0.5F);
        horariosPanel.add(time17);

        time18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time18.setText("18:00");
        time18.setAlignmentX(0.5F);
        horariosPanel.add(time18);

        time19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time19.setText("19:00");
        time19.setAlignmentX(0.5F);
        horariosPanel.add(time19);

        time20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time20.setText("20:00");
        time20.setAlignmentX(0.5F);
        horariosPanel.add(time20);

        time21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time21.setText("21:00");
        time21.setAlignmentX(0.5F);
        horariosPanel.add(time21);

        time22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time22.setText("22:00");
        time22.setAlignmentX(0.5F);
        horariosPanel.add(time22);

        time23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time23.setText("23:00");
        time23.setAlignmentX(0.5F);
        horariosPanel.add(time23);

        time24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time24.setText("24:00");
        time24.setAlignmentX(0.5F);
        horariosPanel.add(time24);

        diasSemana1.add(horariosPanel);

        domingoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        domingoPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        domingoPanel.setLayout(new javax.swing.BoxLayout(domingoPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(domingoPanel);

        segundaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        segundaPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        segundaPanel.setLayout(new javax.swing.BoxLayout(segundaPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(segundaPanel);

        tercaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        tercaPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        tercaPanel.setLayout(new javax.swing.BoxLayout(tercaPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(tercaPanel);

        quartaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        quartaPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        quartaPanel.setLayout(new javax.swing.BoxLayout(quartaPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(quartaPanel);

        quintaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        quintaPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        quintaPanel.setLayout(new javax.swing.BoxLayout(quintaPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(quintaPanel);

        sextaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        sextaPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        sextaPanel.setLayout(new javax.swing.BoxLayout(sextaPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(sextaPanel);

        sabadoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        sabadoPanel.setPreferredSize(new java.awt.Dimension(100, 447));
        sabadoPanel.setLayout(new javax.swing.BoxLayout(sabadoPanel, javax.swing.BoxLayout.PAGE_AXIS));
        diasSemana1.add(sabadoPanel);

        rotinaPanel.add(diasSemana1, java.awt.BorderLayout.CENTER);

        rotinaScroll.setViewportView(rotinaPanel);

        painelRotina.add(rotinaScroll, java.awt.BorderLayout.CENTER);

        tituloLabel.setBackground(new java.awt.Color(150, 150, 150));
        tituloLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLabel.setText("Calendário de Rotina");
        tituloLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        painelRotina.add(tituloLabel, java.awt.BorderLayout.NORTH);

        InformacoesExtras.add(painelRotina);

        getContentPane().add(InformacoesExtras, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void preencherListaEventosFav() {
        listaEventosFav.removeAll();
        listaEventosFav.revalidate();
        listaEventosFav.repaint();
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
            pstm = conexao.prepareStatement("select titulo from evento where dia > DATE(NOW()) and favorito = 1 order by dia asc, hora_inicio asc");

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                JLabel l = new JLabel(rs.getString("titulo"));
                l.setFont(l.getFont().deriveFont(16.0f));
                listaEventosFav.add(l);
            }
            pstm.close();
            conexao.close();
        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar do banco.");
            System.err.println(excp);
        }
    }

    private void preencherListaEventosAtiv() {
        listaEventosAtiv.removeAll();
        listaEventosAtiv.revalidate();
        listaEventosAtiv.repaint();
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
            pstm = conexao.prepareStatement("select titulo from evento where dia > DATE(NOW()) and atividade = 1 order by dia asc, hora_inicio asc");

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                JLabel l = new JLabel(rs.getString("titulo"));
                l.setFont(l.getFont().deriveFont(16.0f));
                listaEventosAtiv.add(l);
            }
            pstm.close();
            conexao.close();
        } catch (HeadlessException | SQLException excp) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar do banco.");
            System.err.println(excp);
        }
    }

    private void diaPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel11MouseClicked
    }//GEN-LAST:event_diaPanel11MouseClicked

    private void diaPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel12MouseClicked

    }//GEN-LAST:event_diaPanel12MouseClicked

    private void diaPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel13MouseClicked

    }//GEN-LAST:event_diaPanel13MouseClicked

    private void diaPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel14MouseClicked

    }//GEN-LAST:event_diaPanel14MouseClicked

    private void diaPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel15MouseClicked

    }//GEN-LAST:event_diaPanel15MouseClicked

    private void diaPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel16MouseClicked

    }//GEN-LAST:event_diaPanel16MouseClicked

    private void diaPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel17MouseClicked

    }//GEN-LAST:event_diaPanel17MouseClicked

    private void diaPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel21MouseClicked

    }//GEN-LAST:event_diaPanel21MouseClicked

    private void diaPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel22MouseClicked

    }//GEN-LAST:event_diaPanel22MouseClicked

    private void diaPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel23MouseClicked

    }//GEN-LAST:event_diaPanel23MouseClicked

    private void diaPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel24MouseClicked

    }//GEN-LAST:event_diaPanel24MouseClicked

    private void diaPanel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel25MouseClicked

    }//GEN-LAST:event_diaPanel25MouseClicked

    private void diaPanel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel26MouseClicked

    }//GEN-LAST:event_diaPanel26MouseClicked

    private void diaPanel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel27MouseClicked

    }//GEN-LAST:event_diaPanel27MouseClicked

    private void diaPanel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel31MouseClicked

    }//GEN-LAST:event_diaPanel31MouseClicked

    private void diaPanel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel32MouseClicked

    }//GEN-LAST:event_diaPanel32MouseClicked

    private void diaPanel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel33MouseClicked

    }//GEN-LAST:event_diaPanel33MouseClicked

    private void diaPanel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel34MouseClicked

    }//GEN-LAST:event_diaPanel34MouseClicked

    private void diaPanel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel35MouseClicked

    }//GEN-LAST:event_diaPanel35MouseClicked

    private void diaPanel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel36MouseClicked

    }//GEN-LAST:event_diaPanel36MouseClicked

    private void diaPanel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel37MouseClicked

    }//GEN-LAST:event_diaPanel37MouseClicked

    private void diaPanel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel41MouseClicked

    }//GEN-LAST:event_diaPanel41MouseClicked

    private void diaPanel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel42MouseClicked

    }//GEN-LAST:event_diaPanel42MouseClicked

    private void diaPanel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel43MouseClicked

    }//GEN-LAST:event_diaPanel43MouseClicked

    private void diaPanel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel44MouseClicked

    }//GEN-LAST:event_diaPanel44MouseClicked

    private void diaPanel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel45MouseClicked

    }//GEN-LAST:event_diaPanel45MouseClicked

    private void diaPanel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel46MouseClicked

    }//GEN-LAST:event_diaPanel46MouseClicked

    private void diaPanel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel47MouseClicked

    }//GEN-LAST:event_diaPanel47MouseClicked

    private void diaPanel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel51MouseClicked

    }//GEN-LAST:event_diaPanel51MouseClicked

    private void diaPanel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel52MouseClicked

    }//GEN-LAST:event_diaPanel52MouseClicked

    private void diaPanel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel53MouseClicked

    }//GEN-LAST:event_diaPanel53MouseClicked

    private void diaPanel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel54MouseClicked

    }//GEN-LAST:event_diaPanel54MouseClicked

    private void diaPanel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel55MouseClicked

    }//GEN-LAST:event_diaPanel55MouseClicked

    private void diaPanel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel56MouseClicked

    }//GEN-LAST:event_diaPanel56MouseClicked

    private void diaPanel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel57MouseClicked

    }//GEN-LAST:event_diaPanel57MouseClicked

    private void diaPanel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel61MouseClicked

    }//GEN-LAST:event_diaPanel61MouseClicked

    private void diaPanel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel62MouseClicked

    }//GEN-LAST:event_diaPanel62MouseClicked

    private void diaPanel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel63MouseClicked

    }//GEN-LAST:event_diaPanel63MouseClicked

    private void diaPanel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel64MouseClicked

    }//GEN-LAST:event_diaPanel64MouseClicked

    private void diaPanel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel65MouseClicked

    }//GEN-LAST:event_diaPanel65MouseClicked

    private void diaPanel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel66MouseClicked

    }//GEN-LAST:event_diaPanel66MouseClicked

    private void diaPanel67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaPanel67MouseClicked

    }//GEN-LAST:event_diaPanel67MouseClicked

    private void arrowLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowLActionPerformed
        // clicou pra esquerda, diminui um mês
        if (mesExibido == 1) {
            mesExibido = 12;
            anoExibido--;
        } else {
            mesExibido--;
        }
        preencherCalendario(mesExibido, anoExibido);
    }//GEN-LAST:event_arrowLActionPerformed

    private void arrowRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowRActionPerformed
        // clicou pra direita, aumenta um mês
        if (mesExibido == 12) {
            mesExibido = 1;
            anoExibido++;
        } else {
            mesExibido++;
        }
        preencherCalendario(mesExibido, anoExibido);
    }//GEN-LAST:event_arrowRActionPerformed

    private void BotaoAddEventoSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAddEventoSemanalActionPerformed
        new AdicionarEventoSemanal().setVisible(true);
    }//GEN-LAST:event_BotaoAddEventoSemanalActionPerformed

    private void BotaoAddEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAddEventoActionPerformed
        AdicionarEvento av = new AdicionarEvento();
        av.setVisible(true);

    }//GEN-LAST:event_BotaoAddEventoActionPerformed

    private void BotaoRemoverSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverSemanalActionPerformed
        new DeletarSemanal().setVisible(true);
    }//GEN-LAST:event_BotaoRemoverSemanalActionPerformed

    private void BotaoRemoverEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverEventoActionPerformed
        javax.swing.JOptionPane.showMessageDialog(null, "Clique no dia diretamente no calendário para expandir seus eventos e deletar.");
    }//GEN-LAST:event_BotaoRemoverEventoActionPerformed

    private void BotaoExpandirListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExpandirListasActionPerformed
        new ListasExpandidas().setVisible(true);
    }//GEN-LAST:event_BotaoExpandirListasActionPerformed

    private void BotaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAtualizarActionPerformed
        preencherListaEventosFav();
        preencherListaEventosAtiv();
        preencherCalendario(mesExibido, anoExibido);
    }//GEN-LAST:event_BotaoAtualizarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoAddEvento;
    private javax.swing.JButton BotaoAddEventoSemanal;
    private javax.swing.JButton BotaoAtualizar;
    private javax.swing.JButton BotaoExpandirListas;
    private javax.swing.JButton BotaoRemoverEvento;
    private javax.swing.JButton BotaoRemoverSemanal;
    private javax.swing.JPanel Dias;
    private javax.swing.JPanel DiasSemana;
    private javax.swing.JLabel Domingo;
    private javax.swing.JPanel InformacoesExtras;
    private javax.swing.JPanel PainelBotoes;
    private javax.swing.JPanel PainelCalendario;
    private javax.swing.JPanel PainelTabelas;
    private javax.swing.JLabel Quarta;
    private javax.swing.JLabel Quinta;
    private javax.swing.JLabel Segunda;
    private javax.swing.JLabel Sexta;
    private javax.swing.JLabel Sábado;
    private javax.swing.JLabel Terça;
    private javax.swing.JLabel TextoMes;
    private javax.swing.JPanel Titulo;
    private javax.swing.JButton arrowL;
    private javax.swing.JButton arrowR;
    private javax.swing.JPanel diaPanel11;
    private javax.swing.JPanel diaPanel12;
    private javax.swing.JPanel diaPanel13;
    private javax.swing.JPanel diaPanel14;
    private javax.swing.JPanel diaPanel15;
    private javax.swing.JPanel diaPanel16;
    private javax.swing.JPanel diaPanel17;
    private javax.swing.JPanel diaPanel21;
    private javax.swing.JPanel diaPanel22;
    private javax.swing.JPanel diaPanel23;
    private javax.swing.JPanel diaPanel24;
    private javax.swing.JPanel diaPanel25;
    private javax.swing.JPanel diaPanel26;
    private javax.swing.JPanel diaPanel27;
    private javax.swing.JPanel diaPanel31;
    private javax.swing.JPanel diaPanel32;
    private javax.swing.JPanel diaPanel33;
    private javax.swing.JPanel diaPanel34;
    private javax.swing.JPanel diaPanel35;
    private javax.swing.JPanel diaPanel36;
    private javax.swing.JPanel diaPanel37;
    private javax.swing.JPanel diaPanel41;
    private javax.swing.JPanel diaPanel42;
    private javax.swing.JPanel diaPanel43;
    private javax.swing.JPanel diaPanel44;
    private javax.swing.JPanel diaPanel45;
    private javax.swing.JPanel diaPanel46;
    private javax.swing.JPanel diaPanel47;
    private javax.swing.JPanel diaPanel51;
    private javax.swing.JPanel diaPanel52;
    private javax.swing.JPanel diaPanel53;
    private javax.swing.JPanel diaPanel54;
    private javax.swing.JPanel diaPanel55;
    private javax.swing.JPanel diaPanel56;
    private javax.swing.JPanel diaPanel57;
    private javax.swing.JPanel diaPanel61;
    private javax.swing.JPanel diaPanel62;
    private javax.swing.JPanel diaPanel63;
    private javax.swing.JPanel diaPanel64;
    private javax.swing.JPanel diaPanel65;
    private javax.swing.JPanel diaPanel66;
    private javax.swing.JPanel diaPanel67;
    private javax.swing.JPanel diasSemana;
    private javax.swing.JPanel diasSemana1;
    private javax.swing.JPanel domingoPanel;
    private javax.swing.JLabel domingoTitulo;
    private javax.swing.JPanel horariosPanel;
    private javax.swing.JLabel horariosTitulo;
    private javax.swing.JPanel listaEventosAtiv;
    private javax.swing.JPanel listaEventosFav;
    private javax.swing.JPanel painelRotina;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel panelTitulo2;
    private javax.swing.JPanel quartaPanel;
    private javax.swing.JLabel quartaTitulo;
    private javax.swing.JPanel quintaPanel;
    private javax.swing.JLabel quintaTitulo;
    private javax.swing.JPanel rotinaPanel;
    private javax.swing.JScrollPane rotinaScroll;
    private javax.swing.JPanel sabadoPanel;
    private javax.swing.JLabel sabadoTitulo;
    private javax.swing.JScrollPane scrollEventos;
    private javax.swing.JScrollPane scrollEventos2;
    private javax.swing.JPanel segundaPanel;
    private javax.swing.JLabel segundaTitulo;
    private javax.swing.JPanel sextaPanel;
    private javax.swing.JLabel sextaTitulo;
    private javax.swing.JPanel tabelaAtividades;
    private javax.swing.JPanel tabelaFavorito;
    private javax.swing.JPanel tercaPanel;
    private javax.swing.JLabel tercaTitulo;
    private javax.swing.JLabel textTitulo;
    private javax.swing.JLabel textTitulo2;
    private javax.swing.JLabel time0;
    private javax.swing.JLabel time1;
    private javax.swing.JLabel time10;
    private javax.swing.JLabel time11;
    private javax.swing.JLabel time12;
    private javax.swing.JLabel time13;
    private javax.swing.JLabel time14;
    private javax.swing.JLabel time15;
    private javax.swing.JLabel time16;
    private javax.swing.JLabel time17;
    private javax.swing.JLabel time18;
    private javax.swing.JLabel time19;
    private javax.swing.JLabel time2;
    private javax.swing.JLabel time20;
    private javax.swing.JLabel time21;
    private javax.swing.JLabel time22;
    private javax.swing.JLabel time23;
    private javax.swing.JLabel time24;
    private javax.swing.JLabel time3;
    private javax.swing.JLabel time4;
    private javax.swing.JLabel time5;
    private javax.swing.JLabel time6;
    private javax.swing.JLabel time7;
    private javax.swing.JLabel time8;
    private javax.swing.JLabel time9;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JPanel tituloPanel;
    // End of variables declaration//GEN-END:variables

    private JPanel getPanel(Integer linha, Integer col) {
        String op = linha.toString() + col.toString();
        switch (op) {
            case "11":
                return diaPanel11;
            case "12":
                return diaPanel12;
            case "13":
                return diaPanel13;
            case "14":
                return diaPanel14;
            case "15":
                return diaPanel15;
            case "16":
                return diaPanel16;
            case "17":
                return diaPanel17;
            case "21":
                return diaPanel21;
            case "22":
                return diaPanel22;
            case "23":
                return diaPanel23;
            case "24":
                return diaPanel24;
            case "25":
                return diaPanel25;
            case "26":
                return diaPanel26;
            case "27":
                return diaPanel27;
            case "31":
                return diaPanel31;
            case "32":
                return diaPanel32;
            case "33":
                return diaPanel33;
            case "34":
                return diaPanel34;
            case "35":
                return diaPanel35;
            case "36":
                return diaPanel36;
            case "37":
                return diaPanel37;
            case "41":
                return diaPanel41;
            case "42":
                return diaPanel42;
            case "43":
                return diaPanel43;
            case "44":
                return diaPanel44;
            case "45":
                return diaPanel45;
            case "46":
                return diaPanel46;
            case "47":
                return diaPanel47;
            case "51":
                return diaPanel51;
            case "52":
                return diaPanel52;
            case "53":
                return diaPanel53;
            case "54":
                return diaPanel54;
            case "55":
                return diaPanel55;
            case "56":
                return diaPanel56;
            case "57":
                return diaPanel57;
            case "61":
                return diaPanel61;
            case "62":
                return diaPanel62;
            case "63":
                return diaPanel63;
            case "64":
                return diaPanel64;
            case "65":
                return diaPanel65;
            case "66":
                return diaPanel66;
            case "67":
                return diaPanel67;
            default:
                System.err.println("Coordenadas inválidas");
                return null;
        }
    }

}
