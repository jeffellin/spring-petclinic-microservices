package org.springframework.samples.petclinic.api.application;


import org.springframework.context.annotation.*;
import org.springframework.security.config.web.server.*;
import org.springframework.security.oauth2.client.oidc.web.server.logout.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.web.server.*;
import org.springframework.security.web.server.header.*;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveClientRegistrationRepository clientRegistrationRepository) {
        // Authenticate through configured OpenID Provider
        http.oauth2Login();
        // Also logout at the OpenID Connect provider
        http.logout(logout -> logout.logoutSuccessHandler(new OidcClientInitiatedServerLogoutSuccessHandler(
            clientRegistrationRepository)));
        // Require authentication for all requests
        http.authorizeExchange()
            .pathMatchers("/get/**","/httpbin/**").authenticated().and().authorizeExchange().anyExchange().permitAll();
        // Allow showing /home within a frame
        http.headers().frameOptions().mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN);
        // Disable CSRF in the gateway to prevent conflicts with proxied service CSRF
        http.csrf().disable();
        return http.build();
    }

}
