package com.aluracursos.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.Languages;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findByTitleContainingIgnoreCase(String keyword);
    public List<Book> findByLanguages(Languages languages);
}
