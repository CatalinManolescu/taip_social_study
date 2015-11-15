package taip.social.domain.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taip.commons.control.AbstractEntityControl;
import taip.social.domain.entity.User;
import taip.social.domain.entity.repository.UserRepository;

/**
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class UserControl extends AbstractEntityControl<UserRepository, User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }
}
