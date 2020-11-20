package org.springframework.samples.petclinic.httpbin.web;

import org.keycloak.adapters.springboot.*;
import org.keycloak.adapters.springsecurity.*;
import org.keycloak.adapters.springsecurity.authentication.*;
import org.keycloak.adapters.springsecurity.config.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.authority.mapping.*;
import org.springframework.security.core.session.*;
import org.springframework.security.web.authentication.session.*;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class Security extends KeycloakWebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(
        AuthenticationManagerBuilder auth) throws Exception {

        KeycloakAuthenticationProvider keycloakAuthenticationProvider
            = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
            new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
            new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
            .antMatchers("/get*")
            .hasRole("vets")
            .anyRequest()
            .permitAll();
    }
}
