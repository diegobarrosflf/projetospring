package com.example.demo;

import java.text.SimpleDateFormat;
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
import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoBoleto;
import com.example.demo.domain.PagamentoCredito;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.StatusPagamento;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ItemPedidoRepository;
import com.example.demo.repositories.PagamentoRepository;
import com.example.demo.repositories.PedidoRepository;
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

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetospringApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");
		Categoria cat3 = new Categoria(null, "Cozinha");
		Categoria cat4 = new Categoria(null, "TV");
		Categoria cat5 = new Categoria(null, "Games");
		

		Produto p1 = new Produto(null, "notebook_DELL", 1500.00);
		Produto p2 = new Produto(null, "impressora", 350.00);
		Produto p3 = new Produto(null, "cooktop", 600.00);
		Produto p4 = new Produto(null, "SmartTV", 1500.00);
		Produto p5 = new Produto(null, "PlayStation 4", 2500.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p3));
		cat4.getProdutos().addAll(Arrays.asList(p4));
		cat5.getProdutos().addAll(Arrays.asList(p5));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p4.getCategorias().addAll(Arrays.asList(cat4));
		p5.getCategorias().addAll(Arrays.asList(cat5));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		Estado est1 = new Estado(null, "Ceará");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cd1 = new Cidade(null, "Fortaleza", est1);
		Cidade cd2 = new Cidade(null, "São Paulo", est2);
		Cidade cd3 = new Cidade(null, "Lorena", est2);

		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));

		Cliente cl1 = new Cliente(null, "Zezin da Chica Boa", "jose@email.com", "65314725896",
				TipoCliente.PESSOA_FISICA);
		Cliente cl2 = new Cliente(null, "Jucileudo Filho", "juju@email.com", "95198432678", TipoCliente.PESSOA_FISICA);

		Endereco end1 = new Endereco(null, "Rua 3 de maio", "235", "casa 3A", "Bela Vista", "60440580", cd1);
		Endereco end2 = new Endereco(null, "Rua Silva Bueno", "222", "sala 6069", "Ipiranga", "04208002", cd2);

		cl1.getEnderecos().add(end1);
		cl1.getTelefones().addAll(Arrays.asList("3482-6565", "99865-6565"));

		cl2.getEnderecos().add(end2);
		cl2.getTelefones().addAll(Arrays.asList("9875-6598", "6548-9875"));

		end1.setCliente(cl1);
		end2.setCliente(cl2);

		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("20/05/2020 20:15"), cl1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("23/05/2020 18:30"), cl2, end2);

		Pagamento pg1 = new PagamentoCredito(null, StatusPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pg1);

		Pagamento pg2 = new PagamentoBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("26/05/2020 10:20"), null);
		ped2.setPagamento(pg2);

		cl1.getPedidos().add(ped1);
		cl2.getPedidos().add(ped2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pg1,pg2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, p1.getPreco());
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.0, 2, p2.getPreco());
		ItemPedido ip3 = new ItemPedido(ped1, p3, 0.0, 1, p3.getPreco());
		ItemPedido ip4 = new ItemPedido(ped2, p4, 0.0, 1, p4.getPreco());
		ItemPedido ip5 = new ItemPedido(ped2, p5, 0.0, 2, p5.getPreco());
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2,ip3));
		ped2.getItens().addAll(Arrays.asList(ip4,ip5));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));
		p4.getItens().addAll(Arrays.asList(ip4));
		p5.getItens().addAll(Arrays.asList(ip5));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5));
		

	}

}
