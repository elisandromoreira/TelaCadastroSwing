package br.com.elisandro.testes;

import java.util.List;

import br.com.elisandro.cadastro.modelo.dao.PessoaDAO;
import br.com.elisandro.cadastro.modelo.entidades.Pessoa;

public class TesteReadById {
	
	public static void main(String[] args) {
		
		Pessoa pessoa = new Pessoa();
		
		PessoaDAO dao = new PessoaDAO();
		pessoa = dao.readById(3);
		
		System.out.println(pessoa.getId() + " | " + pessoa.getNome() + " | " 
				   + pessoa.getTelefone() + " | " + pessoa.getEmail());
								
	}

}
