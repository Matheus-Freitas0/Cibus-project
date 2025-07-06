package br.com.cibus.controller;

import br.com.cibus.domain.Restaurante;
import br.com.cibus.dto.request.RestauranteRequest;
import br.com.cibus.dto.response.RestauranteListResponse;
import br.com.cibus.dto.response.RestauranteResponse;
import br.com.cibus.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteListResponse listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return new RestauranteListResponse(restaurantes);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));;
        return new RestauranteResponse(restaurante);
    }

    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        restauranteRepository.deleteById(id);
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse salvar(@RequestBody RestauranteRequest restaurante) {
        Restaurante restauranteSalvo = restauranteRepository.save(restaurante.toEntity());
        return new RestauranteResponse(restauranteSalvo);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteResponse atualizar(@PathVariable Long id, @RequestBody RestauranteRequest restaurante) {
        Restaurante restauranteAtualizado = restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        restauranteAtualizado.setNome(restaurante.getNome());
        restauranteAtualizado.setEndereco(restaurante.getEndereco());
        Restaurante restauranteSalvo = restauranteRepository.save(restauranteAtualizado);
        return new RestauranteResponse(restauranteSalvo);
    }
}
