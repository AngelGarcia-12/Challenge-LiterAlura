package com.aluracursos.literalura.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idBook;
    @Column(unique = true)
    private String title;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors;
    @Enumerated(EnumType.STRING)
    private Languages languages;
    private long downloads;

    public Book() {}

    public Book(BookData bookData) {
        this.idBook = bookData.idBook();
        this.title = bookData.title();
        this.authors = bookData.authors()
                        .stream()
                        .map(auth -> new Author(
                            auth.name(), 
                            auth.birthYear() != null ? auth.birthYear() : 0,
                            auth.deathYear() != null ? auth.deathYear() : 0 
                        ))
                        .collect(Collectors.toList());
        this.languages = bookData.languages()
                         .stream()
                         .map(lang -> {
                            try{
                                return Languages.fromString(lang);
                            }
                            catch (IllegalArgumentException e){
                                return null;
                            }
                         })
                         .filter(Objects::nonNull)
                         .collect(Collectors.toList()).get(0);
        this.downloads = bookData.dowloads();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return  "\n╔════════════════════════════════════════╗\n"+
                "║ id: " + idBook + ",\n" +
                "║ titulo: " + title + ",\n"+ 
                "║ autores: " + authors.toString().replaceAll("[\\[\\]]", "") +
                "║ idiomas: "+ languages + ",\n"+
                "║ descargas: "+ downloads + "\n"+
                "╚══════════════════════════════════════════╝\n";
    }
}
