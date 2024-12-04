package com.generation.Qgg_games.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name ="tb_categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nome;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoria",cascade = CascadeType.REMOVE)
    private List<Produto> produtoList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 2, max = 50) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 2, max = 50) String nome) {
        this.nome = nome;
    }

    public @NotBlank @Size(max = 500) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @Size(max = 500) String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }
}
