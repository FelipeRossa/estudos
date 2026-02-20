package com.sr.development.resources;

import java.util.List;

import com.sr.development.models.Pessoa;

import io.micrometer.core.annotation.Counted;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResouce {

	@GET
	@Counted(value = "counted.getPessoa")
	public List<Pessoa> getPessoa() {
		return Pessoa.listAll();
	}
	
	@GET
	@Path("anoNascimento")
	public List<Pessoa> findByAnoNascimento(int ano) {
		return Pessoa.findByAnoNascimento(ano);
	}

	@POST
	@Transactional
	public Pessoa addPessoa(Pessoa pessoa) {
		pessoa.id = null;
		pessoa.persist();
		return pessoa;

	}

	@PUT
	@Transactional
	public Pessoa updatePessoa(Pessoa pessoa) {
		Pessoa p = Pessoa.findById(pessoa.id);
		p.nome = pessoa.nome;
		p.anoNascimento = pessoa.anoNascimento;

		p.persist();

		return p;
	}

	@DELETE
	@Transactional
	public void deletePessoa(int idPessoa) {
		Pessoa.deleteById(idPessoa);
	}
}
