package com.example.passwordhamster.json;
import com.example.passwordhamster.object.Login;
import com.example.passwordhamster.object.Password;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class PasswordSaver {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_PATH = "saved-passwords.json";

    public static void savePasswords(List<Password> passwords) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(passwords, writer);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static List<Password> loadPasswords() {
        List<Password> passwords;
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Password>>() {
            }.getType();
            passwords = gson.fromJson(reader, listType);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return passwords;
    }

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