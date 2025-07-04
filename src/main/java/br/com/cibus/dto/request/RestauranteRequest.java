package br.com.cibus.dto.request;

import br.com.cibus.domain.Restaurante;
import br.com.cibus.domain.TipoDeCozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestauranteRequest {

    private String nome;
    private String endereco;
    private String cep;
    private String cnpj;
    private String descricao;
    private TipoDeCozinha tipoDeCozinha;

    public Restaurante toEntity() {
        return Restaurante.builder()
                .nome(nome)
                .endereco(endereco)
                .cep(cep)
                .cnpj(cnpj)
                .descricao(descricao)
                .tipoDeCozinha(tipoDeCozinha)
                .build();
    }
}
