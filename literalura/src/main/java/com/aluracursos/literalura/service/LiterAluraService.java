package com.aluracursos.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LiterAluraService {
    private final String ENDPOINTURL = "https://gutendex.com/books/";

    public String createConnectionAPI() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINTURL))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            return response.body();
        }
        catch (IOException e) {
            throw new RuntimeException("No se pudo hacer conexion con la API "+ e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String createConnectionAPI(String url) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode json = mapper.readValue(response.body(), JsonNode.class);
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

            return prettyJson;
        }
        catch (IOException e) {
            throw new RuntimeException("No se pudo hacer conexion con la API "+ e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
