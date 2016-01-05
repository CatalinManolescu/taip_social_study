package taip.social.api.rest.unsecured.social.config;

import org.springframework.context.annotation.*;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class SocialConfig {
    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository connectionRepository) {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        return connection != null ? connection.getApi() : null;
    }

}
