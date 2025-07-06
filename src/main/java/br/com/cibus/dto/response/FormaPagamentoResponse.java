package br.com.cibus.dto.response;

import br.com.cibus.domain.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormaPagamentoResponse {
    private Long id;
    private String descricao;

    public FormaPagamentoResponse(FormaPagamento formaPagamento) {
        this(formaPagamento.getId(),
            formaPagamento.getDescricao()
        );
    }
}
