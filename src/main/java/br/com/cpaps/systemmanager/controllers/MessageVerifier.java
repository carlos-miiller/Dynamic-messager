package br.com.cpaps.systemmanager.controllers;

import br.com.cpaps.systemmanager.data.JsonFetcher;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MessageVerifier {
    private static final String VERSION_FILE_PATH = System.getProperty("version.file.path", "user_data/version.properties");

    public static boolean haveNew() {
        try {
            JsonNode currentData = getCurrentDataFromServer();
            if (currentData != null) {
                String type = currentData.path("type").asText();
                int currentVersion = currentData.path("version").asInt();
                String lastVersionStr = getVersionForType(type);
                int lastVersion = lastVersionStr != null ? Integer.parseInt(lastVersionStr) : -1;
                boolean isNew = lastVersion != currentVersion;

                if (isNew || lastVersion == -1) {
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

    private static JsonNode getCurrentDataFromServer() throws InterruptedException, ExecutionException {
        String url = "http://192.168.1.158:8022/api-v1/get-version";
        CompletableFuture<JsonNode> dataFuture = JsonFetcher.fetchData(url);
        return dataFuture.get();
    }

    private static String getVersionForType(String type) throws IOException {
        Properties properties = new Properties();

        // Try to load the properties file
        try (InputStream input = new FileInputStream(VERSION_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty(type);
        } catch (FileNotFoundException e) {
            // If the file is not found, create a new file and return null for the version
            createEmptyVersionFile();
            return null;
        }
    }

    private static void updateVersionForType(String type, int newVersion) throws IOException {
        Properties properties = new Properties();

        // Load existing properties if the file exists
        try (InputStream input = new FileInputStream(VERSION_FILE_PATH)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            // If the file does not exist, create it
            createEmptyVersionFile();
        }

        // Update or add the version for the specific type
        properties.setProperty(type, String.valueOf(newVersion));

        // Store the updated properties
        try (OutputStream output = new FileOutputStream(VERSION_FILE_PATH)) {
            properties.store(output, null);
        }
    }

    private static void createEmptyVersionFile() throws IOException {
        File file = new File(VERSION_FILE_PATH);
        File parentDir = file.getParentFile();

        // Create the parent directories if they don't exist
        if (parentDir != null && !parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated) {
                throw new IOException("Failed to create directories for path: " + VERSION_FILE_PATH);
            }
        }

        // Create an empty properties file
        Properties properties = new Properties();
        try (OutputStream output = new FileOutputStream(file)) {
            properties.store(output, "Version properties file created.");
        }
        System.out.println("Version properties file created at: " + VERSION_FILE_PATH);
    }
}
