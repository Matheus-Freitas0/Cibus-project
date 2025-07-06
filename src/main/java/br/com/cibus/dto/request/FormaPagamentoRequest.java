package br.com.cibus.dto.request;

import br.com.cibus.domain.FormaPagamento;
import br.com.cibus.domain.TipoDeCozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FormaPagamentoRequest {

    @JsonProperty
    @Size(min = 1, max = 50)
    private String descricao;

    public FormaPagamento toEntity() {
        return new FormaPagamento(descricao);
    }
}
