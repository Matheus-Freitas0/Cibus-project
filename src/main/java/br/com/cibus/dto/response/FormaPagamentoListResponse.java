package br.com.cibus.dto.response;

import br.com.cibus.domain.FormaPagamento;
import lombok.*;

import java.util.List;

@Data
public class FormaPagamentoListResponse {
    private List<FormaPagamentoResponse> formasPagamentos;

    public FormaPagamentoListResponse (List<FormaPagamento> formasPagamentos) {
        this.formasPagamentos = formasPagamentos.stream()
               .map(FormaPagamentoResponse::new)
               .toList();
    }
}
