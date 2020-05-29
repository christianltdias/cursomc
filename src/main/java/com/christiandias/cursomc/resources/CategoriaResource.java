package com.christiandias.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.christiandias.cursomc.domain.Categoria;
import com.christiandias.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias") // endpoint
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	// Informa que o endpoint desse método é id
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// PathVariable informa que esse id vai para a url
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
}
