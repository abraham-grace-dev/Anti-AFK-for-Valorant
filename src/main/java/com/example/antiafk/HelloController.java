package com.example.antiafk;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.robot.Robot;
import javafx.util.Duration;

import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class HelloController {

    private Robot robot;
    private Process process;
    private Path tempScriptPath; // Store reference to temp file for cleanup (optional)

    public HelloController() {
        robot = new Robot();
    }

    @FXML
    private Label head;

    @FXML
    void start() {
        head.setText("Starting in 3...");
        Timeline countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> head.setText("Starting in 2...")),
                new KeyFrame(Duration.seconds(2), e -> head.setText("Starting in 1...")),
                new KeyFrame(Duration.seconds(3), e -> {
                    try {
                        head.setText("Starting...");

                        // Load the script.exe from resources
                        InputStream in = getClass().getResourceAsStream("/com/example/antiafk/script.exe");
                        if (in == null) {
                            head.setText("Error: script.exe not found!");
                            return;
                        }

                        // Create temp file to run
                        tempScriptPath = Files.createTempFile("anti_afk_", ".exe");
                        Files.copy(in, tempScriptPath, StandardCopyOption.REPLACE_EXISTING);
                        in.close();

                        // Execute the temp script
                        process = new ProcessBuilder(tempScriptPath.toAbsolutePath().toString()).start();
                        head.setText("Started.");
                    } catch (Exception ex) {
                        head.setText("Failed to start.");
                        ex.printStackTrace();
                    }
                })
        );
        countdown.play();
    }


    @FXML
    void stop() {
        head.setText("Stopping...");
        try {
            if (process != null) {
                process.destroy();
                process = null;
                head.setText("Stopped.");
            }
        } catch (Exception e) {
            head.setText("Failed to stop.");
            e.printStackTrace();
        }
    }
}