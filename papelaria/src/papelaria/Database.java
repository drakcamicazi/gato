package papelaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database {

	private String tabela, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, val1, val2, val3, val4, val5, val6, val7, val8, val9, val10;
	
	private String url = "jdbc:mysql://localhost/papelaria?useSSL=false", 
			usuario = "root",
			senha = "root";
	private Connection conexao;
	private Statement stm;
	private ResultSet rs;
	
	public void conectar(){
		try {
			setConexao(DriverManager.getConnection(url, usuario, senha));
			setStm(getConexao().createStatement()); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	public void inserir() {
		try{ 
			conectar();
			
				getStm().executeUpdate("insert into "+tabela+"("+col1+", "+col2+", "+col3+", "+col4+", "+col5+", "+col6+", "+col7+", "+col8+", "+col9+", "+col10+")"						
				+ " values ('"+val1+"', '"+val2+"', '"+val3+"', '"+val4+"', '"+val5+"', '"+val6+"', '"+val7+"', '"+val8+"', '"+val9+"', '"+val10+"' )"	); 
			
			
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!"); 
			getStm().close(); 
		}
		catch(Exception excp){
			excp.printStackTrace(); 
		}
	}
		
	public void inserir(String tabela, String col1, String col2, String col3, String col4, String col5, String col6, String val1, String val2, String val3, String val4, String val5, String val6) {
			
		try{ 
				conectar();
				
					getStm().executeUpdate("insert into "+tabela+"("+col1+", "+col2+", "+col3+", "+col4+", "+col5+", "+col6+")"						
					+ " values ('"+val1+"', '"+val2+"', '"+val3+"', '"+val4+"', '"+val5+"', '"+val6+"' )"	); 
				
				
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!"); 
				getStm().close(); 
			}
			catch(Exception excp){
				excp.printStackTrace(); 
			}
	}
	public void inserir(String tabela, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8) {
		
		try{ 
				conectar();
				
					getStm().executeUpdate("insert into "+tabela+"("+col1+", "+col2+", "+col3+", "+col4+", "+col5+", "+col6+", "+col7+", "+col8+")"						
					+ " values ('"+val1+"', '"+val2+"', '"+val3+"', '"+val4+"', '"+val5+"', '"+val6+"', '"+val7+"', "+val8+" )"	); 
				
				
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!"); 
				getStm().close(); 
			}
			catch(Exception excp){
				excp.printStackTrace(); 
			}
	}
	public void inserir(String tabela, String col1, String col2, String col3, String col4, String col5, String val1, String val2, String val3, String val4, String val5) {
		
		try{ 
				conectar();
					getStm().executeUpdate("insert into "+tabela+"("+col1+", "+col2+", "+col3+", "+col4+", "+col5+")"						
					+ " values ('"+val1+"', '"+val2+"', '"+val3+"', '"+val4+"', '"+val5+"' )"	); 
				
				
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!"); 
				getStm().close(); 
			}
			catch(Exception excp){
				excp.printStackTrace(); 
			}
	}
	
	public void inserirProduto(String nome, String marca, String tipo, String preco, String quantidade, String fornecedor) {
		
		try{ 
				conectar();
			ResultSet rs =getStm().executeQuery("select codigo_fornecedor from fornecedor where razao_social = '"+fornecedor+"'" );	
			if (rs.next())	
					getStm().executeUpdate("insert into produto(nome_produto, tipo, marca, preco, quantidade, fornecedor_Codigo_Fornecedor)"						
					+ " values ('"+nome+"', '"+tipo+"', '"+marca+"', "+preco+","+quantidade+","+rs.getInt("codigo_fornecedor")+")"); 
					
				
				
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!"); 
				getStm().close(); 
			}
			catch(Exception excp){
				excp.printStackTrace(); 
			}
	}
	
	public void adicionarEstoque(String quantidade, String cod ){
		try{ 
			conectar();
				getStm().executeUpdate("update produto set quantidade = quantidade + "+ quantidade +" where codigo_produto = "+cod); 
			
			JOptionPane.showMessageDialog(null, quantidade+" produtos adicionados ao estoque!"); 
			getStm().close(); 
		}
		catch(Exception excp){
			excp.printStackTrace(); 
		}
	}

	public Connection getConexao() {
		return conexao;
	}
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	public Statement getStm() {
		return stm;
	}
	public void setStm(Statement stm) {
		this.stm = stm;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}	
}