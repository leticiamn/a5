package com.example.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.a5.entities.Client;
import com.example.a5.repositories.ClientRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository repositorio;

	/**
	 * Cenário de teste 1
	 * Objetivo: Verificar se a exclusão realmente apaga um registro existente.
	 * monta o cenário,
	 * - arquivo import.sql carrega o cenário (clientes cadastrados)
	 * - definir o id de um cliente que exista em import.sql
	 * executa a ação
	 * - executar o método de exclusão por id
	 * - executar o método de buscar por id
	 * e valida a saída.
	 * - verificar se o resultado do método de busca é falso
	 */
	@Test
	public void testarSeDeleteApagaClienteComIdExistente() {
		// cenário
		long id = 1;
		repositorio.deleteById(id);
		Optional<Client> resposta = repositorio.findById(id);
		Assertions.assertFalse(resposta.isPresent());

	}

	/**
	 * Cenário de teste 2
	 * Objetivo: Verificar se a exclusão retorna um erro quando um id não existente
	 * é informado.
	 * monta o cenário,
	 * - arquivo import.sql carrega o cenário (clientes cadastrados)
	 * - definir o id de um cliente que não exista em import.sql
	 * executa a ação
	 * - executar o método de exclusão por id
	 * e valida a saída.
	 * - verificar se ocorre o erro: EmptyResultDataAccessException
	 */
	@Test
	public void testarSeDeleteRetornaExceptionCasoIdNaoExiste() {
		long id = 10000;
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repositorio.deleteById(id);
		});
	}

	/**
	 * Cenário de teste 3.
	 * Objetivo: Verificar se a exclusão de todos elementos realmente apaga todos os
	 * registros do Banco de dados.
	 * monta o cenário,
	 * - arquivo import.sql carrega o cenário (clientes cadastrados)
	 * executa a ação
	 * - executar o método de exclusão de todos registros
	 * e valida a saída.
	 * - consultar todos os registros do banco e verificar se retorna vazio.
	 */
	@Test
	public void testarSeDeleteApagaTodosRegistros() {
		repositorio.deleteAll();
		List<Client> resultado = repositorio.findAll();
		Assertions.assertTrue(resultado.isEmpty());
	}

	/**
	 * Cenário de teste 4
	 * Objetivo: Verificar se a exclusão de uma entidade existente no banco de dados
	 * realmente ocorre.
	 * monta o cenário,
	 * - arquivo import.sql carrega o cenário (clientes cadastrados)
	 * - id de um cliente que existe em import.sql
	 * executa a ação
	 * - executa o método encontrar por id para retornar a entidade do cliente com
	 * id informado.
	 * - executa o método apagar a entidade
	 * - executar novamente o método encontrar por id e verificar se o retorno dele
	 * é vazio
	 * e valida a saída.
	 * - verifica se o retorno do método encontrar por id é vazio.
	 */
	@Test
	public void testarSeDeleteApagaUmRegistroComIDExistente() {
		long id = 4;
		Optional<Client> cliente = repositorio.findById(id);
		repositorio.delete(cliente.get());
		Optional<Client> resultado = repositorio.findById(id);
		Assertions.assertTrue(resultado.isEmpty());
	}

	/*
	 * Cenário de Teste 5
	 * Objetivo: Verificar se um cliente pode ser excluído pelo cpf.
	 * monta o cenário,
	 * - arquivo import.sql carrega o cenário (clientes cadastrados)
	 * - cpf de um cliente cadastrado (10919444522)
	 * executa a ação
	 * - executar um método para excluir um cliente pelo cpf (não existe ainda).
	 * - buscar um cliente pelo cpf (não existe)
	 * e valida a saída.
	 * - a busca deve retornar vazia.
	 */
	@Test
	public void testarSeDeleteApagaUmClientePorCpfExiste() {
		String cpfExistente = "10619244881";
		repositorio.deleteByCpf(cpfExistente);
		System.out.println(repositorio.findAll().size());
		Optional<Client> resultado = repositorio.findByCpf(cpfExistente);
		Assertions.assertTrue(resultado.isEmpty());
	}

	@Test
	public void testar() {
		double salarioI = 2000;
		repositorio.deleteByIncomeGreaterThan(salarioI);
		List<Client> resultado = repositorio.findByIncomeGreaterThan(salarioI);
		Assertions.assertTrue(resultado.isEmpty());
	}

	/* criar um método personalizado para buscar um cliente pelo seu nome. */

	/*
	 * • Testar o método que retorna o cliente com nome existente;
	 * ◦ Testar um nome existente;
	 * ◦ Testar um nome não existente;
	 * • Testar o método que retorna vários cliente com parte do nome similar ao
	 * texto
	 * informado;
	 * ◦ Testar um texto existente;
	 * ◦ Testar um texto não existente;
	 * ◦ Testar find para nome vazio (Neste caso teria que retornar todos os
	 * clientes);
	 * • Testar o método que retorna vários cliente baseado no salário;
	 * ◦ Testar o método que busca clientes com salários superiores a um valor;
	 * ◦ Testar o método que busca clientes com salários inferiores a um valor;
	 * ◦ Testar o método que busca clientes com salários que esteja no intervalo
	 * entre
	 * dois valores informados.
	 * • Testar o método que retorna vários cliente baseado na sua data de
	 * aniversário.
	 * ◦ Teste o método buscando clientes que nasceram entre duas datas, sugestão
	 * uma data qualquer e a data atual. Códigos para criar uma classe que
	 * representa a data.
	 * ▪ Instant dataI = Instant.parse("2017-12-25T20:30:50Z");
	 * ▪ Instant dataT = Instant.now();
	 * • Testar o update (save) de um cliente. Modifique o nome, o salário e o
	 * aniversário e
	 * utilize os métodos criados anteriormente para verificar se realmente foram
	 * modificados.
	 */
	@Test
	public void testeFindByNomeExistente() {
		// ArrayList<Client> list = new ArrayList<Client>();

		List<Client> resultado = repositorio.findByLowerNameLike("Clarice");
		Assertions.assertFalse(resultado.isEmpty());
	}

}
