package io.github.Thalessantos7.libraryapi.repository;

import io.github.Thalessantos7.libraryapi.model.Autor;
import io.github.Thalessantos7.libraryapi.model.GeneroLivro;
import io.github.Thalessantos7.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */

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
    List<Livro> findByTituloOrIsbnOrderByTitulo(String titulo, String isbn);

    // SELECT * FROM livro WHERE data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL -> referencia as entidades e as propriedades
    // SELECT l.* FROM livro AS l ORDER BY l.titulo, l.preco
    @Query("SELECT l FROM Livro AS l ORDER BY l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    /**
     * SELECT a.*
     * FROM livro l
     * JOIN autor a ON a.id = l.id_autor
     */
    @Query("SELECT a FROM Livro l JOIN l.autor a")
    List<Autor> listarAutoresDosLivros();

    // SELECT DISTINCT l.* FROM livro l
    @Query("SELECT DISTINCT l.titulo FROM Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
        SELECT l.genero
        FROM Livro l
        JOIN l.autor a
        WHERE a.nacionalidade = 'Brasileira'
        ORDER BY l.genero
    """)
    List<String> listarGenerosAutoresBrasileiros();

    // Named Parameters -> parâmetros nomeados
    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY :paramOrdenacao")
    List<Livro> findByGenero(
            @Param("genero") GeneroLivro generoLivro,
            @Param("paramOrdenacao") String nomePropriedade
    );

    // Positional Parameters
    @Query("SELECT l FROM Livro l WHERE l.genero = ?2 ORDER BY ?1")
    List<Livro> findByGeneroPositionalParameters(String nomePropriedade, GeneroLivro generoLivro);
}