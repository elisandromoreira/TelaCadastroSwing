package br.com.elisandro.cadastro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD {
	
	public static Connection conexao = null;
	
	public static Connection getConexao() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/contatos?autoReconnect=true&useSSL=false", "root", "123456");
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao Banco de Dados!");
		}
		
		return conexao;
	}
	
}
