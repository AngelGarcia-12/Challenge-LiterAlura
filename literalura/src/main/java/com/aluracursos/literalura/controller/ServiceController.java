package com.aluracursos.literalura.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.BookResponse;
import com.aluracursos.literalura.model.Languages;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.ConvertData;
import com.aluracursos.literalura.service.LiterAluraService;

@Service
public class ServiceController {
    @Autowired
    private BookRepository repositoryBook;
    @Autowired
    private AuthorRepository repositoryAuthor;
    private List<Book> listBooks = new ArrayList<>();

    public ServiceController() {}

    public void findBookTitle(String keyword) {
        final String BASEURLSEARCH = "https://gutendex.com/books/?search=";
        final LiterAluraService API = new LiterAluraService();
        String json = API.createConnectionAPI(BASEURLSEARCH + keyword.replace(" ", "%20"));
        ConvertData convertData = new ConvertData();
        var data = convertData.getData(json, BookResponse.class);
        
        // Check if exits at least one book
        if(data.count() > 0) {
            System.out.println("Se encontraron " + data.count() + " libros");
            listBooks = repositoryBook.findByTitleContainingIgnoreCase(keyword);
                            
            Optional<Book> books = listBooks.stream()
                            .filter(b -> b.getTitle().contains(keyword))
                            .findFirst();

            if(books.isPresent()) {
                var bookFind = books.get();
                System.out.println("Libros: " + bookFind);
                repositoryBook.save(bookFind);
                System.out.println("El libro se registro en la base de datos");
            }
            else {
                System.out.println("No se registro el libro");
            }
        }
        else {
            System.out.println("Libro no encontrado por el momento");
        }
    }

    public void getRegisteredBooks() {
        List<Book> books = repositoryBook.findAll();
        
        books.stream().sorted(Comparator.comparing(Book::getIdBook)).forEach(System.out::println);
    }

    public void getRegisteredAuthors() {
        List<Author> authors = repositoryAuthor.findAll();

        authors.stream().sorted(Comparator.comparing(Author::getName)).forEach(System.out::println);
    }

    public void getAuthorAliveOrDeath(int year) {
        List<Author> listaAuthors = repositoryAuthor.findAll();

        Optional<Author> authors = listaAuthors.stream()
                                        .filter(auths -> year >= auths.getBirthYear() && year <= auths.getDeathYear())
                                        .findFirst();
        if(authors.isPresent()) {
            var authorsFind = authors.get();
            System.out.println("Autores vivos: " + authorsFind);
        }
        else {
            System.out.println("No se encontro ningun autor vivo en esa determinada fecha");
        }
    }

    public void findByLanguages(Languages language) {
        List<Book> listBooks = repositoryBook.findAll();

        List<Book> filteredBooks = listBooks.stream()
                                    .filter(b -> b.getLanguages().equals(language))
                                    .collect(Collectors.toList());
        
        if(!filteredBooks.isEmpty()) {
            System.out.println("Libros por idioma " + language.getLanguagesBooksSpanish() + " encontrados en la base de datos: " + filteredBooks.size());
            System.out.println(filteredBooks.toString().replaceAll("[\\[\\]]", ""));
        }
        else {
            System.out.println("No se encontro ningun libro de idioma " + language.getLanguagesBooksSpanish());
        }
    }
}
