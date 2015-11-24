package taip.social.api.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import taip.commons.errors.BadRequestError;
import taip.social.domain.control.UserControl;
import taip.social.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Username & Password authentication.
 *
 * @author Laura Asoltanei <laura.asoltanei@gmail.com>
 */
@Component
public class UserPassAuthenticationProvider implements AuthenticationProvider {
    /**
     * User and password not null error key.
     */
    public static final String AUTHENTICATION_USER_AND_PASSWORD_NOT_NULL = "authentication.user.and.password.not.null";
    /**
     * Login disabled error key.
     */
    public static final String ERROR_LOGIN_DISABLED = "authentication.login.disabled";
    /**
     * Password invalid error key.
     */
    public static final String ERROR_PASSWORD_INVALID = "authentication.password.invalid";
    /**
     * User not found error key.
     */
    public static final String ERROR_USER_NOT_FOUND = "authentication.user.not.found";

    /**
     * User service.
     */
    @Autowired
    private UserControl userControl;

    /**
     * Password encoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : null;

        User user = validate(username, password);

        return new UsernamePasswordAuthenticationToken(username, password,
                getRoles(user));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * Get granted authorities for user.
     *
     * @param user User
     * @return List of granted authorities
     */
    protected List<GrantedAuthority> getRoles(User user) {
        boolean isAdmin = userControl.isAdmin(user.getId());
        List<String> roles = null;

        if (isAdmin) {
            roles = userControl.getAvailableRoles();
        } else {
            roles = userControl.getRolesAsString(user.getId());
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    /**
     * Validate .
     *
     * @param username username
     * @param password password
     * @return User
     */
    protected User validate(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BadRequestError(
                    AUTHENTICATION_USER_AND_PASSWORD_NOT_NULL,
                    "Username and password cannot be empty");
        }

        User user = userControl.findByUsername(username);
        if (user == null) {
            throw new BadRequestError(ERROR_USER_NOT_FOUND,
                    "Invalid username or password");
        }

        if (!user.getEnabled()) {
            throw new BadRequestError(ERROR_LOGIN_DISABLED,
                    "Invalid username or password");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestError(ERROR_PASSWORD_INVALID,
                    "Invalid username or password");
        }

        return user;
    }
}
