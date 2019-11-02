package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProdutoC extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnSair = new JButton("Sair"),
	        btnLimpar = new JButton("Limpar"),
	        btnEstoque = new JButton("Alterar Estoque"),
	        btnSalvar = new JButton ("Salvar");
	
	private Database db = new Database();
	
	private JLabel lblNome = new JLabel("Nome do Produto: "),
			lblTipo = new JLabel("Tipo: "),
			lblMarca = new JLabel("Marca: "),
			lblPrecoQuant = new JLabel("Preço:                                                                                    Quantidade:"),
			lblFornecedor = new JLabel("Fornecedor: ");
	
	private JTextField txtNome = new JTextField(),
			txtTipo = new JTextField(),
			txtMarca = new JTextField(),
			txtPreco = new JTextField("0.00"),
			txtQuant = new JTextField("0");
	
	private JComboBox<String> cmbFornecedor = new JComboBox<>();
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlEspaco2 = new JPanel(),
			pnlValores = new JPanel(new GridLayout(10, 10, 4, 1)),
			pnlPrecoQuant = new JPanel(new GridLayout(1, 2, 100, 30));
	
	private Color azul = new Color(181,227,240);

	public ProdutoC() { //---------------------------------------CONSTRUTOR
		
		setTitle("Cadastro de Produto");
		setLayout(new BorderLayout(20, 20));
		setBounds(500,100,540,380);
		getContentPane().setBackground(azul); 
		add(pnlEspaco, BorderLayout.EAST);
		add(pnlEspaco2, BorderLayout.WEST);
		add(pnlBotoes, BorderLayout.SOUTH);
		add(pnlValores, BorderLayout.CENTER);
		
		pnlBotoes.add(btnLimpar);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnEstoque);
		pnlBotoes.add(btnSair);

		btnLimpar.addActionListener(this);
		btnSair.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnEstoque.addActionListener(this);
		
		pnlBotoes.setBackground(azul); 
		pnlEspaco.setBackground(azul); 
		pnlEspaco2.setBackground(azul); 
		pnlValores.setBackground(azul); 
		pnlPrecoQuant.setBackground(azul);

		pnlValores.add(lblNome);
		pnlValores.add(txtNome);
		pnlValores.add(lblMarca);
		pnlValores.add(txtMarca);
		pnlValores.add(lblTipo);
		pnlValores.add(txtTipo);
		pnlValores.add(lblFornecedor);
		pnlValores.add(cmbFornecedor);
		pnlValores.add(lblPrecoQuant);
		pnlValores.add(pnlPrecoQuant);
		
		pnlPrecoQuant.add(txtPreco);
		pnlPrecoQuant.add(txtQuant);		
		
		try {//---------------------------ÍCONE
		    this.setIconImage(ImageIO.read(new File("res/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		cmbFornecedor.removeAll();
		cmbFornecedor.addItem("");
		this.preencherFornecedor();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource () == btnSair){
			ProdutoC.this.dispose();
		}
		else if(e.getSource() == btnLimpar){
			limpar();			
		}
		else if(e.getSource() == btnEstoque){
			AlterarEstoque ae = new AlterarEstoque();
			ae.setVisible(true);
			ae.setResizable(false);
		}
		else if(e.getSource() == btnSalvar){
			db.inserirProduto(txtNome.getText(), txtMarca.getText(), txtTipo.getText(), txtPreco.getText(), txtQuant.getText(), cmbFornecedor.getItemAt(cmbFornecedor.getSelectedIndex()));
			limpar();
		}		
	}
	
	public void limpar(){
		cmbFornecedor.setSelectedIndex(0);
		txtNome.setText("");
		txtTipo.setText("");
		txtMarca.setText("");
		txtPreco.setText("0.00");
		txtQuant.setText("0");
	}
	
	public void preencherFornecedor(){
		//------------------------GERAR ARRAY DE FORNECEDORES
				
				try {
					db.conectar();
					String sql = "select razao_social from fornecedor";
					ResultSet rs = db.getStm().executeQuery(sql);
					
					while(rs.next()){
						cmbFornecedor.addItem(rs.getString("razao_social"));
					}
					cmbFornecedor.updateUI();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	}
	
	public static void main (String[]args){
		ProdutoC p = new ProdutoC();
		p.setVisible(true);
		p.setResizable(false);
	}
}
