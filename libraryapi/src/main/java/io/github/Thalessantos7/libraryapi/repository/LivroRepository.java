package io.github.Thalessantos7.libraryapi.repository;

import io.github.Thalessantos7.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {}