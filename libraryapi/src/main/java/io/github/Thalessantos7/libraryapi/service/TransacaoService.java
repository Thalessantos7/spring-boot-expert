package io.github.Thalessantos7.libraryapi.service;

import io.github.Thalessantos7.libraryapi.model.Autor;
import io.github.Thalessantos7.libraryapi.model.GeneroLivro;
import io.github.Thalessantos7.libraryapi.model.Livro;
import io.github.Thalessantos7.libraryapi.repository.AutorRepository;
import io.github.Thalessantos7.libraryapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar() {
        // Salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        // Salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Teste Francisco")) throw new RuntimeException("Rollback!");
    }
}