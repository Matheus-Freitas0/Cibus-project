package br.com.cibus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantePorTipoCozinhaResponse {
    private String nome;
    private long quantidade;
}
