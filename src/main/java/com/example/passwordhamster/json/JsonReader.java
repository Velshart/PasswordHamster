package com.example.passwordhamster.json;

import com.example.passwordhamster.object.Login;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public static String readUserPassword() {
        String password;
        String filename = "login.json";
        try (FileReader fileReader = new FileReader(filename)) {
            Gson gson = new Gson();
            Login userPassword = gson.fromJson(fileReader, Login.class);

            password = userPassword.getPassword();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return password;
    }
}
