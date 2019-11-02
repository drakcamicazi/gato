package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ClienteC extends JFrame implements ActionListener  {

	private String[] strUf = { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
			"MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
	private JComboBox<String> cmbUf = new JComboBox<>(strUf);
	
	private String endereco;

	private Color azul = new Color(181,227,240);
	
	private Database db = new Database();
	
	private JPanel pnlValores = new JPanel(new GridLayout(0, 1)),
					pnlNome = new JPanel(new GridLayout(3, 1)),
					pnlUf  = new JPanel(new FlowLayout (FlowLayout.LEADING, 2, 5)),
					pnlSexo = new JPanel(new GridLayout(1, 4)),
					pnlDivisor = new JPanel(new GridLayout(3, 1)),
				   pnlEndereco = new JPanel(new GridLayout(3,2)),
			       pnlBotoes = new JPanel(),
			       pnlTudo = new JPanel(),
			       
			       pnlTelefone = new JPanel(new FlowLayout (FlowLayout.LEADING, 2, 5)),
			   	   pnlRg = new JPanel(new FlowLayout (FlowLayout.LEADING, 2, 5)),
			   	   pnlCpf = new JPanel(new FlowLayout (FlowLayout.LEADING, 2, 5)),
			       pnlEspaco = new JPanel(),
			       pnlEspaco2 = new JPanel();
	
	private JRadioButton masc = new JRadioButton("M", false),
				fem = new JRadioButton("F", false),
				outro = new JRadioButton("X", false);
	
	private JLabel lblNome = new JLabel ("Nome:"),
			       lblTelefone = new JLabel ("Telefone:    "),
			       lblEndereco = new JLabel ("Endereço:"),
			       lblUf = new JLabel ("        UF:    "),
			       lblCidade = new JLabel ("Cidade:    "),
			       lblSexo = new JLabel ("Sexo:"),
			       lblCpf = new JLabel ("CPF:    "),
			       lblRg = new JLabel ("RG:    ");
	
	private JTextField txtNome = new JTextField (),
			txtTelefone = new JTextField (),
		    txtEndereco = new JTextField (),
		    txtCidade = new JTextField (),
		    txtCpf = new JTextField (),
		    txtRg = new JTextField ();
			   
	private JButton btnSair = new JButton("Sair"),
			        btnLimpar = new JButton("Limpar"),
			        btnSalvar = new JButton ("Salvar");
			    		   
	private ButtonGroup grpSexo = new ButtonGroup();
	
	public ClienteC(String nome, int largura, int altura){
		setTitle("Cadastro de "+nome);
		getPnlTudo().setLayout(new BorderLayout(20, 20));
		setBounds(500,100,largura,altura);
		getPnlTudo().add(getPnlEspaco(), BorderLayout.EAST);
		getPnlTudo().add(getPnlEspaco2(), BorderLayout.WEST);
		getPnlTudo().add(getPnlBotoes(), BorderLayout.SOUTH);
		getPnlTudo().add(getPnlDivisor(), BorderLayout.CENTER);

		add(getPnlTudo());

		getMasc().addActionListener(this);
		getFem().addActionListener(this);
		getOutro().addActionListener(this);
		getBtnSair().addActionListener(this);
		getBtnLimpar().addActionListener(this);
		getBtnSalvar().addActionListener(this);
		
		getPnlDivisor().add(getPnlNome());
		getPnlDivisor().add(getPnlValores());
		getPnlDivisor().add(getPnlEndereco());
		
		getPnlValores().add(getPnlCpf());
		getPnlValores().add(getPnlRg());
		getPnlValores().add(getPnlTelefone());

		getTxtTelefone().setPreferredSize(new Dimension(200, 25));
		getPnlTelefone().add(lblTelefone);
		getPnlTelefone().add(getTxtTelefone());
		getTxtRg().setPreferredSize(new Dimension(150, 25));
		getPnlRg().add(lblRg);
		getPnlRg().add(getTxtRg());
		getTxtCpf().setPreferredSize(new Dimension(200, 25));
		getPnlCpf().add(lblCpf);
		getPnlCpf().add(getTxtCpf());
		
		getTxtCidade().setPreferredSize(new Dimension(300, 25));
		
		pnlUf.add(lblCidade);
		pnlUf.add(getTxtCidade());
		pnlUf.add(lblUf);
		pnlUf.add(getCmbUf());
		
		getPnlEndereco().add(lblEndereco);
		getPnlEndereco().add(getTxtEndereco());
		getPnlEndereco().add(pnlUf);
		
		getPnlNome().add(lblNome);
		getPnlNome().add(getTxtNome());
		getPnlNome().add(pnlSexo);
		
		pnlSexo.add(lblSexo);
		pnlSexo.add(getMasc());
		pnlSexo.add(getFem());
		pnlSexo.add(getOutro());
		
		getPnlBotoes().add(getBtnLimpar());
		getPnlBotoes().add(getBtnSalvar());
		getPnlBotoes().add(getBtnSair());
		
		//----------------------------COLORIR
		getPnlValores().setBackground(azul); 
		getPnlNome().setBackground(azul); 
		pnlUf.setBackground(azul); 
		pnlSexo.setBackground(azul); 
		pnlDivisor.setBackground(azul); 
		getPnlEndereco().setBackground(azul); 
		pnlBotoes.setBackground(azul); 
		getPnlTudo().setBackground(azul); 
		getPnlTelefone().setBackground(azul); 
		getPnlRg().setBackground(azul); 
		getPnlCpf().setBackground(azul); 
		pnlEspaco.setBackground(azul); 
		pnlEspaco2.setBackground(azul); 
		outro.setBackground(azul); 
		fem.setBackground(azul); 
		masc.setBackground(azul); 
		
		try {//---------------------------ÍCONE
		    this.setIconImage(ImageIO.read(new File("res/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		getGrpSexo().add(getFem());
		getGrpSexo().add(getMasc());
		getGrpSexo().add(getOutro());
	}
	public void actionPerformed(ActionEvent e) { //------------------------------------AÇÕES
		if(e.getSource () == getBtnSair()){
			ClienteC.this.dispose();
		}
		else if(e.getSource() == getBtnLimpar()){
			limpar();			
		}
		else if(e.getSource() == getBtnSalvar()){
			db.inserir("cliente", "nome", "telefone", "endereco", "cpf", "rg", "sexo", getTxtNome().getText(), getTxtTelefone().getText(), getEndereco(), getTxtCpf().getText(), getTxtRg().getText(), getSexo() );			
			limpar();
		}
	}
	
	public String getSexo(){
		if (getMasc().isSelected())
			return "Masculino";
		else if (getFem().isSelected())
			return "Feminino";
		else
			return "Outro";
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
	}

public static void main (String[]args){ //--------------------------------------MAIN
	ClienteC cli = new ClienteC("Cliente", 540, 380);
	cli.setVisible(true);
	cli.setResizable(false);
}

//------------------------------------------------------------GETs e SETs
public void setLayoutDivisor(LayoutManager l){
		getPnlDivisor().setLayout(l);
	}
	
public JPanel getPnlDivisor(){
		return pnlDivisor;
	}
	
public JPanel getPnlBotoes() {
	return pnlBotoes;
}
public void setPnlBotoes(JPanel pnlBotoes) {
	this.pnlBotoes = pnlBotoes;
}
public JPanel getPnlEspaco() {
	return pnlEspaco;
}
public void setPnlEspaco(JPanel pnlEspaco) {
	this.pnlEspaco = pnlEspaco;
}
public JPanel getPnlEspaco2() {
	return pnlEspaco2;
}
public void setPnlEspaco2(JPanel pnlEspaco2) {
	this.pnlEspaco2 = pnlEspaco2;
}
public void addPnlDivisor(Component comp) {
	pnlDivisor.add(comp);
}
public ButtonGroup getGrpSexo() {
	return grpSexo;
}
public void setGrpSexo(ButtonGroup grpSexo) {
	this.grpSexo = grpSexo;
}
public JButton getBtnSair() {
	return btnSair;
}
public JButton getBtnLimpar() {
	return btnLimpar;
}
public JButton getBtnSalvar() {
	return btnSalvar;
}
public void setBtnSalvar(JButton btnSalvar) {
	this.btnSalvar = btnSalvar;
}
public JTextField getTxtNome() {
	return txtNome;
}
public void setTxtNome(JTextField txtNome) {
	this.txtNome = txtNome;
}
public JTextField getTxtTelefone() {
	return txtTelefone;
}
public void setTxtTelefone(JTextField txtTelefone) {
	this.txtTelefone = txtTelefone;
}
public JTextField getTxtEndereco() {
	return txtEndereco;
}
public void setTxtEndereco(JTextField txtEndereco) {
	this.txtEndereco = txtEndereco;
}
public JTextField getTxtCidade() {
	return txtCidade;
}
public void setTxtCidade(JTextField txtCidade) {
	this.txtCidade = txtCidade;
}
public JTextField getTxtCpf() {
	return txtCpf;
}
public void setTxtCpf(JTextField txtCpf) {
	this.txtCpf = txtCpf;
}
public JTextField getTxtRg() {
	return txtRg;
}
public void setTxtRg(JTextField txtRg) {
	this.txtRg = txtRg;
}
public JComboBox<String> getCmbUf() {
	return cmbUf;
}
public void setCmbUf(JComboBox<String> cmbUf) {
	this.cmbUf = cmbUf;
}
public JRadioButton getMasc() {
	return masc;
}
public void setMasc(JRadioButton masc) {
	this.masc = masc;
}
public JRadioButton getFem() {
	return fem;
}
public void setFem(JRadioButton fem) {
	this.fem = fem;
}
public JRadioButton getOutro() {
	return outro;
}
public void setOutro(JRadioButton outro) {
	this.outro = outro;
}
public String getEndereco() {
	setEndereco(getTxtEndereco().getText()+", "+getTxtCidade().getText()+", "+ strUf[getCmbUf().getSelectedIndex()]);
	return endereco;
}
public void setEndereco(String endereco) {
	this.endereco = endereco;
}
public JPanel getPnlValores() {
	return pnlValores;
}
public void setPnlValores(JPanel pnlValores) {
	this.pnlValores = pnlValores;
}
public JPanel getPnlNome() {
	return pnlNome;
}
public void setPnlNome(JPanel pnlNome) {
	this.pnlNome = pnlNome;
}
public JPanel getPnlEndereco() {
	return pnlEndereco;
}
public void setPnlEndereco(JPanel pnlEndereco) {
	this.pnlEndereco = pnlEndereco;
}
public JPanel getPnlTudo() {
	return pnlTudo;
}
public void setPnlTudo(JPanel pnlTudo) {
	this.pnlTudo = pnlTudo;
}
public JPanel getPnlTelefone() {
	return pnlTelefone;
}
public void setPnlTelefone(JPanel pnlTelefone) {
	this.pnlTelefone = pnlTelefone;
}
public JPanel getPnlRg() {
	return pnlRg;
}
public void setPnlRg(JPanel pnlRg) {
	this.pnlRg = pnlRg;
}
public JPanel getPnlCpf() {
	return pnlCpf;
}
public void setPnlCpf(JPanel pnlCpf) {
	this.pnlCpf = pnlCpf;
}
}
