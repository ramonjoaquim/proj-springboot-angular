package br.com.dcc.api.utils;

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

    public String getMessage() {
        return this.message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return this.error;
    }

    private void setError(Boolean error) {
        this.error = error;
    }

    public Object getObject() {
        return object;
    }

    private void setObject(Object object) {
        this.object = object;
    }
}
