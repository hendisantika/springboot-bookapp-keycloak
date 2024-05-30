package com.hendisantika.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-bookapp-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/01/22
 * Time: 13.59
 * To change this template use File | Settings | File Templates.
 */
//@KeycloakConfiguration
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     * <p>
     * Since Spring Security requires that role names start with "ROLE_",
     * a SimpleAuthorityMapper is used to instruct the KeycloakAuthenticationProvider
     * to insert the "ROLE_" prefix.
     * <p>
     * e.g. Librarian -> ROLE_Librarian
     * <p>
     * Should you prefer to have the role all in uppercase, you can instruct
     * the SimpleAuthorityMapper to convert it by calling:
     * {@code grantedAuthorityMapper.setConvertToUpperCase(true); }.
     * The result will be: Librarian -> ROLE_LIBRARIAN.
     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
//        grantedAuthorityMapper.setPrefix("ROLE_");
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
//        auth.authenticationProvider(keycloakAuthenticationProvider);
//    }

    /**
     * Defines the session authentication strategy.
     * <p>
     * RegisterSessionAuthenticationStrategy is used because this is a public application
     * from the Keycloak point of view.
     */
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }

    /**
     * Define an HttpSessionManager bean only if missing.
     * <p>
     * This is necessary because since Spring Boot 2.1.0, spring.main.allow-bean-definition-overriding
     * is disabled by default.
     */
//    @Bean
//    @Override
//    @ConditionalOnMissingBean(HttpSessionManager.class)
//    protected HttpSessionManager httpSessionManager() {
//        return new HttpSessionManager();
//    }

    /**
     * Define security constraints for the application resources.
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/books").hasAnyRole("member", "librarian")
                        .requestMatchers("/static/images/**").permitAll()
                        .requestMatchers("/manager").hasRole("librarian")
                        .anyRequest().permitAll()
                ).build();

    }
}
