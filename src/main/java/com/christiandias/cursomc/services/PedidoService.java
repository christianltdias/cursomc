package com.christiandias.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christiandias.cursomc.domain.Pedido;
import com.christiandias.cursomc.repositories.PedidoRepository;
import com.christiandias.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// Autowired informa que é automaticamente instanciada po
	// IC ou injeção de dependencia
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
