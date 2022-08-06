package group.streetwear.trapp.ldap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ActiveDirectoryUserDetails extends User {
    private final String activeDirectoryGUID;
    private final Long userId;

    public ActiveDirectoryUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                                      boolean credentialsNonExpired, boolean accountNonLocked,
                                      Collection<? extends GrantedAuthority> authorities, String activeDirectoryGUID,
                                      Long userId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.activeDirectoryGUID = activeDirectoryGUID;
        this.userId = userId;
    }

    public String getActiveDirectoryGUID() {
        return activeDirectoryGUID;
    }

    public Long getUserId() {
        return userId;
    }
}
