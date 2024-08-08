package br.com.cpaps.systemmanager.data;

import com.fasterxml.jackson.core.TreeCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RamaisApi {
    private static final String API_URL = "http://localhost:8022/api-v1/ramais";
    private static final String DISPATCH_CALL_API = "http://localhost:8022/api-v1/ramais/push?number=";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode fetchAllRamals() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder responseBuilder = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                responseBuilder.append(output);
            }
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(responseBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void pushCall(String number){
        try {
            String fullUrl = DISPATCH_CALL_API + number;
            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
