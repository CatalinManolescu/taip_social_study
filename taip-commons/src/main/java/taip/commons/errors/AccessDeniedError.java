package taip.commons.errors;

/**
 * Error thrown when the request is valid but the user does not have access to resource.
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public class AccessDeniedError extends InternationalizedError {
    /**
     * {@link InternationalizedError#InternationalizedError(String, Object[], String, Throwable)}
     * .
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public AccessDeniedError(String localizationKey, Object[] args, String defaultMessage, Throwable cause) {
        super(localizationKey, args, defaultMessage, cause);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, String, Throwable)}
     * .
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public AccessDeniedError(String localizationKey, String defaultMessage, Throwable cause) {
        super(localizationKey, defaultMessage, cause);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, Object[], String)}
     * .
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     */
    public AccessDeniedError(String localizationKey, Object[] args, String defaultMessage) {
        super(localizationKey, args, defaultMessage);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, String)}.
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     */
    public AccessDeniedError(String localizationKey, String defaultMessage) {
        super(localizationKey, defaultMessage);
    }
}
