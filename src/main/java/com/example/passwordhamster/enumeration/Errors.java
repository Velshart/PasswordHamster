package com.example.passwordhamster.enumeration;

public enum Errors {
    LENGTH_IS_NOT_A_NUMBER("LENGTH IS NOT A NUMBER"),
    LENGTH_EMPTY("LENGTH FIELD CANNOT BE EMPTY"),
    LENGTH_NOT_PROPERLY_FORMATTED("WRONG LENGTH FORMAT"),
    TAG_NOT_PROVIDED("CHOOSE PASSWORD TAG TO SAVE THIS PASSWORD"),
    SAVED_PASSWORDS_INCORRECTLY_FILLED_FIELDS("SOME FIELDS ARE EMPTY");

    private final String error;

    Errors(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return error;
    }
}
