package com.aluracursos.literalura.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.aluracursos.literalura.controller.ServiceController;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;

public class MenuApp {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void menuMain(BookRepository repositoryBook, AuthorRepository repositoryAuthor) throws IOException, InterruptedException {
        final ServiceController CONTROLLER = new ServiceController(repositoryBook, repositoryAuthor);
        final String MENUVIEW = 
                """
                *******************************************************
                |                        📘                           |
                *******************************************************
                \\---------------------------------------------------\\
                Selecciona una de las siguientes opciones:
                1. Buscar libro por su titulo.
                2. Listar los libros registrados.
                3. Listar autores registrados.
                4. Listar autores vivos en un determinado año
                5. Listar libros por idiomas.
                0. Salir.
                \\---------------------------------------------------\\
                """;

        byte optionMenu = -1;
        String option = null;
        String searchingBook = null;

        do {
            // TODO: Hacer menu
            System.out.println(MENUVIEW);
            System.out.print("Ingrese su opcion: ");
            option = br.readLine().trim().toLowerCase();
            System.out.println();
            if(option.equals("salir")) {
                break;
            }

            if(!option.matches("\\d+")) {
                System.out.println("El valor ingresado no corresponde con las opciones\nPor favor intentelo de nuevo\n");
                continue;
            }

            try {
                optionMenu = Byte.parseByte(option);
            }
            catch(NumberFormatException e) {
                System.out.println("Número fuera de rango o inválido. Intente de nuevo.\n");
                continue;
            }

            switch (optionMenu) {
                case 1:
                    // TODO: Buscar libros por titulo
                    // Searching a book for title
                    System.out.println("Searching a book for title");
                    System.out.print("Ingrese el titulo del libro a buscar: ");
                    searchingBook = br.readLine();
                    CONTROLLER.findBookTitle(searchingBook);
                    break;
                case 2:
                    // TODO: listar los libros registrados
                    // List the registered books
                    System.out.println("List the registered books");
                    CONTROLLER.getRegisteredBooks();
                    break;
                case 3:
                    // TODO: listar autores registrados
                    // List the registered authors
                    System.out.println("List the registered authors");
                    CONTROLLER.getRegisteredAuthors();
                    break;
                case 4:
                    // TODO: Listar autores vivos en un determinado anio
                    // List the alive authors in a determinate time
                    break;
                case 5:
                    // TODO: Listar libros por idiomas
                    // List books for languages
                    menuBookLanguage();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.\n");
                    break;
            }
        } while(optionMenu != 0);

        showMessageExit();

        br.close();
    }

    private static void menuBookLanguage() throws IOException{
        final String OPTIONLANGUAGES = 
                """
                es -> Español.
                en -> Ingles.
                fr -> Frances.
                pt -> Portugues.
                salir.
                """;
        String option = null;
        do {
            System.out.println(OPTIONLANGUAGES);
            System.out.print("Ingrese la opcion: ");
            option = br.readLine().trim().toLowerCase();
            System.out.println();

            // Validate if the input was a number
            if(option.matches("\\d+")) {
                System.out.println("El valor ingresado no corresponde con las opciones\nPor favor intentelo de nuevo\n");
                continue;
            }

            switch (option) {
                case "es" -> System.out.println("Español");
                case "en" -> System.out.println("Inglés");
                case "fr" -> System.out.println("Francés");
                case "pt" -> System.out.println("Portugués");
                case "salir" -> System.out.println("Saliendo del menú...");
                default -> System.out.println("Opción no válida. Intente de nuevo.\n");
            }
        }while(!option.equals("salir"));
    }

    private static void showMessageExit() throws InterruptedException{
        System.out.print("Saliendo de la app.");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println(".");
    }
}
