package com.aluracursos.literalura.model;

public enum Languages {
    SPANISH("es", "español"),
    ENGLISH("en", "ingles"),
    FRENCH("fr", "frances"),
    PORTUGUES("pt", "portugues");
    
    private String languagesBooks;
    private String languagesBooksSpanish;
    Languages(String languagesBooks, String languagesBooksSpanish) {
        this.languagesBooks = languagesBooks;
        this.languagesBooksSpanish = languagesBooksSpanish;
    }

    public String getLanguagesBooks() {
        return languagesBooks;
    }

    public String getLanguagesBooksSpanish() {
        return languagesBooksSpanish;
    }

    public static Languages fromString(String text) {
        for(Languages lang : Languages.values()) {
            if(lang.languagesBooks.equalsIgnoreCase(text.trim())) {
                return lang;
            }
        }

        throw new IllegalArgumentException("Ningun idioma encontrado: " + text); 
    }

    public static Languages fromSpanish(String text) {
        for(Languages lang : Languages.values()) {
            if(lang.languagesBooksSpanish.equalsIgnoreCase(text.trim())) {
                return lang;
            }
        }

        throw new IllegalArgumentException("Ningun idioma encontrado: " + text); 
    }
}
