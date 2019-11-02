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
public class FornecedorR extends JFrame implements ActionListener   {
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlTudo = new JPanel(new BorderLayout(20, 20)),
			pnlEspaco2 = new JPanel();
	
	private JButton btnAtualizar = new JButton("Atualizar"), 
			btnFechar = new JButton("Fechar");
	
	private Color azul = new Color(181,227,240);

	private Database db = new Database();
	
	private JLabel lblTitulo = new JLabel("Fornecedores Cadastrados");
	
	private Font fonte;
	
	public FornecedorR(){
		
		setTitle("Relatório de Fornecedores");
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
		//ResultMetaData traz também o nome das colunas
		db.setRs(db.getStm().executeQuery("select codigo_fornecedor, razao_social, nome_fantasia, cnpj, telefone, email from fornecedor"));
				
		cabecalhos.addElement("Código");
		cabecalhos.addElement("Razão Social");
		cabecalhos.addElement("Nome Fantasia");
		cabecalhos.addElement("CNPJ");
		cabecalhos.addElement("Telefone");
		cabecalhos.addElement("E-mail");
		
		while(db.getRs().next()){
			Vector<String> linha = new Vector<String>();
			linha.addElement(db.getRs().getString("codigo_Fornecedor"));
			linha.addElement(db.getRs().getString("razao_social"));
			linha.addElement(db.getRs().getString("nome_fantasia"));
			linha.addElement(db.getRs().getString("cnpj"));
			linha.addElement(db.getRs().getString("telefone"));
			linha.addElement(db.getRs().getString("email"));
			linhas.addElement(linha);
		}
			
			tabela = new JTable(linhas, cabecalhos);
			tabela.setEnabled(false);
			tabela.getTableHeader().setFont(new Font("Arial Black", 5, 12));
			
			tabela.getColumnModel().getColumn(0).setPreferredWidth(5); //cod
			tabela.getColumnModel().getColumn(1).setPreferredWidth(180); //razao
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150); //nome
			tabela.getColumnModel().getColumn(3).setPreferredWidth(15); //cnpj
			tabela.getColumnModel().getColumn(4).setPreferredWidth(30); //tel
			tabela.getColumnModel().getColumn(5).setPreferredWidth(150); //email
			
			JScrollPane scroller = new JScrollPane(tabela); //painel especial com barra de rolagem

			db.getRs().close(); 
			return scroller;
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAtualizar)){
			FornecedorR.this.dispose();
			FornecedorR forr = new FornecedorR();
			forr.setVisible(true);
			forr.setResizable(true);
		}
		else
			if(e.getSource().equals(btnFechar)){
				FornecedorR.this.dispose();
			}
		
	}
	
	public static void main(String[] args) {
		FornecedorR forr = new FornecedorR();
		forr.setVisible(true);
		forr.setResizable(true);
	}
}
