package kz.enu.epam.azimkhan.tour.exception;

/**
 * Auth exception
 */
public class AuthenticationTechnicalException extends Exception {

    public AuthenticationTechnicalException(){
    }

    public AuthenticationTechnicalException(String message) {
        super(message);
    }

    public AuthenticationTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationTechnicalException(Throwable cause) {
        super(cause);
    }

    public AuthenticationTechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
