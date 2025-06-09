package com.aluracursos.literalura.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.aluracursos.literalura.controller.ServiceController;
import com.aluracursos.literalura.model.Languages;

@Component
public class MenuApp {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ServiceController CONTROLLER;

    public MenuApp(ServiceController controller) {
        this.CONTROLLER = controller;
    }

    public void menuMain() throws IOException, InterruptedException {
        final String BANNER = 
        "   _     _ _             _    _                 \n" +
        "  | |   (_) |_ ___ _ __ / \\  | |_   _ _ __ __ _ \n" +
        "  | |   | | __/ _ \\ '__/ _ \\ | | | | | '__/ _` |\n" +
        "  | |___| | ||  __/ | / ___ \\| | |_| | | | (_| |\n" +
        "  |_____|_|\\__\\___|_|/_/   \\_\\_|\\__,_|_|  \\__,_|\n"
        ;
        final String MENUVIEW = 
                """
                *******************************************************
                %s                                     
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
            System.out.printf(MENUVIEW, BANNER);
            System.out.print("Ingrese su opcion: ");
            option = br.readLine().trim().toLowerCase();
            System.out.println();
            if(option.equals("salir")) {
                break;
            }

            if(!option.matches("^-?\\d+")) {
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
                    // Searching a book for title
                    System.out.print("Ingrese el titulo del libro a buscar: ");
                    searchingBook = br.readLine();
                    CONTROLLER.findBookTitle(searchingBook);
                    continueAction(br);
                    break;
                case 2:
                    // List the registered books
                    CONTROLLER.getRegisteredBooks();
                    continueAction(br);
                    break;
                case 3:
                    // List the registered authors
                    CONTROLLER.getRegisteredAuthors();
                    continueAction(br);
                    break;
                case 4:
                    // List the alive authors in a determinate time
                    System.out.print("Ingrese el anio en que cree que pudo estar vivo o en el cual murio el autor: ");
                    option = br.readLine();
                    if(!option.matches("^-?\\d+")) {
                        System.out.println("Por favor ingrese un valor numerico para el anio\n");
                        continueAction(br);
                        continue;
                    }
                    if(Integer.parseInt(option) < -1 || Integer.parseInt(option) > LocalDate.now().getYear()) {
                        System.out.println("Ingrese un anio valido\n");
                        continueAction(br);
                        continue;
                    }
                    CONTROLLER.getAuthorAliveOrDeath(Integer.parseInt(option));
                    continueAction(br);
                    break;
                case 5:
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

    private void menuBookLanguage() throws IOException {
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
            if(option.matches("^-?\\d+")) {
                System.out.println("El valor ingresado no corresponde con las opciones\nPor favor intentelo de nuevo\n");
                continue;
            }

            try {
                // Intentar primero por código ("es", "en", etc.)
                Languages lang = Languages.fromString(option);
                CONTROLLER.findByLanguages(lang);
                continueAction(br);
            } catch (IllegalArgumentException e1) {
                try {
                    // Si no funcionó, intentar por español ("español", "ingles", etc.)
                    Languages lang = Languages.fromSpanish(option);
                    CONTROLLER.findByLanguages(lang);
                    continueAction(br);
                } catch (IllegalArgumentException e2) {
                    System.out.println("Idioma no reconocido. Intente con un código válido o el nombre en español.\n");
                }
            }
        }while(!option.equals("salir"));
    }

    private void showMessageExit() throws InterruptedException{
        System.out.print("Saliendo de la app.");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println(".");
    }

    private void continueAction(BufferedReader br) throws IOException{
        System.out.println("Presione Enter para continuar");
        br.readLine();
    }
}
