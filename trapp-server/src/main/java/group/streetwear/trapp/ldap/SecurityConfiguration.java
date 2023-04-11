package group.streetwear.trapp.ldap;

import group.streetwear.trapp.repository.ActiveDirectoryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    ActiveDirectoryUserMapper activeDirectoryUserMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/h2-console/*").hasAuthority(Authority.ADMIN)
                .anyRequest()
                .hasAnyAuthority(Authority.USER, Authority.ADMIN)
                .and()
                .formLogin()
                .passwordParameter("userPassword");
        httpSecurity.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        //to remove
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();
    }

    @Bean
    ActiveDirectoryLdapAuthenticationProvider authenticationProvider(
            @Value("${activedirectory.domain}") String domain,
            @Value("${activedirectory.url}") String url,
            @Value("${activedirectory.dn}") String dn
    ) {
        ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
                new ActiveDirectoryLdapAuthenticationProvider(domain, url, dn);
        authenticationProvider.setUserDetailsContextMapper(activeDirectoryUserMapper);
        return authenticationProvider;
    }
}