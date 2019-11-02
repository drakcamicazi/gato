package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class VendaR extends JFrame implements ActionListener   {
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlTudo = new JPanel(new BorderLayout(20, 20)),
			pnlEspaco2 = new JPanel();
	
	private JButton btnAtualizar = new JButton("Atualizar"),
			btnProdutos = new JButton("Ver Produtos das Vendas"),
			btnFechar = new JButton("Fechar");
	
	private Color azul = new Color(181,227,240);

	private Database db = new Database();
	
	private JLabel lblTitulo = new JLabel("Vendas Realizadas");
	
	private Font fonte;
	
	public VendaR(){
		
		setTitle("Relatório de Vendas");
		setLayout(new BorderLayout(20, 20));
		setBounds(20,100,1200,380);
		getContentPane().setBackground(azul);
		add(pnlTudo, BorderLayout.CENTER);
		add(pnlEspaco, BorderLayout.WEST);
		add(pnlEspaco2, BorderLayout.EAST);
		
		pnlTudo.add(lblTitulo, BorderLayout.NORTH);
		pnlTudo.add(pnlBotoes, BorderLayout.SOUTH);
		
		pnlBotoes.add(btnAtualizar);
		pnlBotoes.add(btnProdutos);
		pnlBotoes.add(btnFechar);
		btnAtualizar.addActionListener(this);
		btnProdutos.addActionListener(this);
		btnFechar.addActionListener(this);
		
		pnlTudo.setBackground(azul);
		pnlBotoes.setBackground(azul);
		pnlEspaco.setBackground(azul);
		pnlEspaco2.setBackground(azul);
		
		try {
			pnlTudo.add(gerarTabela(), BorderLayout.CENTER);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
    	//---------------------------FONTE
		try {
	        fonte = Font.createFont(Font.TRUETYPE_FONT, new File("res\\a song for jennifer.ttf")).deriveFont(40f);
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res\\a song for jennifer.ttf")));
	        lblTitulo.setFont(fonte);
	        
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	    catch(FontFormatException e)
	    {
	        e.printStackTrace();
	    }
		
		try {//---------------------------ÍCONE
		    this.setIconImage(ImageIO.read(new File("res/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
	}
	
public JScrollPane gerarTabela() throws Exception{ 

	 JTable tabela;
	 Vector<Serializable> cabecalhos = new Vector<Serializable>(), 
			linhas = new Vector<Serializable>();
	
		db.conectar();
		db.setRs(db.getStm().executeQuery("select codigo_venda, data, hora, valor, forma_pagamento, "
				+ "(select nome from cliente where codigo_cliente = cliente_codigo_cliente) as cliente,"
				+"(select nome from funcionario where codigo_funcionario = funcionario_codigo_funcionario) as funcionario"
				+ " from venda"));
			
		cabecalhos.addElement("Código");
		cabecalhos.addElement("Data");
		cabecalhos.addElement("Hora");
		cabecalhos.addElement("Valor Total");
		cabecalhos.addElement("Forma de Pagto.");
		cabecalhos.addElement("Cliente");
		cabecalhos.addElement("Vendedor");
		
		while(db.getRs().next()){
			Vector<String> linha = new Vector<String>();
			linha.addElement(db.getRs().getString("codigo_venda"));
			linha.addElement(db.getRs().getString("data"));
			linha.addElement(db.getRs().getString("hora"));
			linha.addElement(db.getRs().getString("valor"));
			linha.addElement(db.getRs().getString("forma_pagamento"));
			linha.addElement(db.getRs().getString("cliente"));
			linha.addElement(db.getRs().getString("funcionario"));
			linhas.addElement(linha);
		}
			
			tabela = new JTable(linhas, cabecalhos);
			tabela.setEnabled(false);
			tabela.getTableHeader().setFont(new Font("Arial Black", 5, 12));
			
			tabela.getColumnModel().getColumn(0).setPreferredWidth(5); //cod
			tabela.getColumnModel().getColumn(1).setPreferredWidth(30); //data
			tabela.getColumnModel().getColumn(2).setPreferredWidth(10); //hora
			tabela.getColumnModel().getColumn(3).setPreferredWidth(10); //valor
			tabela.getColumnModel().getColumn(4).setPreferredWidth(30); //forma_pagamento
			tabela.getColumnModel().getColumn(5).setPreferredWidth(200); //cliente
			tabela.getColumnModel().getColumn(6).setPreferredWidth(200); //funcionario
			
			JScrollPane scroller = new JScrollPane(tabela); //painel especial com barra de rolagem

			db.getRs().close(); 
			return scroller;
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAtualizar)){
			VendaR.this.dispose();
			VendaR vr = new VendaR();
			vr.setVisible(true);
			vr.setResizable(true);
		}
		else
			if(e.getSource().equals(btnFechar)){
				VendaR.this.dispose();
			}
			else
				if(e.getSource().equals(btnProdutos)){
					@SuppressWarnings("unused")
					VendaProdutos vp = new VendaProdutos();
				}
		
	}
	
	public static void main(String[] args) {
		VendaR vr = new VendaR();
		vr.setVisible(true);
		vr.setResizable(true);
	}
}
