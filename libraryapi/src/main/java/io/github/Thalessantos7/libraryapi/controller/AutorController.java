package io.github.Thalessantos7.libraryapi.controller;

import io.github.Thalessantos7.libraryapi.controller.dto.AutorDTO;
import io.github.Thalessantos7.libraryapi.model.Autor;
import io.github.Thalessantos7.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("autores")
// http://localhost:8080/autores
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
        var autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        // http://localhost:8080/autores/3879c3c1-0f53-45df-af5b-0532ac985f71
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> ObterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);

        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();

            AutorDTO dto = new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade()
            );

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }
}