package com.github.jaketaylorpro.dropwizardgradle.exception;

public class MyException extends Exception {
    private final int code;
    private final String message;
    public MyException(int code, String message) {

        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
