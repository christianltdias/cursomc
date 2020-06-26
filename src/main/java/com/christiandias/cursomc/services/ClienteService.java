package com.christiandias.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.christiandias.cursomc.domain.Cidade;
import com.christiandias.cursomc.domain.Cliente;
import com.christiandias.cursomc.domain.Endereco;
import com.christiandias.cursomc.domain.enums.TipoCliente;
import com.christiandias.cursomc.dto.ClienteDTO;
import com.christiandias.cursomc.dto.ClienteNewDTO;
import com.christiandias.cursomc.repositories.CidadeRepository;
import com.christiandias.cursomc.repositories.ClienteRepository;
import com.christiandias.cursomc.repositories.EnderecoRepository;
import com.christiandias.cursomc.services.exceptions.DataIntegrityException;
import com.christiandias.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	// Autowired informa que é automaticamente instanciada po
	// IC ou injeção de dependencia
	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private CidadeRepository cidRepo;

	@Autowired
	private BCryptPasswordEncoder pe;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente newObj) {
		Cliente obj = find(newObj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cliente com entidades entrelaçadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null,null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()),pe.encode(objDto.getSenha()));
		//Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Cidade cid = cidRepo.findById(objDto.getCidadeId()).get();
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		obj.setNome(newObj.getNome());
		obj.setEmail(newObj.getEmail());
	}
}
