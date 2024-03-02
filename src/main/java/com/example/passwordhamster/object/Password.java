package com.example.passwordhamster.object;

public class Password {
    private final String tag;

    private String password;

    public Password(String tag, String password) {
        this.tag = tag;
        this.password = password;
    }

    public String getTag() {
        return tag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
