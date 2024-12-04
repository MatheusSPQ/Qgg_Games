package com.generation.Qgg_games.controller;

import com.generation.Qgg_games.model.Produto;
import com.generation.Qgg_games.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto com id " + id + " não encontrado");
        }
        return ResponseEntity.ok(produto.get());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Produto>> getAllByNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }


    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<?>> getAllByCategoria(@PathVariable String nome) {
        List<Produto> produtoList = produtoRepository.findAllByCategoria_nomeIgnoreCase(nome);
        if (produtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList("Nenhum produto produto encontrado para a categoria:" + nome));
        }
        return ResponseEntity.ok(produtoList);
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<Object> getByNome(@PathVariable String nome) {
        Produto produto = produtoRepository.findByNomeIgnoreCase(nome);
        if(produto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto com o nome " + nome + "não encontrado");
        }
        return ResponseEntity.ok(produto);

    }

    @PostMapping
    public ResponseEntity<List<Produto>> create(@Valid @RequestBody List<Produto> produto) {
        produtoRepository.saveAll(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Produto produto) {
        if(produtoRepository.existsById(produto.getId())){
            produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Produto com o id " + produto.getId() + "não encontrado");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Produto com id " + id + " deletado");
        }

        return ResponseEntity.status(404)
                .body("Produto com o id " + id + " não foi encontrado");
    }





}
