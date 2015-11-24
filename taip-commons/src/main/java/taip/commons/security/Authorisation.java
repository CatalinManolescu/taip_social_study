package taip.commons.security;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Authorisation information.
 *
 * @author Laura Asoltanei <laura.asoltanei@gmail.com>
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "authorisation")
public class Authorisation {
    /**
     * User name.
     */
    private String username = "";
    /**
     * List of roles.
     */
    private List<String> roles = new ArrayList<String>();

    /**
     * Default constructor.
     */
    public Authorisation() {
    }

    /**
     * Set username and roles.
     *
     * @param username Username
     * @param roles    Roles
     */
    public Authorisation(String username, List<String> roles) {
        this.username = username;
        safeSetRoles(roles);
    }

    /**
     * Set username and roles.
     *
     * @param username Username
     * @param roles    Roles
     */
    public Authorisation(String username, Set<String> roles) {
        this.username = username;
        safeSetRoles(roles);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    /**
     * Set roles and initialise the member with empty array in case of param 'null'.
     *
     * @param roles List of roles.
     */
    public void setRoles(List<String> roles) {
        safeSetRoles(roles);
    }

    /**
     * Set roles and initialise the member with empty array in case of param
     * null.
     *
     * @param roles List of roles.
     */
    public void setRoles(Set<String> roles) {
        safeSetRoles(roles);
    }

    /**
     * Convenience method for adding roles.
     *
     * @param roles List of roles
     */
    protected final void safeSetRoles(List<String> roles) {
        this.roles = roles == null ? new ArrayList<String>() : roles;
    }

    /**
     * Convenience method for adding roles.
     *
     * @param roles Set of roles
     */
    protected final void safeSetRoles(Set<String> roles) {
        this.roles.clear();
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }
}
