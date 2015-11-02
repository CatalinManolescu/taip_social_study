/**
 *
 */
package taip.commons.errors;

/**
 * Bad request.
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public class BadRequestError extends InternationalizedError {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -5693911399766893545L;

    /**
     * {@link InternationalizedError#InternationalizedError(String, Object[], String, Throwable)}
     * .
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public BadRequestError(String localizationKey, Object[] args,
                           String defaultMessage, Throwable cause) {
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
    public BadRequestError(String localizationKey, String defaultMessage,
                           Throwable cause) {
        super(localizationKey, null, defaultMessage, cause);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, Object[], String)}
     * .
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     */
    public BadRequestError(String localizationKey, Object[] args,
                           String defaultMessage) {
        super(localizationKey, args, defaultMessage);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, String)}.
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     */
    public BadRequestError(String localizationKey, String defaultMessage) {
        super(localizationKey, defaultMessage);
    }
}
