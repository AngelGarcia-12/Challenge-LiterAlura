package com.aluracursos.literalura.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.BookResponse;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.ConvertData;
import com.aluracursos.literalura.service.LiterAluraService;

public class ServiceController {
    private BookRepository repositoryBook;
    private AuthorRepository repositoryAuthor;

    public ServiceController() {}
    
    public ServiceController(BookRepository repositoryBook, AuthorRepository repositoryAuthor) {
        this.repositoryBook = repositoryBook;
        this.repositoryAuthor = repositoryAuthor;
    }

    public void findBookTitle(String keyword) {
        final String BASEURLSEARCH = "https://gutendex.com/books/?search=";
        final LiterAluraService API = new LiterAluraService();
        String json = API.createConnectionAPI(BASEURLSEARCH + keyword.replace(" ", "%20"));
        ConvertData convertData = new ConvertData();
        var data = convertData.getData(json, BookResponse.class);
        
        // Check if exits at least one book
        if(data.count() > 0) {
            System.out.println("Se encontraron " + data.count() + " libros");
            List<Book> listBooks = data.results()
                                    .stream()
                                    .map(b -> new Book(b))
                                    .collect(Collectors.toList());
            
            System.out.println("Libros: " + listBooks);
            Book books = new Book(data.results().get(0));
            repositoryBook.save(books);
            System.out.println("El libro se registro en la base de datos");
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
}
