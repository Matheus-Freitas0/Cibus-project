package br.com.cibus.controller;

import br.com.cibus.domain.Restaurante;
import br.com.cibus.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Restaurante> listar() {
        return restauranteRepository.findAll();
    }
}
