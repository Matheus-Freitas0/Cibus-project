package br.com.cibus.controller;

import br.com.cibus.domain.FormaPagamento;
import br.com.cibus.dto.request.FormaPagamentoRequest;
import br.com.cibus.dto.response.FormaPagamentoListResponse;
import br.com.cibus.dto.response.FormaPagamentoResponse;
import br.com.cibus.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping("/all")
    public FormaPagamentoListResponse getAllFormasPagamento() {
        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
        return new FormaPagamentoListResponse(formaPagamentos);
    }

    @GetMapping("/{id}")
    public FormaPagamentoResponse getFormaPagamentoById(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada"));
        return new FormaPagamentoResponse(formaPagamento);
    }

    @PostMapping("/salvar")
    public FormaPagamentoResponse salvarFormaPagamento(@RequestBody FormaPagamentoRequest formaPagamento) {
        FormaPagamento formaCadastrada = formaPagamentoRepository.save(formaPagamento.toEntity());
        return new FormaPagamentoResponse(formaCadastrada);
    }

    @PutMapping("/atualizar/{id}")
    public FormaPagamentoResponse atualizarFormaPagamento(@PathVariable Long id, @RequestBody FormaPagamentoRequest formaPagamentoAtualizado) {
        FormaPagamento formaPagamentoById = formaPagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada"));
        formaPagamentoById.setDescricao(formaPagamentoAtualizado.getDescricao());
        FormaPagamento formaAtualizada = formaPagamentoRepository.save(formaPagamentoById);
        return new FormaPagamentoResponse(formaAtualizada);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirFormaPagamento(@PathVariable Long id) {
        formaPagamentoRepository.deleteById(id);
    }
}
