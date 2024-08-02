package br.com.cpaps.systemmanager.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JsonFetcher {
    public static CompletableFuture<JsonNode> fetchData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(JsonFetcher::parseJson);
    }

    private static JsonNode parseJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            // Handle JSON parsing exception
            e.printStackTrace();
            return null; // Or throw a custom exception
        }
    }
}
