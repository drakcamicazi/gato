package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class AlterarEstoque extends JFrame implements ActionListener{

	private Database db = new Database();
	
	private JLabel lblQuantidade = new JLabel("Quantidade a adicionar: ");
	
	private JTextField txtQuantidade = new JTextField();
	
	private JButton btnCancelar = new JButton("Cancelar"),
			btnSalvar = new JButton("Salvar");
	
	private JComboBox<String> cmbProduto = new JComboBox<>();
	
	private Color azul = new Color(181,227,240);
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlEspaco2 = new JPanel(),
			pnlEspaco3 = new JPanel(),
			pnlProduto = new JPanel(),
			pnlValores = new JPanel(new GridLayout(2, 1, 5, 5));
	
	public AlterarEstoque() {
		
		setTitle("Alterar Estoque");
		setLayout(new BorderLayout(20, 20));
		setBounds(500,100, 400,200);
		getContentPane().setBackground(azul); 
		add(pnlEspaco, BorderLayout.EAST);
		add(pnlEspaco2, BorderLayout.WEST);
		add(pnlBotoes, BorderLayout.SOUTH);
		add(pnlValores, BorderLayout.CENTER);
		add(pnlEspaco3, BorderLayout.NORTH);
		
		pnlValores.add(cmbProduto);
		pnlValores.add(pnlProduto);
		
		pnlProduto.add(lblQuantidade);
		pnlProduto.add(txtQuantidade);

		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnCancelar);
		
		cmbProduto.setMaximumSize(new Dimension(300, 20));
		txtQuantidade.setPreferredSize(new Dimension(170, 30));
		
		btnCancelar.addActionListener(this);
		btnSalvar.addActionListener(this);

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
		
		cmbProduto.removeAll();
		cmbProduto.addItem("Selecione ou digite nome do produto");
		this.preencherProduto();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource () == btnCancelar){
			AlterarEstoque.this.dispose();
		}
		
		if(e.getSource () == btnSalvar){
			db.adicionarEstoque(txtQuantidade.getText(), ""+cmbProduto.getSelectedIndex());
			cmbProduto.setSelectedIndex(0);
			txtQuantidade.setText("");
			
		}
	}
	
	public void preencherProduto(){
		//------------------------GERAR ARRAY DE PRODUTOS
				
				try {
					db.conectar();
					String sql = "select nome_produto, codigo_produto from Produto";
					ResultSet rs = db.getStm().executeQuery(sql);
					
					while(rs.next()){
						cmbProduto.addItem(rs.getString("nome_produto") + ", cod. "+ rs.getString("codigo_produto"));
					}
					cmbProduto.updateUI();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	public static void main (String[]args){
		AlterarEstoque a = new AlterarEstoque();
		a.setVisible(true);
		a.setResizable(false);
	}
}
