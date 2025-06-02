package com.aluracursos.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
}
