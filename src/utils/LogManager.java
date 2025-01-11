package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface LogManager {
    // Metoda do logowania zdarzeń
    default void logEvent(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String logMessage = now.format(formatter) + " - " + message + "\n";

        try (FileWriter writer = new FileWriter("application_log.txt", true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda dedykowana do logowania zamknięcia aplikacji
    default void logApplicationClose() {
        logEvent("Aplikacja została zamknięta.");
    }

    // Można dodać inne zdarzenia, np. logowanie błędów
    default void logError(String errorMessage) {
        logEvent("BŁĄD: " + errorMessage);
    }
}