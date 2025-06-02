package com.aluracursos.literalura.model;

public enum Languages {
    SPANISH("es"),
    ENGLISH("en"),
    FRENCH("fr"),
    PORTUGUES("pt");
    
    private String languagesBooks;
    Languages(String languagesBooks) {
        this.languagesBooks = languagesBooks;
    }

    public static Languages fromString(String text) {
        for(Languages lang : Languages.values()) {
            if(lang.languagesBooks.equalsIgnoreCase(text)) {
                return lang;
            }
        }

        throw new IllegalArgumentException("Ningun idioma encontrado: " + text); 
    }
}
