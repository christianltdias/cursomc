package com.christiandias.cursomc.services.validation;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.christiandias.cursomc.domain.Cliente;
import com.christiandias.cursomc.domain.enums.TipoCliente;
import com.christiandias.cursomc.dto.ClienteNewDTO;
import com.christiandias.cursomc.repositories.ClienteRepository;
import com.christiandias.cursomc.resources.exceptions.FieldMessage;
import com.christiandias.cursomc.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    
    @Autowired
    private ClienteRepository repo;
    
    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        
        List<FieldMessage> list = new ArrayList<>();
        
        if(objDto.getTipo() == TipoCliente.PESSOAFISICA.getValue() && !BR.isValidSsn(objDto.getCpfOuCnpj())){
           list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        } 
        if (objDto.getTipo() == TipoCliente.PESSOAJURIDICA.getValue() && !BR.isValidTfn(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());

        if(aux != null){
            list.add(new FieldMessage("email","Email já está cadastrado"));
        }

        // inclua os testes aqui, inserindo erros na lista

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}