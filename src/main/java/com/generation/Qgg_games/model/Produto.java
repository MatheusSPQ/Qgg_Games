package com.generation.Qgg_games.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotBlank
    @Size(min = 1, max = 1)
    private String clasificacaoEtaria;

    @NotNull
    private long valor;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 2, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 2, max = 100) String nome) {
        this.nome = nome;
    }

    public @NotBlank @Size(max = 500) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @Size(max = 500) String descricao) {
        this.descricao = descricao;
    }

    public @NotBlank @Size(min = 1, max = 1) String getClasificacaoEtaria() {
        return clasificacaoEtaria;
    }

    public void setClasificacaoEtaria(@NotBlank @Size(min = 1, max = 1) String clasificacaoEtaria) {
        this.clasificacaoEtaria = clasificacaoEtaria;
    }

    @NotNull
    public long getValor() {
        return valor;
    }

    public void setValor(@NotNull long valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
