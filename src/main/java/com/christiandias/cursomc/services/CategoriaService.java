package com.christiandias.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christiandias.cursomc.domain.Categoria;
import com.christiandias.cursomc.repositories.CategoriaRepository;
import com.christiandias.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// Autowired informa que é automaticamente instanciada po
	// IC ou injeção de dependencia
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
}