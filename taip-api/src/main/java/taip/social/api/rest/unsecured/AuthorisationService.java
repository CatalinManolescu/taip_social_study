package taip.social.api.rest.unsecured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import taip.commons.security.Authorisation;
import taip.social.domain.control.UserControl;
import taip.social.domain.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Authorization support for rest services.
 *
 * @author Laura Asoltanei <laura.asoltanei@gmail.com>
 */
@PermitAll
@Path("/")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AuthorisationService {
    private final Logger logger = LoggerFactory.getLogger(AuthorisationService.class);
    
    /**
     * Param key 'username'.
     */
    public static final String KEY_USERNAME = "username";
    /**
     * Param key 'password'.
     */
    public static final String KEY_PASSWORD = "password";

    /**
     * Authentication manager.
     */
    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    UserControl userControl;
    
    /**
     * Login method.
     *
     * @param username The user name.
     * @param password The password.
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Authorization information.
     */
    @POST
    @Path("/login")
    public Authorisation login(@FormParam(KEY_USERNAME) String username,
                               @FormParam(KEY_PASSWORD) String password,
                               @Context HttpServletRequest request,
                               @Context HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new Authorisation(username, toStringRoles(authentication.getAuthorities()));
    }

    /**
     * Logout user.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Authorisation
     */
    @GET
    @Path("/logout")
    public Authorisation logout(@Context HttpServletRequest request,
                                @Context HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        request.getSession().invalidate();

        // reset cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("JSESSIONID")) {
                cookie.setMaxAge(0);
                cookie.setValue("");
                response.addCookie(cookie);
                break;
            }
        }

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setHeader("Authorization", null);
        response.setHeader("authorization", null);

        return getAuthorisation();
    }

    /**
     * Check if user authenticated.
     *
     * @param request HttpServletRequest
     * @return Authorization information if user authenticated and Status.UNAUTHORIZED if not
     */
    @GET
    @Path("/ping")
    public Authorisation ping(@Context HttpServletRequest request) {
        return getAuthorisation();
    }

    /**
     * Get authorisation for current user.
     *
     * @return Authorisation
     */
    private Authorisation getAuthorisation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authorisation authorisation;

        if (authentication != null && !authentication.getName().equalsIgnoreCase("anonymousUser")) {
            authorisation = new Authorisation(authentication.getName(), toStringRoles(authentication.getAuthorities()));
        } else {
            authorisation = new Authorisation("guest", Arrays.asList("guest"));
        }

        return authorisation;
    }

    /**
     * Convert collection of GrantedAuthority items to list of string roles.
     *
     * @param authorities Collection<GrantedAuthority>
     * @return List of roles (string representation)
     */
    private List<String> toStringRoles(Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = new ArrayList<>();

        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                roles.add(authority.getAuthority());
            }
        }

        return roles;
    }
    
    @PostConstruct
    private void onInit() {
        logger.debug("Authorisation service initialised");
        User user = new User();
        user.setUserName("user1");
        userControl.create(user);
        user.setUserName("user2");
        userControl.update(user);
    }
}
