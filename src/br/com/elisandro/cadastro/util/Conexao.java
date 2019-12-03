package br.com.elisandro.cadastro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class Conexao {
	
	public static Connection conexao = null;
	public static ResourceBundle config = ResourceBundle.getBundle("br.com.elisandro.cadastro.util.db");
	
	public static Connection getConexao() {
		
		try {
			Class.forName(config.getString("banco.driver"));
			conexao = DriverManager.getConnection(config.getString("banco.url"), 
												  config.getString("banco.usuario"), 
												  config.getString("banco.senha"));
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao Banco de Dados!");
		}
		
		return conexao;
	}
	
}
