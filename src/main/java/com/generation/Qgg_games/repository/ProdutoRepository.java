package com.generation.Qgg_games.repository;

import com.generation.Qgg_games.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public Produto findByNomeIgnoreCase(String nome);
    public List<Produto> findAllByCategoria_nomeIgnoreCase(String nome);
    public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
}
