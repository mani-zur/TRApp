package group.streetwear.trapp.ldap;

import group.streetwear.trapp.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ActiveDirectoryUserDetails extends org.springframework.security.core.userdetails.User{

    private final User user;
    public ActiveDirectoryUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                                      boolean credentialsNonExpired, boolean accountNonLocked,
                                      Collection<? extends GrantedAuthority> authorities,
                                      User user) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
