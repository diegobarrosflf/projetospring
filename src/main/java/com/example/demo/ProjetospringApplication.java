package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Produto;
import com.example.demo.domain.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetospringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetospringApplication.class, args);
					
	}
	
	@Override
	public void run(String... args)throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");
		Categoria cat3 = new Categoria(null, "Cozinha");
		
		Produto p1 = new Produto(null, "notebook_DELL", 1500.00);
		Produto p2 = new Produto(null, "impressora", 350.00);
		Produto p3 = new Produto(null, "cooktop", 600.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));	
		
		Estado est1 = new Estado(null, "Ceará");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cd1 = new Cidade(null, "Fortaleza", est1);
		Cidade cd2 = new Cidade(null, "São Paulo", est2);
		Cidade cd3 = new Cidade(null, "Lorena", est2);
		
		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cd1,cd2,cd3));
		
		Cliente cl1 = new Cliente(null, "Zezin da Chica Boa", "jose@email.com", "65314725896", TipoCliente.PESSOA_FISICA);
		Cliente cl2 = new Cliente(null, "Jucileudo Filho", "juju@email.com", "95198432678", TipoCliente.PESSOA_FISICA);
		
		Endereco end1 = new Endereco(null, "Rua 3 de maio", "235", "casa 3A", "Bela Vista", "60440580", cd1);
		Endereco end2 = new Endereco(null, "Rua Silva Bueno", "222", "sala 6069", "Ipiranga", "04208002", cd2);
		
		cl1.getEnderecos().add(end1);
		cl1.getTelefones().addAll(Arrays.asList("3482-6565","99865-6565"));
		
		cl2.getEnderecos().add(end2);
		cl2.getTelefones().addAll(Arrays.asList("9875-6598","6548-9875"));
		
		end1.setCliente(cl1);
		end2.setCliente(cl2);
		
		clienteRepository.saveAll(Arrays.asList(cl1,cl2));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
	}

}
