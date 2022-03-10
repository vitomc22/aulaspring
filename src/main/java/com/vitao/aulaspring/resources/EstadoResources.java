package com.vitao.aulaspring.resources;

import com.vitao.aulaspring.domain.Cidade;
import com.vitao.aulaspring.domain.Estado;
import com.vitao.aulaspring.dto.CidadeDTO;
import com.vitao.aulaspring.dto.EstadoDTO;
import com.vitao.aulaspring.services.CidadeService;
import com.vitao.aulaspring.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResources {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = service.findAll();
        List<EstadoDTO> listDTO = list.stream().map(EstadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{estadoId}/cidades")
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDTO = list.stream().map(CidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }


}
