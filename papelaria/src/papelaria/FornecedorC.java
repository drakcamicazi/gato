package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FornecedorC extends JFrame implements ActionListener  {

	private Database db = new Database();
	
	private JPanel pnlBotoes = new JPanel(),
			pnlEspaco = new JPanel(),
			pnlEspaco2 = new JPanel(),
			pnlValores = new JPanel(new GridLayout(10, 10, 4, 1)),
			pnlTelCnpj = new JPanel(new GridLayout(1, 10, 0, 3));
			
	private JTextField txtRazao = new JTextField(),
						txtFantasia = new JTextField(),
						txtCnpj = new JTextField(),
						txtEmail = new JTextField(),
						txtTelefone = new JTextField();
	
	private JLabel lblRazao = new JLabel("Razão Social: "),
			lblFantasia = new JLabel("Nome Fantasia: "),
			lblCnpj = new JLabel("CNPJ:                                                                                             Telefone:"),
			lblEmail = new JLabel("Email: "),
			lblTelefone = new JLabel(" ");
	
	private JButton btnSair = new JButton("Sair"),
	        btnLimpar = new JButton("Limpar"),
	        btnSalvar = new JButton ("Salvar");
	
	private Color azul = new Color(181,227,240);
	
	public FornecedorC() {
		setTitle("Cadastro de Fornecedor");
		setLayout(new BorderLayout(20, 20));
		setBounds(500,100,540,380);
		getContentPane().setBackground(azul); 
		add(pnlEspaco, BorderLayout.EAST);
		add(pnlEspaco2, BorderLayout.WEST);
		add(pnlBotoes, BorderLayout.SOUTH);
		add(pnlValores, BorderLayout.CENTER);
		
		pnlValores.add(lblRazao);
		pnlValores.add(txtRazao);
		pnlValores.add(lblFantasia);
		pnlValores.add(txtFantasia);
		pnlValores.add(lblEmail);
		pnlValores.add(txtEmail);
		pnlValores.add(lblCnpj);
		pnlValores.add(pnlTelCnpj);

		pnlTelCnpj.add(txtCnpj);
		pnlTelCnpj.add(lblTelefone);
		pnlTelCnpj.add(txtTelefone);
		
		pnlBotoes.add(btnLimpar);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnSair);
		
		btnLimpar.addActionListener(this);
		btnSair.addActionListener(this);
		btnSalvar.addActionListener(this);
		
		pnlBotoes.setBackground(azul); 
		pnlEspaco.setBackground(azul); 
		pnlEspaco2.setBackground(azul); 
		pnlValores.setBackground(azul); 
		pnlTelCnpj.setBackground(azul); 
		
		try {//---------------------------ÍCONE
		    this.setIconImage(ImageIO.read(new File("res/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource () == btnSair){
			FornecedorC.this.dispose();
		}
		else if(e.getSource() == btnLimpar){
			limpar();			
		}
		else if(e.getSource() == btnSalvar){
			db.inserir("fornecedor", "Razao_Social", "telefone", "email", "cnpj", "nome_fantasia", txtRazao.getText(), txtTelefone.getText(), txtEmail.getText(), txtCnpj.getText(), txtFantasia.getText() );			
			limpar();
		}
	}

	public void limpar(){
		txtRazao.setText("");
		txtFantasia.setText("");
		txtCnpj.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
	}

	
	public static void main (String[]args){ //--------------------------------------MAIN
		FornecedorC forc = new FornecedorC();
		forc.setVisible(true);
		forc.setResizable(false);
	}
}
