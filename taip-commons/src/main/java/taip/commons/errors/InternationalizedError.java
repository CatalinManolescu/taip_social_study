/**
 *
 */
package taip.commons.errors;

import java.text.MessageFormat;

/**
 * Base class for internationalized error messages.
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public class InternationalizedError extends RuntimeException {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -8924891403782123307L;

    /**
     * Localization Key.
     */
    private String localizationKey;

    /**
     * Message parameters used for localization.
     */
    private Object[] messageParameters;

    /**
     * Constructs a new internationalized error with localization key and default message.
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     */
    public InternationalizedError(String localizationKey, String defaultMessage) {
        this(localizationKey, null, defaultMessage, null);
    }

    /**
     * Constructs a new internationalized error with localization key localization parameters and default message.
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     */
    public InternationalizedError(String localizationKey, Object[] args,
            String defaultMessage) {
        this(localizationKey, args, defaultMessage, null);
    }

    /**
     * Constructs a new internationalized error with localization key, default message and the error cause.
     *
     * @param localizationKey Localization key
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public InternationalizedError(String localizationKey,
            String defaultMessage, Throwable cause) {
        this(localizationKey, null, defaultMessage, cause);
    }

    /**
     * Constructs a new internationalized error with localization key, message parameters, default message and the error cause.
     *
     * @param localizationKey Localization key
     * @param args            Localization parameters
     * @param defaultMessage  Default error message
     * @param cause           The error cause
     */
    public InternationalizedError(String localizationKey, Object[] args,
            String defaultMessage, Throwable cause) {
        super(MessageFormat.format(defaultMessage, args), cause);
        this.localizationKey = localizationKey;
        this.messageParameters = args != null ? args.clone() : null;
    }

    public String getLocalizationKey() {
        return localizationKey;
    }

    public Object[] getMessageParameters() {
        return messageParameters == null ? null : messageParameters.clone();
    }

}
