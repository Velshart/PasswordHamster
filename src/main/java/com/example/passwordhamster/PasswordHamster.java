package com.example.passwordhamster;

import com.example.passwordhamster.object.Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PasswordHamster extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String loginFilePath = "login.json";

        if(!Files.exists(Paths.get(loginFilePath))) {
            Login defaultPassword = new Login("admin");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonData = gson.toJson(defaultPassword);

            try {
                FileWriter fileWriter = new FileWriter(loginFilePath);
                fileWriter.write(jsonData);
                fileWriter.close();
            }catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        String savedPasswordsFilePath = "saved-passwords.json";
        File savedPasswordsFile = new File(savedPasswordsFilePath);

        if(!savedPasswordsFile.exists()) {
            try {
                FileWriter savedPasswordsWriter = new FileWriter(savedPasswordsFilePath);
                savedPasswordsWriter.write("[]");
                savedPasswordsWriter.close();

            }catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(PasswordHamster.class.getResource("password-generator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Password Hamster");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}