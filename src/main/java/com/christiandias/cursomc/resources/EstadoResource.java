package com.christiandias.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.christiandias.cursomc.domain.Cidade;
import com.christiandias.cursomc.domain.Estado;
import com.christiandias.cursomc.dto.CidadeDTO;
import com.christiandias.cursomc.dto.EstadoDTO;
import com.christiandias.cursomc.services.CidadeService;
import com.christiandias.cursomc.services.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estados") // endpoint
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;


	// Informa que o endpoint desse método é id
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		// PathVariable informa que esse id vai para a url

		List<Estado> est = service.findAll();
		List<EstadoDTO> estDTO = est.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estDTO);
	}

	// Informa que o endpoint desse método é id
	@RequestMapping(value = "/{estado_id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<?> findCidades(@PathVariable Integer estado_id) {
		// PathVariable informa que esse id vai para a url

		List<Cidade> est = cidadeService.findByEstado(estado_id);
		List<CidadeDTO> estDTO = est.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estDTO);
	}
}
