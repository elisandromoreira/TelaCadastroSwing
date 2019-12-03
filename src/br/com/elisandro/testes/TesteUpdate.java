package br.com.elisandro.testes;

import java.util.List;

import br.com.elisandro.cadastro.modelo.dao.PessoaDAO;
import br.com.elisandro.cadastro.modelo.entidades.Pessoa;

public class TesteUpdate {
public static void main(String[] args) {
		
		Pessoa p = new Pessoa();
		p.setId(2);
		p.setNome("Elisandro Moreira");
		p.setTelefone("47996485847");
		p.setEmail("elisandromoreira@gmail.com");
		
				
		PessoaDAO dao = new PessoaDAO();
		dao.update(p);
		System.out.println("Atualizado!!!");
		
		PessoaDAO dao2 = new PessoaDAO();
		List<Pessoa> ls = dao2.read();
		
		for(Pessoa pessoa : ls) {
			System.out.println(pessoa.getId() + " | " + pessoa.getNome() + " | " 
							   + pessoa.getTelefone() + " | " + pessoa.getEmail());
		}
		
	}

}
