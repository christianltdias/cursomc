package com.christiandias.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.christiandias.cursomc.domain.Categoria;
import com.christiandias.cursomc.domain.Produto;
import com.christiandias.cursomc.repositories.CategoriaRepository;
import com.christiandias.cursomc.repositories.ProdutoRepository;
import com.christiandias.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	// Autowired informa que é automaticamente instanciada po
	// IC ou injeção de dependencia
	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	

	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}

}
