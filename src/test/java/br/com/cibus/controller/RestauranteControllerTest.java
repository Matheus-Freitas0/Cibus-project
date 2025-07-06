package br.com.cibus.controller;

import br.com.cibus.domain.Restaurante;
import br.com.cibus.domain.TipoDeCozinha;
import br.com.cibus.dto.response.RestauranteListResponse;
import br.com.cibus.dto.response.RestauranteResponse;
import br.com.cibus.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestauranteRepository restauranteRepository;

    private final ObjectMapper jsonParser = new ObjectMapper();

    @Test
    @DisplayName("Listar restaurantes - Sucesso")
    void listar() throws Exception {
        when(restauranteRepository.findAll())
                .thenReturn(List.of(new Restaurante(
                        1L,
                        "Teste",
                        "30000-000",
                        "Rua Teste",
                        "1234567890",
                        "Teste",
                        new TipoDeCozinha(1L, "Japonesa")
                )));

        MvcResult mvcResult = mockMvc.perform(get("/restaurante/listar"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        RestauranteListResponse restauranteListResponse = jsonParser.readValue(response, RestauranteListResponse.class);

        assertEquals(1, restauranteListResponse.getRestaurantes().size());
        assertEquals("Teste", restauranteListResponse.getRestaurantes().getFirst().getNome());
        assertEquals("Rua Teste", restauranteListResponse.getRestaurantes().getFirst().getEndereco());
        assertEquals("30000-000", restauranteListResponse.getRestaurantes().getFirst().getCep());
        assertEquals("Teste", restauranteListResponse.getRestaurantes().getFirst().getDescricao());
        assertEquals("1234567890", restauranteListResponse.getRestaurantes().getFirst().getCnpj());
        assertEquals("Japonesa", restauranteListResponse.getRestaurantes().getFirst().getTipoDeCozinha().getNome());
    }

    @Test
    @DisplayName("Buscar por ID - Sucesso")
    void buscarPorId() throws Exception {
        when(restauranteRepository.findById(1L))
                .thenReturn(Optional.of(new Restaurante(
                        1L,
                        "Teste",
                        "30000-000",
                        "Rua Teste",
                        "1234567890",
                        "Teste",
                        new TipoDeCozinha(1L, "Japonesa")
                )));

        MvcResult mvcResult = mockMvc.perform(get("/restaurante/buscar/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        RestauranteResponse restauranteResponse = jsonParser.readValue(response, RestauranteResponse.class);

        assertEquals("Teste", restauranteResponse.getNome());
        assertEquals("Rua Teste", restauranteResponse.getEndereco());
        assertEquals("30000-000", restauranteResponse.getCep());
        assertEquals("Teste", restauranteResponse.getDescricao());
        assertEquals("1234567890", restauranteResponse.getCnpj());
        assertEquals("Japonesa", restauranteResponse.getTipoDeCozinha().getNome());
    }

    @Test
    @DisplayName("Excluir por ID - Sucesso")
    void excluir() throws Exception {
        mockMvc.perform(delete("/restaurante/excluir/{id}", 1))
                .andExpect(status().isNoContent())
                .andReturn();

        verify(restauranteRepository).deleteById(1L);
        verifyNoMoreInteractions(restauranteRepository);
    }

    @Test
    @DisplayName("Salvar - Sucesso")
    void salvar() throws Exception {
        Restaurante restauranteSalvo = new Restaurante(
                1L,
                "Teste",
                "30000-000",
                "Rua Teste",
                "1234567890",
                "Teste",
                new TipoDeCozinha(1L, "Japonesa")
        );

        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restauranteSalvo);

        String novoRestauranteJson = """
                {
                  "nome": "Teste",
                  "cep": "30000-000",
                  "endereco": "Rua Teste",
                  "cnpj": "1234567890",
                  "descricao": "Teste",
                  "tipoDeCozinha": {
                    "id": 1,
                    "nome": "Japonesa"
                  }
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/restaurante/salvar").contentType(MediaType.APPLICATION_JSON).content(novoRestauranteJson))
                .andExpect(status().isCreated())
                .andReturn();

        verify(restauranteRepository).save(any(Restaurante.class));
        verifyNoMoreInteractions(restauranteRepository);
    }
    @Test
    @DisplayName("Atualizar - Sucesso")
    void atualizar() throws Exception {
        when(restauranteRepository.findById(1L))
                .thenReturn(Optional.of(new Restaurante(
                        1L,
                        "Teste",
                        "30000-000",
                        "Rua Teste",
                        "1234567890",
                        "Teste",
                        new TipoDeCozinha(1L, "Japonesa")
                )));
        Restaurante restauranteSalvo = new Restaurante(
                1L,
                "Restaurante Sabor do Campo",
                "12345678",
                "Rua das Flores, 123 - Bairro Centro - Cidade Exemplo",
                "12345678000199",
                "Restaurante especializado em comida caseira brasileira.",
                new TipoDeCozinha(1L, "Japonesa")
        );
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restauranteSalvo);

        String novoRestauranteJson = """
        {
            "nome": "Restaurante Sabor do Campo",
            "cep": "12345678",
            "endereco": "Rua das Flores, 123 - Bairro Centro - Cidade Exemplo",
            "cnpj": "12345678000199",
            "descricao": "Restaurante especializado em comida caseira brasileira.",
            "tipoDeCozinha": {
              "id": 1
            }
        }
        """;
        mockMvc.perform(MockMvcRequestBuilders.put("/restaurante/atualizar/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(novoRestauranteJson))
                .andExpect(status().isOk())
                .andReturn();

        verify(restauranteRepository).findById(1L);
        verify(restauranteRepository).save(any(Restaurante.class));
        verifyNoMoreInteractions(restauranteRepository);
    }
}