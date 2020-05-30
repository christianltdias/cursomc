package com.christiandias.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christiandias.cursomc.domain.Cliente;
import com.christiandias.cursomc.repositories.ClienteRepository;
import com.christiandias.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// Autowired informa que é automaticamente instanciada po
	// IC ou injeção de dependencia
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
