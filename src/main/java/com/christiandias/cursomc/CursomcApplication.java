package com.christiandias.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.christiandias.cursomc.domain.Categoria;
import com.christiandias.cursomc.domain.Cidade;
import com.christiandias.cursomc.domain.Cliente;
import com.christiandias.cursomc.domain.Endereco;
import com.christiandias.cursomc.domain.Estado;
import com.christiandias.cursomc.domain.Produto;
import com.christiandias.cursomc.domain.enums.TipoCliente;
import com.christiandias.cursomc.repositories.CategoriaRepository;
import com.christiandias.cursomc.repositories.CidadeRepository;
import com.christiandias.cursomc.repositories.ClienteRepository;
import com.christiandias.cursomc.repositories.EnderecoRepository;
import com.christiandias.cursomc.repositories.EstadoRepository;
import com.christiandias.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private EstadoRepository estadoRepo;

	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().add(cat1);
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);		
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est2.getCidades().addAll(Arrays.asList(c2,c3));
		est1.getCidades().add(c1);
		
		estadoRepo.saveAll(Arrays.asList(est1,est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
	
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apt 203","Jardim","38220834",cli1, c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		// Peimeiro o cliente pois este é independente do endereço
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
	
	}

}
