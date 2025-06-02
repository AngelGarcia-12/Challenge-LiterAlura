package com.aluracursos.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
