package br.com.cibus.dto.response;

import br.com.cibus.domain.Restaurante;
import br.com.cibus.domain.TipoDeCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestauranteResponse {

    private Long id;
    private String nome;
    private String cep;
    private String endereco;
    private String cnpj;
    private String descricao;
    private TipoDeCozinha tipoDeCozinha;

    public RestauranteResponse(Restaurante restaurante) {
        this(restaurante.getId(),
             restaurante.getNome(),
             restaurante.getCep(),
             restaurante.getEndereco(),
             restaurante.getCnpj(),
             restaurante.getDescricao(),
             restaurante.getTipoDeCozinha()
        );
    }
}
