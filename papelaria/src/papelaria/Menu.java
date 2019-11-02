package papelaria;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener {
	
	private Font fonte;

	private JMenuBar barraMenu = new JMenuBar();
    private JMenu mnCadastros = new JMenu("Cadastros");
    private JMenu mnRelatorios = new JMenu("Relatórios");
    private JMenu mnOutros = new JMenu("Outros");
    private JMenuItem mnitemClienteC = new JMenuItem("Cliente");
    private JMenuItem mnitemFuncionarioC = new JMenuItem("Funcionário");
    private JMenuItem mnitemFornecedorC = new JMenuItem("Fornecedor");
    private JMenuItem mnitemProdutoC = new JMenuItem("Produto");

    private JMenuItem mnitemClienteR = new JMenuItem("Cliente");
    private JMenuItem mnitemFuncionarioR = new JMenuItem("Funcionário");
    private JMenuItem mnitemFornecedorR = new JMenuItem("Fornecedor");
    private JMenuItem mnitemProdutoR = new JMenuItem("Produto");
    private JMenuItem mnitemVendaR = new JMenuItem("Venda");
    
    
    private JMenuItem mnitemVenda = new JMenuItem("Venda");    
    private JMenuItem mnitemSobre = new JMenuItem("Sobre");    
    private JMenuItem mnitemEstoque = new JMenuItem("Estoque");
	private JMenuItem mnitemSair = new JMenuItem("Sair");
	
	private JPanel painel1 = new JPanel(),
		           painel2 = new JPanel(),
				   painel4 = new JPanel(new BorderLayout()),
			       painel3 = new JPanel(new GridLayout(5, 1, 20, 20));
	
	private JLabel  fig = new JLabel(),
			espaco = new JLabel(),
			lblMMPapelaria = new JLabel ("M&M Papelaria");
	
	private JButton btnVenda = new JButton("Nova Venda"),
			btnCliente = new JButton("Cadastro de Cliente"),
			btnSair = new JButton("Sair");

	private Color azul = new Color(181,227,240);

	public Menu (){
		
    	mnCadastros.add(mnitemClienteC);
        mnCadastros.addSeparator();
        mnCadastros.add(mnitemProdutoC);
        mnCadastros.addSeparator();
        mnCadastros.add(mnitemFuncionarioC);
        mnCadastros.addSeparator();
        mnCadastros.add(mnitemFornecedorC);
        
        mnRelatorios.add(mnitemClienteR);
        mnRelatorios.addSeparator();
        mnRelatorios.add(mnitemProdutoR);
        mnRelatorios.addSeparator();
        mnRelatorios.add(mnitemFuncionarioR);
        mnRelatorios.addSeparator();
        mnRelatorios.add(mnitemFornecedorR);
        mnRelatorios.addSeparator();
        mnRelatorios.add(mnitemVendaR);
        
        mnOutros.add(mnitemVenda);
        mnOutros.addSeparator();
        mnOutros.add(mnitemEstoque);
        mnOutros.addSeparator();
        mnOutros.add(mnitemSobre);
        mnOutros.addSeparator();
        mnOutros.add(mnitemSair);
        
        barraMenu.add(mnCadastros);
        barraMenu.add(mnRelatorios);
        barraMenu.add(mnOutros);
        
        mnitemEstoque.addActionListener(this);
        mnitemClienteC.addActionListener(this);
        mnitemFuncionarioC.addActionListener(this);
        mnitemFornecedorC.addActionListener(this);
        mnitemProdutoC.addActionListener(this);
        mnitemClienteR.addActionListener(this);
        mnitemFuncionarioR.addActionListener(this);
        mnitemFornecedorR.addActionListener(this);
        mnitemProdutoR.addActionListener(this);
        mnitemVendaR.addActionListener(this);
        mnitemVenda.addActionListener(this);
        mnitemSobre.addActionListener(this);
    	mnitemSair.addActionListener(this);
    	
    	btnVenda.addActionListener(this);
    	btnSair.addActionListener(this);
    	btnCliente.addActionListener(this);
    	
    	painel4.add(painel1, BorderLayout.NORTH);
    	painel4.add(painel2, BorderLayout.CENTER);
        
    	//---------------------------FONTE
		try {
	        fonte = Font.createFont(Font.TRUETYPE_FONT, new File("res\\a song for jennifer.ttf")).deriveFont(40f);
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res\\a song for jennifer.ttf")));
	        lblMMPapelaria.setFont(fonte);
	        
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	    catch(FontFormatException e)
	    {
	        e.printStackTrace();
	    }

    	painel1.add(lblMMPapelaria);
    	
        this.setJMenuBar(barraMenu);
        
        this.fig.setBounds(500,100,200,200);
        ImageIcon img = new ImageIcon("res/icon620circulo.png");
        
        img.setImage(img.getImage().getScaledInstance(this.fig.getWidth(), this.fig.getHeight(), 1));
        fig.setIcon(img);
		add(painel4, BorderLayout.CENTER);
		add(painel3, BorderLayout.EAST);
		
		painel1.setBackground(azul); 
		painel3.setBackground(azul); 
		
		painel3.add(espaco);
		painel3.add(btnVenda);
		painel3.add(btnCliente);
		painel3.add(btnSair);
		
		painel2.add(fig);
		painel2.setBackground(azul); 
		
		setBounds(600, 300, 500, 370);
		setVisible(true);
        setTitle("Menu");
        
		try {
		    this.setIconImage(ImageIO.read(new File("res/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e){	
		if(e.getSource () == mnitemSair){
			System.exit(0);
		}
		else
			if(e.getSource () == mnitemSobre){
				JOptionPane.showMessageDialog(null, "'M&M Papelaria 1.0' \nCriado por Milena Andrade e Michele Guedes para a matéria de Programação Orientada a Objetos, 4º Etapa.");
			}
			else
				if(e.getSource () == mnitemClienteC){
					ClienteC cc = new ClienteC("Cliente", 540, 380);
					cc.setVisible(true);
					cc.setResizable(false);
				}
				else
					if(e.getSource () == btnSair){
						System.exit(0);
					}
					else
						if (e.getSource () == btnCliente){
							ClienteC cc = new ClienteC("Cliente", 540, 380);
							cc.setVisible(true);
							cc.setResizable(false);
						}
					else
						if(e.getSource () == mnitemFuncionarioC){
							FuncionarioC fc = new FuncionarioC("Funcionário");
							fc.setVisible(true);
							fc.setResizable(false);
						}
						else
							if(e.getSource () == mnitemFornecedorC){
								FornecedorC forc = new FornecedorC();
								forc.setVisible(true);
								forc.setResizable(false);
							}
							else
								if(e.getSource () == mnitemProdutoC){
									ProdutoC prod = new ProdutoC();
									prod.setVisible(true);
									prod.setResizable(false);
								}
								else
									if(e.getSource () == mnitemVenda){
										Venda v = new Venda();
										v.setVisible(true);
										v.setResizable(true);
									}
									else
										if(e.getSource () == btnVenda){
											Venda v = new Venda();
											v.setVisible(true);
											v.setResizable(true);
										}
										else
											if(e.getSource () == mnitemClienteR){
												ClienteR cr = new ClienteR();
												cr.setVisible(true);
												cr.setResizable(true);
											}
											else
												if(e.getSource () == mnitemFuncionarioR){
													FuncionarioR fr = new FuncionarioR();
													fr.setVisible(true);
													fr.setResizable(true);
												}
												else
													if(e.getSource () == mnitemFornecedorR){
														FornecedorR forr = new FornecedorR();
														forr.setVisible(true);
														forr.setResizable(true);
													}
													else
														if(e.getSource () == mnitemProdutoR){
															ProdutoR pr = new ProdutoR();
															pr.setVisible(true);
															pr.setResizable(true);
														}
														else
															if(e.getSource () == mnitemVendaR){
																VendaR vr = new VendaR();
																vr.setVisible(true);
																vr.setResizable(true);
															}
															else
																if(e.getSource () == mnitemEstoque){
																	AlterarEstoque ae = new AlterarEstoque();
																	ae.setVisible(true);
																	ae.setResizable(false);
																}
	}

     @SuppressWarnings("unused")
	public static void main (String[]args){
           Menu m = new Menu();
}
}
