package com.jonasgestopa.ems.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jonasgestopa.ems.MainApplication;

public class Database {
    public Connection getConnection() throws SQLException {
        String dbUrl = loadDatabaseFromResources("database/ems.db", "appdata/test.db");
        assert dbUrl != null;
        return DriverManager.getConnection(dbUrl);
    }

    // Function to load the database from resources
    private String loadDatabaseFromResources(String resourcePath, String destinationPath) {
        // System.out.println(MainApplication.class.getResourceAsStream("database/ems.db"));
        try (InputStream dbStream = MainApplication.class.getResourceAsStream(resourcePath)) {
            if (dbStream == null) {
                System.out.println("Database file not found in resources.");
                System.exit(0);
                return null;
            }

            File permanentDb = new File(destinationPath);
            permanentDb.getParentFile().mkdirs(); // Ensure directory exists
            Files.copy(dbStream, permanentDb.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String url = "jdbc:sqlite:" + permanentDb.getAbsolutePath();
            System.out.println("Database URL: " + url);
            return url;
        } catch (IOException e) {
            System.out.println("Error loading database file: " + e.getMessage());
            return null;
        }
    }
}
