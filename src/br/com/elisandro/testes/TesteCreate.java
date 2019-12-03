package br.com.elisandro.testes;

import br.com.elisandro.cadastro.modelo.dao.PessoaDAO;
import br.com.elisandro.cadastro.modelo.entidades.Pessoa;

public class TesteCreate {
	
	public static void main(String[] args) {
		
		Pessoa p = new Pessoa();
		p.setNome("Jon Snow");
		p.setTelefone("9999999999");
		p.setEmail("patrulhadanoite@patrulhadanoite.com");	
		
		PessoaDAO dao = new PessoaDAO();
		dao.create(p);
		
		System.out.println("Foi!");
		
	}

}
