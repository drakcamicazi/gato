package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VendaProdutos extends JFrame implements ActionListener {

private Database db = new Database();
	
	private JLabel lblVenda = new JLabel("Código da venda: ");
	
	private JTextField txtVenda = new JTextField();
	
	private JButton btnFechar = new JButton("Fechar"),
			btnNovo = new JButton("Nova Consulta"),
			btnIr = new JButton("Ir");
	
	
	private Color azul = new Color(181,227,240);
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlEspaco2 = new JPanel(),
			pnlEspaco3 = new JPanel(),
			pnlProduto = new JPanel(),
			pnlValores = new JPanel(new BorderLayout());
	
	public VendaProdutos() {
		
		setTitle("Produtos das Vendas");
		setLayout(new BorderLayout(20, 20));
		setBounds(500,100, 400, 300);
		setVisible(true);
		getContentPane().setBackground(azul); 
		add(pnlEspaco, BorderLayout.EAST);
		add(pnlEspaco2, BorderLayout.WEST);
		add(pnlBotoes, BorderLayout.SOUTH);
		add(pnlValores, BorderLayout.CENTER);
		add(pnlEspaco3, BorderLayout.NORTH);
		
		pnlValores.add(pnlProduto, BorderLayout.NORTH);
		
		pnlProduto.add(lblVenda);
		pnlProduto.add(txtVenda);
		pnlProduto.add(btnIr);
		
		pnlBotoes.add(btnNovo);
		pnlBotoes.add(btnFechar);
		
		txtVenda.setPreferredSize(new Dimension(100, 30));
		
		btnFechar.addActionListener(this);
		btnIr.addActionListener(this);
		btnNovo.addActionListener(this);

		pnlBotoes.setBackground(azul);
		pnlValores.setBackground(azul);
		pnlEspaco.setBackground(azul);
		pnlEspaco2.setBackground(azul);
		pnlEspaco3.setBackground(azul);
		pnlProduto.setBackground(azul);
		
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
			db.setRs(db.getStm().executeQuery("select (select nome_produto from produto where codigo_produto = venda_tem_produto.produto_codigo_Produto) as produto from venda_tem_produto where venda_Codigo_Venda="+txtVenda.getText()));
					
			cabecalhos.addElement("Nome do Produto");
			
			while(db.getRs().next()){
				Vector<String> linha = new Vector<String>();
				linha.addElement(db.getRs().getString("produto"));
				linhas.addElement(linha);
			}
				
				tabela = new JTable(linhas, cabecalhos);
				tabela.setEnabled(false);
				tabela.getTableHeader().setFont(new Font("Arial Black", 5, 12));
				
				JScrollPane scroller = new JScrollPane(tabela); 
				db.getRs().close(); 
				if (tabela.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Não foram encontrados produtos para esta venda.");
				}
				return scroller;
			}
		
	public void actionPerformed(ActionEvent e) {
		if(e.getSource () == btnFechar){
			VendaProdutos.this.dispose();
		}
		else
		if(e.getSource () == btnIr){
			try {
				pnlValores.add(gerarTabela(), BorderLayout.CENTER);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			txtVenda.setEditable(false);
			this.validate(); //VALIDA TODOS OS COMPONENTES DO FRAME DE MODO QUE FIQUEM VISÍVEIS
		}
		else
			if(e.getSource () == btnNovo){
				VendaProdutos.this.dispose();
				@SuppressWarnings("unused")
				VendaProdutos vp = new VendaProdutos();
				
			}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		VendaProdutos vp = new VendaProdutos();
	}
}
