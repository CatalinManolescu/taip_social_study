/**
 *
 */
package taip.commons.errors;

/**
 * Server error.
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public class ServerError extends InternationalizedError {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -8924891403612123307L;

    /**
     * {@link InternationalizedError#InternationalizedError(String, Object[], String, Throwable)}
     * .
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public ServerError(String localizationKey, Object[] args,
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
    public ServerError(String localizationKey, String defaultMessage,
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
    public ServerError(String localizationKey, Object[] args,
                       String defaultMessage) {
        super(localizationKey, args, defaultMessage);
    }

    /**
     * {@link InternationalizedError#InternationalizedError(String, String)}.
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     */
    public ServerError(String localizationKey, String defaultMessage) {
        super(localizationKey, defaultMessage);
    }
}
