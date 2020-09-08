package validator.exception;

public class NotSupportedValidationType extends Exception {

    public NotSupportedValidationType() {
    }

    public NotSupportedValidationType(String message) {
        super(message);
    }

    public NotSupportedValidationType(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedValidationType(Throwable cause) {
        super(cause);
    }

    public NotSupportedValidationType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
