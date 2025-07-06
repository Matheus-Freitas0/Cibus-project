package br.com.cibus;

import br.com.cibus.domain.TipoDeCozinha;
import br.com.cibus.controller.TipoDeCozinhaController;
import br.com.cibus.dto.response.RestaurantePorTipoCozinhaResponse;
import br.com.cibus.repository.TipoDeCozinhaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TipoDeCozinhaController.class)
public class TipoDeCozinhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    private final ObjectMapper jsonParser = new ObjectMapper();

    @Test
    void deveCriarNovoTipoDeCozinha() throws Exception {

        String novoTipoDeCozinhaJson = """
            { "nome": "Fusion" }
        """;

        mockMvc.perform(post("/tipos-de-cozinha").contentType(MediaType.APPLICATION_JSON).content(novoTipoDeCozinhaJson))
                .andExpect(status().isCreated())
                .andReturn();

        verify(tipoDeCozinhaRepository).save(any(TipoDeCozinha.class));

    }

    @Test
    void deveListarTiposDeCozinha() throws Exception {
        when(tipoDeCozinhaRepository.findAll()).thenReturn(List.of(new TipoDeCozinha(1L, "Árabe"),new TipoDeCozinha(2L, "Peruana") ));

        MvcResult mvcResult = mockMvc.perform(get("/tipos-de-cozinha"))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        List<Map<String, String>> responseData = jsonParser.readValue(responseJson, List.class);

        assertThat(responseData).hasSize(2);

        verify(tipoDeCozinhaRepository).findAll();
        verifyNoMoreInteractions(tipoDeCozinhaRepository);
    }

    @Test
    @DisplayName("Deve retornar um relatório com a quantidade de restaurantes por tipo de cozinha")
    void deveObterRestaurantesPorTipoCozinha() throws Exception {
        when(tipoDeCozinhaRepository.relatorioQuantidadePorTipoDeCozinha())
                .thenReturn(List.of(
                        new RestaurantePorTipoCozinhaResponse("Italiana", 5L),
                        new RestaurantePorTipoCozinhaResponse("Japonesa", 3L)
                ));

        MvcResult mvcResult = mockMvc.perform(get("/relatorios/restaurantes-por-tipo-cozinha"))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        List<RestaurantePorTipoCozinhaResponse> responseData = jsonParser.readValue(
                responseJson,
                new TypeReference<List<RestaurantePorTipoCozinhaResponse>>() {}
        );

        assertThat(responseData).hasSize(2);
        assertThat(responseData.getFirst().getNome()).isEqualTo("Italiana");
        assertThat(responseData.getFirst().getQuantidade()).isEqualTo(5L);
    }

}
