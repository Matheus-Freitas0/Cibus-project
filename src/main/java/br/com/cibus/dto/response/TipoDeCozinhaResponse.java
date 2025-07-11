package br.com.cibus.dto.response;

import br.com.cibus.domain.TipoDeCozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TipoDeCozinhaResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    public TipoDeCozinhaResponse(TipoDeCozinha tipoDeCozinha) {
        this(tipoDeCozinha.getId(), tipoDeCozinha.getNome());
    }

    public TipoDeCozinhaResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
