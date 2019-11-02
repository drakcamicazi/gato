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
public class FuncionarioR extends JFrame implements ActionListener   {
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlTudo = new JPanel(new BorderLayout(20, 20)),
			pnlEspaco2 = new JPanel();
	
	private JButton btnAtualizar = new JButton("Atualizar"), 
			btnFechar = new JButton("Fechar");
	
	private Color azul = new Color(181,227,240);

	private Database db = new Database();
	
	private JLabel lblTitulo = new JLabel("Funcionários Cadastrados");
	
	private Font fonte;
	
	public FuncionarioR(){
		
		setTitle("Relatório de Funcionários");
		setLayout(new BorderLayout(20, 20));
		setBounds(20,100,1200,380);
		getContentPane().setBackground(azul);
		add(pnlTudo, BorderLayout.CENTER);
		add(pnlEspaco, BorderLayout.WEST);
		add(pnlEspaco2, BorderLayout.EAST);
		
		pnlTudo.add(lblTitulo, BorderLayout.NORTH);
		pnlTudo.add(pnlBotoes, BorderLayout.SOUTH);
		
		pnlBotoes.add(btnAtualizar);
		pnlBotoes.add(btnFechar);
		btnAtualizar.addActionListener(this);
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
	 Vector<Serializable> cabecalhos = new Vector<Serializable>(), //objeto que monta vetores de vários objetos 
			linhas = new Vector<Serializable>();
	
		db.conectar();
		db.setRs(db.getStm().executeQuery("select codigo_Funcionario, nome, sexo, endereco, telefone, rg, cpf, salario, cargo from funcionario"));
				
		cabecalhos.addElement("Código");
		cabecalhos.addElement("Nome");
		cabecalhos.addElement("Sexo");
		cabecalhos.addElement("Cargo");
		cabecalhos.addElement("Salário");
		cabecalhos.addElement("Endereço");
		cabecalhos.addElement("Telefone");
		cabecalhos.addElement("RG");
		cabecalhos.addElement("CPF");
		
		while(db.getRs().next()){
			Vector<String> linha = new Vector<String>();
			linha.addElement(db.getRs().getString("codigo_Funcionario"));
			linha.addElement(db.getRs().getString("nome"));
			linha.addElement(db.getRs().getString("sexo"));
			linha.addElement(db.getRs().getString("cargo"));
			linha.addElement(db.getRs().getString("salario"));
			linha.addElement(db.getRs().getString("endereco"));
			linha.addElement(db.getRs().getString("telefone"));
			linha.addElement(db.getRs().getString("rg"));
			linha.addElement(db.getRs().getString("cpf"));
			linhas.addElement(linha);
		}
			
			tabela = new JTable(linhas, cabecalhos);
			tabela.setEnabled(false);
			tabela.getTableHeader().setFont(new Font("Arial Black", 5, 12));
			
			tabela.getColumnModel().getColumn(0).setPreferredWidth(3); //cod
			tabela.getColumnModel().getColumn(1).setPreferredWidth(150); //nome
			tabela.getColumnModel().getColumn(2).setPreferredWidth(15);//sexo
			tabela.getColumnModel().getColumn(3).setPreferredWidth(15);//cargo
			tabela.getColumnModel().getColumn(4).setPreferredWidth(7);//salario
			tabela.getColumnModel().getColumn(5).setPreferredWidth(240); //endereco
			tabela.getColumnModel().getColumn(6).setPreferredWidth(30); //tel
			tabela.getColumnModel().getColumn(7).setPreferredWidth(7); // rg
			tabela.getColumnModel().getColumn(8).setPreferredWidth(28); //cpf
			
			JScrollPane scroller = new JScrollPane(tabela); //painel especial com barra de rolagem

			db.getRs().close(); 
			return scroller;
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAtualizar)){
			FuncionarioR.this.dispose();
			FuncionarioR cr = new FuncionarioR();
			cr.setVisible(true);
			cr.setResizable(true);
		}
		else
			if(e.getSource().equals(btnFechar)){
				FuncionarioR.this.dispose();
			}
		
	}
	
	public static void main(String[] args) {
		FuncionarioR fr = new FuncionarioR();
		fr.setVisible(true);
		fr.setResizable(true);
	}
}
