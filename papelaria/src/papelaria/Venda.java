package papelaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Venda extends JFrame implements ActionListener{
	
	private Database db = new Database();

	//-----------------------------------------------VARIÁVEIS PARA A DATA: FORMATADOR E VARIÁVEL
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
	private LocalDateTime dataHora = LocalDateTime.now();
	
	private JLabel lblData = new JLabel("Data: "+ dtf.format(dataHora)),
			lblValor = new JLabel("          Valor Total: "),
			lblProduto = new JLabel("Cod. do Produto: ");
	
	private DefaultTableModel modelo;
	
	private JTable tabela;
	
	private String[] strForma = { "Forma de Pagamento...", "Dinheiro", "Débito", "Crédito", "Outro"};
	
	private JComboBox<String> cmbFuncionario = new JComboBox<>(),
			cmbCliente = new JComboBox<>(),
			cmbForma_pagto = new JComboBox<>(strForma);
	
	private JTextField txtHora = new JTextField(),
			txtProduto = new JTextField(),
			txtValor = new JTextField();
	
	private Color azul = new Color(181,227,240);

	private JButton btnVender = new JButton("Vender"),
			btnCancelar = new JButton("Cancelar"),
			btnSair = new JButton("Sair"),
			btnProdutos = new JButton("Produtos"),
			btnAdicionar = new JButton("Adicionar Produto");
	
	private JPanel pnlBotoes = new JPanel(new GridLayout(4, 1, 5, 15)),
			pnlNorte = new JPanel (new BorderLayout(240, 80)),
			pnlProduto = new JPanel(),
			pnlCliPagto = new JPanel(new GridLayout(1, 2, 40, 2)),
			pnlValores = new JPanel(new GridLayout(2, 2, 30, 40)),
			pnlEspaco = new JPanel(),
			pnlEspaco2 = new JPanel(),
			pnlEspaco3 = new JPanel(),
			pnlEspaco4 = new JPanel(),
			pnlDivisor = new JPanel(new BorderLayout(10, 30)),
			pnlTudo = new JPanel(new BorderLayout(100, 80));
			
			
	@SuppressWarnings("static-access")
	public Venda() {		
		setTitle("Venda");
		setLayout(new BorderLayout(10, 40));
		setBounds(500,100, 830,500);
		setLocationRelativeTo(null);
		setExtendedState(this.MAXIMIZED_BOTH);
		getContentPane().setBackground(new Color(181,227,240)); 
 		add(pnlEspaco, BorderLayout.EAST);
 		add(pnlEspaco2, BorderLayout.WEST);
 		add(pnlEspaco3, BorderLayout.SOUTH);
 		add(pnlEspaco4, BorderLayout.NORTH);
 		add(pnlTudo, BorderLayout.CENTER);
		pnlTudo.add(pnlBotoes, BorderLayout.EAST);
		pnlTudo.add(pnlDivisor, BorderLayout.CENTER);
		pnlTudo.add(pnlNorte, BorderLayout.NORTH);
		
		pnlBotoes.setPreferredSize(new Dimension(100, 80));
		pnlBotoes.add(btnCancelar);
		pnlBotoes.add(btnProdutos);
		pnlBotoes.add(btnVender);
		pnlBotoes.add(btnSair);
		
		pnlNorte.add(cmbFuncionario, BorderLayout.CENTER);
		pnlNorte.add(lblData, BorderLayout.EAST);

		pnlDivisor.add(pnlValores, BorderLayout.SOUTH);
		try {
			pnlDivisor.add(gerarTabela(), BorderLayout.NORTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pnlProduto.add(lblProduto);
		pnlProduto.add(txtProduto);
		pnlProduto.add(btnAdicionar);
		pnlProduto.add(lblValor);
		pnlProduto.add(txtValor);
		txtProduto.setPreferredSize(new Dimension(100, 30));
		txtValor.setPreferredSize(new Dimension(100, 30));

		pnlValores.add(pnlProduto);
		pnlValores.add(pnlCliPagto);
		
		pnlCliPagto.add(cmbCliente);
		pnlCliPagto.add(cmbForma_pagto);
		
		btnCancelar.addActionListener(this);
		btnSair.addActionListener(this);
		btnVender.addActionListener(this);
		btnProdutos.addActionListener(this);
		btnAdicionar.addActionListener(this);
		
		btnVender.setFont(new Font("Arial Black", 5, 16));
		
		pnlCliPagto.setBackground(azul);
		pnlProduto.setBackground(azul);
		pnlBotoes.setBackground(azul); 
		pnlEspaco.setBackground(azul); 
		pnlEspaco2.setBackground(azul); 
		pnlEspaco3.setBackground(azul); 
		pnlEspaco4.setBackground(azul); 
		pnlValores.setBackground(azul); 
		pnlNorte.setBackground(azul); 
		pnlTudo.setBackground(azul); 
		
		//---------------------------------COMBO FUNCIONÁRIO
		txtValor.setEditable(false);
		cmbFuncionario.removeAll();
		cmbFuncionario.addItem("Vendedor...");
		this.preencherFuncionario();
		
		//---------------------------------COMBO CLIENTE
				cmbCliente.removeAll();
				cmbCliente.addItem("Nenhum cliente selecionado");
				this.preencherCliente();
		
		try {//---------------------------ÍCONE
		    this.setIconImage(ImageIO.read(new File("res/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource () == btnSair){
			Venda.this.dispose();
		}
		else if(e.getSource() == btnCancelar){
			limpar();			
		}
		else if(e.getSource() == btnVender){
			vender();
			limpar();
		}	
		else if(e.getSource() == btnAdicionar){
			adicionar();
		}
		else if(e.getSource() == btnProdutos){
			ProdutoR pr = new ProdutoR();
			pr.setVisible(true);
			pr.setResizable(true);
		}
		
	}
	
	public void limpar(){
		cmbForma_pagto.setSelectedIndex(0);
		cmbCliente.setSelectedIndex(0);
		txtHora.setText("");
		txtValor.setText("");
		txtProduto.setText("");
		dataHora = LocalDateTime.now();
		lblData.setText("Data: "+ dtf.format(dataHora));
		limparTabela();
	}
	
	private void limparTabela() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}
	
	public void preencherFuncionario(){
		//------------------------GERAR ARRAY DE FUNCIONARIOS
				
				try {
					db.conectar();
					db.setRs(db.getStm().executeQuery("select nome from funcionario where cargo = 'Vendedor'"));
					
					while(db.getRs().next()){
						cmbFuncionario.addItem(db.getRs().getString("nome"));
					}
					cmbFuncionario.updateUI();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	}
	
	public void preencherCliente(){
		//------------------------GERAR ARRAY DE CLIENTES
				
				try {
					db.conectar();
					db.setRs(db.getStm().executeQuery("select nome from cliente"));
					
					while(db.getRs().next()){
						cmbCliente.addItem(db.getRs().getString("nome"));
					}
					cmbCliente.updateUI();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	}
	
	public JScrollPane gerarTabela() throws Exception{ 

		modelo = new DefaultTableModel(new Object[][] {},
				new Object [] {"Código", "Nome do Produto", "Valor Unt."});
		
				tabela = new JTable(modelo);
				tabela.setEnabled(false);
				tabela.getTableHeader().setFont(new Font("Arial Black", 5, 12));
				
				tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
				tabela.getColumnModel().getColumn(1).setPreferredWidth(180);
				tabela.getColumnModel().getColumn(2).setPreferredWidth(15);
				
				modelo = (DefaultTableModel) tabela.getModel();
				JScrollPane scroller = new JScrollPane(tabela); //painel especial com barra de rolagem

				return scroller;
			}
		
	public void adicionar(){
		try {
			db.conectar();
			db.setRs(db.getStm().executeQuery("select * from produto where codigo_produto ="+txtProduto.getText()));
			
			if(db.getRs().next() && db.getRs().getInt("quantidade") > 0){
				modelo.addRow(new String[]{db.getRs().getString("codigo_produto"), db.getRs().getString("nome_produto"), db.getRs().getString("preco")});
				
				double total = 0;
				for (int i = 0; i < tabela.getRowCount(); i++)
					total += Double.parseDouble(modelo.getValueAt(i, 2).toString());
				txtValor.setText(""+total);
				
			}
			else
				JOptionPane.showMessageDialog(null, "Produto não encontrado ou não disponível!");
				txtProduto.setText("");
			db.getStm().close();
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro, inicie uma nova venda.");
			e.printStackTrace();
		}
	}
	
	public void vender(){
		try{ //-------------------INSERE NA TABELA VENDA
			db.conectar();
		db.setRs(db.getStm().executeQuery("select codigo_funcionario from funcionario where funcionario.nome = '"+cmbFuncionario.getItemAt(cmbFuncionario.getSelectedIndex())+"'" ));	
		if (db.getRs().next())	
				db.getStm().executeUpdate("insert into venda(data, hora, forma_pagamento, valor, cliente_codigo_cliente, funcionario_Codigo_funcionario)"						
				+ " values (CURDATE(), CURTIME(), '"+cmbForma_pagto.getItemAt(cmbForma_pagto.getSelectedIndex())+"', "+txtValor.getText()+","+cmbCliente.getSelectedIndex()+","+db.getRs().getInt("codigo_funcionario")+")"); 
		
			JOptionPane.showMessageDialog(null, "Venda efetuada com sucesso!"); 
			db.getStm().close(); 
		}
		catch(Exception excp){
			excp.printStackTrace(); 
		}
		try{ //---------------------INSERE NA TABELA VENDA_TEM_PRODUTO
			for(int j = 0; j<modelo.getRowCount(); j++){
			db.conectar();
			db.setRs(db.getStm().executeQuery("select max(codigo_venda) as codigo_venda from venda;" ));	

			if (db.getRs().next())	
				db.getStm().executeUpdate("insert into venda_tem_produto(venda_codigo_venda, produto_codigo_produto)"						
				+ " values ("+db.getRs().getInt("codigo_venda")+", "+modelo.getValueAt(j, 0)+")"); 
			//Diminui do estoque:
			db.getStm().executeUpdate("update produto set quantidade = quantidade - 1 where codigo_produto ="+modelo.getValueAt(j, 0)); 
		
			db.getStm().close();
			}
			JOptionPane.showMessageDialog(null, "Produtos vendidos com sucesso!");
			
		}
		catch(Exception excp){
			excp.printStackTrace(); 
		}
		
	}
	
	public static void main(String[] args) {
		Venda v = new Venda();
		v.setVisible(true);
		v.setResizable(true);
	}
}
