package br.com.cibus.controller;

import br.com.cibus.dto.request.NovoTipoDeCozinhaRequest;
import br.com.cibus.dto.response.TipoDeCozinhaResponse;
import br.com.cibus.repository.TipoDeCozinhaRepository;
import br.com.cibus.domain.TipoDeCozinha;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TipoDeCozinhaController {

    private final TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public TipoDeCozinhaController(TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @GetMapping("/tipos-de-cozinha")
    public List<TipoDeCozinhaResponse> list() {
        return tipoDeCozinhaRepository.findAll().stream().map(TipoDeCozinhaResponse::new).toList();
    }

    @PostMapping("/tipos-de-cozinha")
    public ResponseEntity<TipoDeCozinhaResponse> create(@RequestBody @Valid NovoTipoDeCozinhaRequest novoTipoDeCozinhaRequest) {
        TipoDeCozinha novoTipoDeCozinha = novoTipoDeCozinhaRequest.toEntity();
        tipoDeCozinhaRepository.save(novoTipoDeCozinha);
        return new ResponseEntity<>(new TipoDeCozinhaResponse(novoTipoDeCozinha), HttpStatus.CREATED);
    }

    @GetMapping("/restaurante-tipos-de-cozinha")
    public List<TipoDeCozinha> getRestauranteTipoDeCozinha() {
        return tipoDeCozinhaRepository.findAll();

    }
}
