package br.com.cpaps.systemmanager;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JsonFetcher {
    public static CompletableFuture<Data> fetchData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(JsonFetcher::parseJson);
    }

    private static Data parseJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Data.class);
        } catch (IOException e) {
            // Handle JSON parsing exception
            e.printStackTrace();
            return null; // Or throw a custom exception
        }
    }

    public static class Data {
        public String title;
        public String message;
        public int version;
        public String type;
    }
}
