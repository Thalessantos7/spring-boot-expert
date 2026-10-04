package io.github.Thalessantos7.libraryapi.repository;

import io.github.Thalessantos7.libraryapi.model.Autor;
import io.github.Thalessantos7.libraryapi.model.GeneroLivro;
import io.github.Thalessantos7.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();

        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("3879c3c1-0f53-45df-af5b-0532ac985f71"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }
}