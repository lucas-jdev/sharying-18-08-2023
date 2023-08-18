package com.sharying.taskin.app.domain.error;

public class ParamInvalid extends Exception {

    public ParamInvalid() {
        super("Parâmetro inválido!");
    }

    public ParamInvalid(String message) {
        super(message);
    }

}
