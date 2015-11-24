package taip.social.domain.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taip.commons.control.AbstractEntityControl;
import taip.social.domain.entity.User;
import taip.social.domain.entity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public User create(User entity) {
        return entity;
    }

    @Override
    public User update(User entity) {
        return entity;
    }
    
    public User findByUsername(String userName) {
        User user = new User();
        user.setUserName(userName);
        return user;
    }

    public boolean isAdmin(Long id) {
        return true;
    }
    
    public List<String> getAvailableRoles() {
        List<String> roles = new ArrayList<>();
        roles.add("user");
        return roles;
    }
    
    public List<String> getRolesAsString(Long id) {
        return getAvailableRoles();
    }
}
