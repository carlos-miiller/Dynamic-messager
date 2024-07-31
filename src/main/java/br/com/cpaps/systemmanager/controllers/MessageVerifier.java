package br.com.cpaps.systemmanager.controllers;

import br.com.cpaps.systemmanager.JsonFetcher;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MessageVerifier {

    private static final String VERSION_FILE_PATH = "src/main/resources/user_data/version.properties";

    public static boolean haveNew() {
        try {
            JsonFetcher.Data currentData = getCurrentDataFromServer();
            if (currentData != null) {
                String type = currentData.type;
                int currentVersion = currentData.version;

                // Fetch the last known version for this type
                String lastVersionStr = getVersionForType(type);
                int lastVersion = lastVersionStr != null ? Integer.parseInt(lastVersionStr) : -1;

                boolean isNew = lastVersion != currentVersion;

                if (isNew || lastVersion == -1) {
                    // If versions differ or type does not exist, update the file
                    updateVersionForType(type, currentVersion);
                }

                return isNew;
            } else {
                System.out.println("Unable to retrieve data from the server.");
                return false;
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    private static JsonFetcher.Data getCurrentDataFromServer() throws InterruptedException, ExecutionException {
        String url = "http://192.168.1.158:8022/api-v1/get-version";
        CompletableFuture<JsonFetcher.Data> dataFuture = JsonFetcher.fetchData(url);
        return dataFuture.get();
    }

    private static String getVersionForType(String type) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(VERSION_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty(type);
        }
    }

    private static void updateVersionForType(String type, int newVersion) throws IOException {
        Properties properties = new Properties();

        // Load existing properties if file exists
        try (InputStream input = new FileInputStream(VERSION_FILE_PATH)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            // If the file does not exist, it's fine; we'll create a new one
        }

        // Update or add the version for the specific type
        properties.setProperty(type, String.valueOf(newVersion));

        // Store the updated properties
        try (OutputStream output = new FileOutputStream(VERSION_FILE_PATH)) {
            properties.store(output, null);
        }
    }
}
