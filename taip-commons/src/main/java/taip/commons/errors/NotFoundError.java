package taip.commons.errors;

/**
 * Error thrown when the resource does not exist.
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public class NotFoundError extends BadRequestError {
    /**
     * {@link InternationalizedError#InternationalizedError(String, Object[], String, Throwable)}
     * .
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public NotFoundError(String localizationKey, Object[] args, String defaultMessage, Throwable cause) {
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
    public NotFoundError(String localizationKey, String defaultMessage, Throwable cause) {
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
    public NotFoundError(String localizationKey, Object[] args, String defaultMessage) {
        super(localizationKey, args, defaultMessage);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, String)}.
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     */
    public NotFoundError(String localizationKey, String defaultMessage) {
        super(localizationKey, defaultMessage);
    }
}
