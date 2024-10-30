package com.projeto.service;

import com.projeto.domain.dto.BuscaCepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BuscaCepService {

    private final RestTemplate restTemplate;

    @Autowired
    public BuscaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<BuscaCepDto> buscaCep(String cep) {
        String url = "http://localhost:8080/cep/" + cep;
        BuscaCepDto response = restTemplate.getForObject(url, BuscaCepDto.class);

        return ResponseEntity.ok(response);
    }

}
