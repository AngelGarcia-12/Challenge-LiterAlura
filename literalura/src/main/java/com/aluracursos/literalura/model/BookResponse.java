package com.aluracursos.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResponse(
    @JsonAlias("count")
    Integer count,
    @JsonAlias("results")
    List<BookData> results
) {}
