package io.github.Thalessantos7.libraryapi.repository;

import io.github.Thalessantos7.libraryapi.model.Autor;
import io.github.Thalessantos7.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    // Query Method
    // SELECT * FROM livro WHERE id_autor = ?
    List<Livro> findByAutor(Autor autor);

    // SELECT * FROM livro WHERE titulo = ?
    List<Livro> findByTitulo(String titulo);

    // SELECT * FROM livro WHERE isbn = ?
    List<Livro> findByIsbn(String isbn);

    // SELECT * FROM livro WHERE titulo = ? AND preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // SELECT * FROM livro WHERE titulo = ? OR isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);
}