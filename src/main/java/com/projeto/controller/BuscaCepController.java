package com.projeto.controller;

import com.projeto.domain.dto.BuscaCepDto;
import com.projeto.service.BuscaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buscacep")
public class BuscaCepController {

    @Autowired
    private BuscaCepService buscaCepService;

    @GetMapping(path = "/{cep}")
    public ResponseEntity<BuscaCepDto> buscaCep(@PathVariable String cep) {
        return buscaCepService.buscaCep(cep);
    }

}
