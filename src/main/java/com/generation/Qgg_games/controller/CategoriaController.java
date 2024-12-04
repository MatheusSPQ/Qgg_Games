package com.generation.Qgg_games.controller;


import com.generation.Qgg_games.model.Categoria;
import com.generation.Qgg_games.repository.CategoriaRepository;
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
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria com id " + id + " não encontrado");
        }
        return ResponseEntity.ok(categoria.get());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<?>> getAllByNome(@PathVariable String nome) {
        List<Categoria> categoriaList = categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
        if(categoriaList.isEmpty()){
            return ResponseEntity.status(404)
                    .body(Collections.singletonList("Não foi encontrado nenhuma categoria com nome " + nome));
        }

        return ResponseEntity.ok(categoriaList);
    }

    @PostMapping
    public ResponseEntity<List<Categoria>> create(@Valid @RequestBody List<Categoria> categoria) {
        categoriaRepository.saveAll(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Categoria categoria) {
        if(categoriaRepository.existsById(categoria.getId())){
            categoriaRepository.save(categoria);
            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Categoria com o id " + categoria.getId() + "não encontrada");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        if(categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Categora com id " + id + " deletada");
        }

        return ResponseEntity.status(404)
                .body("Categoira com o id " + id + " não foi encontrada");
    }
}
