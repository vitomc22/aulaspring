package com.vitao.aulaspring.services.validation;

import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.domain.enums.TipoCliente;
import com.vitao.aulaspring.dto.ClienteDTO;
import com.vitao.aulaspring.dto.ClienteNewDTO;
import com.vitao.aulaspring.repositories.ClienteRepository;
import com.vitao.aulaspring.resources.resources.exception.FieldMessage;
import com.vitao.aulaspring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request; // injeção pra pegar dados do request URI -> ID

    private @Autowired
    ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann) {
    }
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id")); //parse de string pra int
        //estrutura de dados map, (chave valor) pra pegar o id vindo do request

        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null && aux.getId().equals(uriId)){ //comparando id achado (banco) do id enviado(request)
            list.add(new FieldMessage("email","Email já existente" ));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
