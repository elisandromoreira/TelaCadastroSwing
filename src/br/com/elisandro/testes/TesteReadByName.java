package br.com.elisandro.testes;

import java.util.List;

import br.com.elisandro.cadastro.modelo.dao.PessoaDAO;
import br.com.elisandro.cadastro.modelo.entidades.Pessoa;

public class TesteReadByName {
	
	public static void main(String[] args) {
		
		PessoaDAO dao = new PessoaDAO();
		
		List<Pessoa> ls = dao.readByName("an");
		
		for(Pessoa pessoa : ls) {
			System.out.println(pessoa.getId() + " | " + pessoa.getNome() + " | " 
							   + pessoa.getTelefone() + " | " + pessoa.getEmail());
		}
						
	}

}
