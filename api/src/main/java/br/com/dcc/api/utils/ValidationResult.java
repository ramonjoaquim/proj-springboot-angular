package br.com.dcc.api.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResult {

    private String message;
    private Boolean error;
    private Object object;

    public ValidationResult(Boolean error, String message) {
        this.setMessage(message);
        this.setError(error);
    }

    public ValidationResult(Boolean error) {
        this.setError(error);
    }

    public ValidationResult(Boolean error, Object object) {
        this.setError(error);
        this.setObject(object);
    }

    public ValidationResult(Object object, Boolean error, String message){
        this.setMessage(message);
        this.setError(error);
        this.setObject(object);
    }
}
