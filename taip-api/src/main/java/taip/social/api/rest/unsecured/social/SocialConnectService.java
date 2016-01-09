package taip.social.api.rest.unsecured.social;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Controller
@PermitAll
@RequestMapping("/connect")
public class SocialConnectService extends ConnectController {
    public static final String FACEBOOK_PROVIDER_ID = "facebook";

    @Inject
    public SocialConnectService(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        return "/" + super.connectedView(providerId) + ".html";
    }

    /*@RequestMapping(value="/{providerId}", method= RequestMethod.GET)
    public String connectToProvider(@PathVariable String providerId) {
        if (FACEBOOK_PROVIDER_ID.equalsIgnoreCase(providerId)) {
            return "redirect:/services/social/facebook";
        }
        return "/";
    }*/
    /*@POST
    @Path("/{providerId}")
    @Override
    public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
        return super.connect(providerId, request);
    }*/

    /*@POST
    @Path("/connect")
    public RedirectView connect(NativeWebRequest request) {
        return super.connect(PROVIDER_ID, request);
    }*/

    /*@Override
    protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        String path = "/social/connect/" + providerId + getPathExtension(servletRequest);
        if (prependServletPath(servletRequest)) {
            path = servletRequest.getServletPath() + path;
        }
        return new RedirectView(path, true);
    }*/
}
