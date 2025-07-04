package br.com.cibus.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TipoDeCozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Deprecated
    protected TipoDeCozinha() {
    }

    public TipoDeCozinha(String nome) {
        this.nome = nome;
    }

    public TipoDeCozinha(Long id, String nome) {
        this(nome);
        this.id = id;
    }

    @Override
    public String toString() {
        return "TipoDeCozinha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
