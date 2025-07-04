package br.com.cibus.dto.response;

import br.com.cibus.domain.Restaurante;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RestauranteListResponse {
    private List<RestauranteResponse> restaurantes;
    public RestauranteListResponse(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes.stream()
               .map(RestauranteResponse::new)
               .collect(Collectors.toList());
    }
}
