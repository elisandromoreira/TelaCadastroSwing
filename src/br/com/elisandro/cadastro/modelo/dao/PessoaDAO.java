package br.com.elisandro.cadastro.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.elisandro.cadastro.modelo.entidades.Pessoa;
import br.com.elisandro.cadastro.util.Conexao;
import br.com.elisandro.cadastro.util.ConexaoBD;


public class PessoaDAO {
	
	private Connection conn;
	PreparedStatement st;
	ResultSet rs;
	
	public PessoaDAO() {
		this.conn = new Conexao().getConexao();
	}
	
	public void create(Pessoa pessoa) {
		String sql = "INSERT INTO pessoa (nome, telefone, email) value (?, ?, ?)";
		try {
			int i = 0;
			st = conn.prepareStatement(sql);
			st.setString(++i, pessoa.getNome());
			st.setString(++i, pessoa.getTelefone());
			st.setString(++i, pessoa.getEmail());
			st.execute();
			
			st.close();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar!");
		}
	}
	
	public List<Pessoa> read() {
		List<Pessoa> ls = new ArrayList<Pessoa>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa");
			rs = st.executeQuery();
			
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt(1));
				pessoa.setNome(rs.getString(2));
				pessoa.setTelefone(rs.getString(3));
				pessoa.setEmail(rs.getString(4));
				
				ls.add(pessoa);
			}
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Consultar!");
		}
		return ls;
	}
	
	public void update(Pessoa pessoa) {
		String sql = "UPDATE pessoa SET nome = ?, telefone = ?, email = ? WHERE id = ?";
		try {
			int i = 0;
			st = conn.prepareStatement(sql);
			st.setString(++i, pessoa.getNome());
			st.setString(++i, pessoa.getTelefone());
			st.setString(++i, pessoa.getEmail());
			st.setInt(++i, pessoa.getId());
			st.execute();
			
			st.close();
			conn.close();			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
		}
		
		
	}
	
	public void delete(int id) {
		try {
			st = conn.prepareStatement("DELETE FROM pessoa WHERE id = ?");
			st.setInt(1, id);
			st.execute();
			
			st.close();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Deletar!");
		}
		
	}
	
	
	public Pessoa readById(int id) {
		Pessoa pessoa = new Pessoa();
		
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			rs.first();
			
			pessoa.setId(rs.getInt(1));
			pessoa.setNome(rs.getString(2));
			pessoa.setTelefone(rs.getString(3));
			pessoa.setEmail(rs.getString(4));
						
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Consultar!");
		}
		return pessoa;
	}
	
	
	public List<Pessoa> readByName(String texto) {
		List<Pessoa> ls = new ArrayList<Pessoa>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa Where nome LIKE ?");
			st.setString(1, '%' + texto + '%');
			rs = st.executeQuery();
			
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt(1));
				pessoa.setNome(rs.getString(2));
				pessoa.setTelefone(rs.getString(3));
				pessoa.setEmail(rs.getString(4));
				
				ls.add(pessoa);
			}
			st.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Consultar!");
		}
		return ls;
	}
	
	public ResultSet carregarGrade() {
		
		try {
			st = conn.prepareStatement("SELECT Id, Nome FROM pessoa");
			rs = st.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um Erro!");
		}
		
		return rs;
	}
	
	public List<String> nomeCampos() {
		List<String> campos = new ArrayList<String>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa limit 1");
			rs = st.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i=1; i <= rsmd.getColumnCount(); i++)
				campos.add(rsmd.getColumnName(i));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return campos;
		
	}
	
public ResultSet pesquisa(String campo, String valor) {
		String sql = "SELECT Id, Nome FROM pessoa WHERE " + campo + " like '%" + valor + "%'";
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um Erro!");
		}
		
		return rs;
	}
		
	
}
