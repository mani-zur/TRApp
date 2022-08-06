package group.streetwear.trapp.repository;

import group.streetwear.trapp.ldap.ActiveDirectoryUserDetails;
import group.streetwear.trapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ActiveDirectoryUserMapper extends LdapUserDetailsMapper {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {
        UserDetails details = super.mapUserFromContext(dirContextOperations, s, collection);

        String GUID = dirContextOperations.getStringAttribute("objectGUID");

        User user = userRepository.findByActiveDirectoryGUID(GUID);
        if (user == null) {
            User newUser = new User();
            newUser.setActiveDirectoryGUID(GUID);
            newUser.setFirstName(dirContextOperations.getStringAttribute("givenname"));
            newUser.setLastName(dirContextOperations.getStringAttribute("sn"));
            user = userRepository.save(newUser);
        }

        ActiveDirectoryUserDetails userDetails = new ActiveDirectoryUserDetails(details.getUsername(), "[PROTECTED]",
                details.isEnabled(), details.isAccountNonExpired(), details.isCredentialsNonExpired(),
                details.isAccountNonLocked(), details.getAuthorities(), user.getActiveDirectoryGUID(), user.getId());

        return userDetails;
    }
}
