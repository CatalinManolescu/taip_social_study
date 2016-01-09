package taip.social.api.rest.unsecured.social;

import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FriendList;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Controller
@RequestMapping("/social/facebook")
public class FacebookService {
    private Facebook facebook;

    @Inject
    public FacebookService(Facebook facebook) {
        this.facebook = facebook;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (!facebook.isAuthorized()) {
            return "redirect:/services/social/connect/facebook";
        }

        model.addAttribute("user", facebook.userOperations().getUserProfile());
        //facebook.userOperations().getUserPermissions();
        PagedList<FriendList> friends = facebook.friendOperations().getFriendLists();
        model.addAttribute("friends", friends);

        return "/hello.html";
    }
}
