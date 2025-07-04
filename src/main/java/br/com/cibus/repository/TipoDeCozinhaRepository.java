package br.com.cibus.repository;

import br.com.cibus.domain.TipoDeCozinha;
import org.springframework.data.jpa.repository.*;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> { }