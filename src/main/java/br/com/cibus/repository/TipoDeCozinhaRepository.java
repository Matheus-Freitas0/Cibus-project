package br.com.cibus.repository;

import br.com.cibus.domain.TipoDeCozinha;
import br.com.cibus.dto.response.RestaurantePorTipoCozinhaResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {

    @Query("""
        SELECT new br.com.cibus.dto.response.RestaurantePorTipoCozinhaResponse(tc.nome, COUNT(r))
        FROM TipoDeCozinha tc
        LEFT JOIN Restaurante r ON r.tipoDeCozinha = tc
        GROUP BY tc.nome
        ORDER BY COUNT(r) DESC
    """)
    List<RestaurantePorTipoCozinhaResponse> relatorioQuantidadePorTipoDeCozinha();

}