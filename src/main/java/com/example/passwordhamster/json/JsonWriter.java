package com.example.passwordhamster.json;

import com.example.passwordhamster.object.Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public static void writePasswordToFile(String newPassword) {
        String filename = "login.json";

        try (FileReader fileReader = new FileReader(filename)) {
            Gson gson = new Gson();
            Login userPassword = gson.fromJson(fileReader, Login.class);

            userPassword.setPassword(newPassword);
            Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
            String jsonData = gsonBuilder.toJson(userPassword);

            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(jsonData);
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
