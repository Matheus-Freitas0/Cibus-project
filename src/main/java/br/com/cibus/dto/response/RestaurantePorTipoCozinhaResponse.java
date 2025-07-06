package br.com.cibus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantePorTipoCozinhaResponse {
    private String nome;
    private long quantidade;
}
