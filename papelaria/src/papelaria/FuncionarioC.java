package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FuncionarioC extends ClienteC{

	private JPanel pnlCargoSal = new JPanel(new FlowLayout (FlowLayout.LEADING, 2, 5));
	
	private Database db = new Database();
	 
	private JLabel lblCargo = new JLabel("Cargo*:     "),
			 lblSalario = new JLabel("           Salário*:    ");
	 
	private String[] strCargo = { "", "Gerente", "Atendente", "Vendedor"};
	private	JComboBox<String> cmbCargo = new JComboBox<>(strCargo);
	
	private JTextField txtSalario = new JTextField();

	private Color azul = new Color(181,227,240);
	
	public FuncionarioC(String nome){
		super(nome, 540, 440);
		setLayoutDivisor(new GridLayout(4, 1));
		setLayout(new BorderLayout(20, 20));
		getContentPane().setBackground(azul); 
		getContentPane().add(pnlCargoSal);
		txtSalario.setPreferredSize(new Dimension(70, 25));
		
		
		pnlCargoSal.add(lblCargo);
		pnlCargoSal.add(cmbCargo);
		pnlCargoSal.add(lblSalario);
		pnlCargoSal.add(txtSalario);
		addPnlDivisor(pnlCargoSal);
		pnlCargoSal.setBackground(azul);
		lblCargo.setBackground(azul); 
		
		add(getPnlDivisor(), BorderLayout.CENTER);
		add(getPnlBotoes(), BorderLayout.SOUTH);
		add(getPnlEspaco(), BorderLayout.WEST);
		add(getPnlEspaco2(), BorderLayout.EAST);
		
		//----------------------------COLORIR
				getPnlValores().setBackground(azul); 
				getPnlNome().setBackground(azul);
				getPnlDivisor().setBackground(azul); 
				getPnlEndereco().setBackground(azul); 
				getPnlBotoes().setBackground(azul); 
				getPnlTudo().setBackground(azul); 
				getPnlTelefone().setBackground(azul); 
				getPnlRg().setBackground(azul); 
				getPnlCpf().setBackground(azul); 
				getPnlEspaco().setBackground(azul); 
				getPnlEspaco2().setBackground(azul); 
				
	}
	public void actionPerformed(ActionEvent e) { //------------------------------------AÇÕES
		if(e.getSource () == getBtnSair()){
			FuncionarioC.this.dispose();
		}
		else if(e.getSource() == getBtnLimpar()){
			limpar();			
		}
		else if(e.getSource() == getBtnSalvar()){
			db.inserir("funcionario", "nome", "telefone", "endereco", "cpf", "rg", "sexo", "cargo", "salario", getTxtNome().getText(), getTxtTelefone().getText(), getEndereco(), getTxtCpf().getText(), getTxtRg().getText(), getSexo(), cmbCargo.getSelectedItem().toString(), txtSalario.getText() );			
			limpar();
		}
	}
	
	public static void main(String[]args){
		FuncionarioC f = new FuncionarioC("Funcionário");
		f.setVisible(true);
		f.setResizable(false);
	}
	
	public void limpar(){
		getTxtNome().setText("");
		getTxtTelefone().setText("");
		getTxtEndereco().setText("");
		getTxtCidade().setText("");
		getTxtCpf().setText("");
		getTxtRg().setText("");
		getCmbUf().setSelectedIndex(0);
		getGrpSexo().clearSelection();
		cmbCargo.setSelectedIndex(0);
		txtSalario.setText("");
	}
}
