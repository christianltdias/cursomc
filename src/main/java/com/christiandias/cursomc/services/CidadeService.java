package com.christiandias.cursomc.services;

import java.util.List;

import com.christiandias.cursomc.domain.Cidade;
import com.christiandias.cursomc.repositories.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

	// Autowired informa que é automaticamente instanciada po
	// IC ou injeção de dependencia
	@Autowired
	private CidadeRepository repo;
	
	public List <Cidade> findByEstado(Integer estado_id) {
		return repo.findCidades(estado_id);
	}
	

}
