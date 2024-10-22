package Exceptions;

public class NullConversionRatesException extends RuntimeException {
    private String message;
    public NullConversionRatesException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
