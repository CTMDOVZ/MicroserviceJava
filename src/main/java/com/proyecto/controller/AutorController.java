package com.proyecto.controller;

import com.proyecto.model.Autor;
import com.proyecto.repository.AutorRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }

    @PostMapping
    public Autor createAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
