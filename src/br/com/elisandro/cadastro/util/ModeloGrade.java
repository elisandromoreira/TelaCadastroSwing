package br.com.elisandro.cadastro.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloGrade extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<String> colunas;
	private List<List<Object>> linhas;
	
	
	public ModeloGrade() {
		colunas = new ArrayList<String>();
		linhas = new ArrayList<List<Object>>();
	}
	
	
	public ModeloGrade(String[] titulos) {
		colunas = new ArrayList<String>();
		for (int i = 0; i < titulos.length; i++ )
			colunas.add(titulos[1]);
				
		linhas = new ArrayList<List<Object>>();
	}
	
	public ModeloGrade(ResultSet rs, String[] titulos) {
		this();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			if (titulos != null) 
				for (int i = 0; i < titulos.length; i++)
					colunas.add( titulos[i] );
			
			else 
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
					colunas.add(rsmd.getColumnLabel(i));
			
			
			while (rs.next()) {
				ArrayList<Object> linha = new ArrayList<Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) 
					linha.add(rs.getObject(i));
				
				linhas.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int getColumnCount() {
		return colunas.size();
	}
	
	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		return linhas.get(linha).get(coluna);
	}
	
	public List<List<Object>> getLinhas() {
		return linhas;
	}
	
	public void excluiLinha(int linha) {
		linhas.remove(linha);
	}
	
	public void inserirLinha(List<Object> linha) {
		linhas.add(linha);
	}
	
	public void limparLinhas() {
		linhas.clear();
	}
	
	@Override
	public String getColumnName(int indice) {
		return colunas.get(indice);
	}

}
