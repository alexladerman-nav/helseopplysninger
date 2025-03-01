package ca.uhn.fhir.jpa.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.*;

import java.util.List;

@Profile("!test")
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final List<String> acceptedAudience;
    private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    /**
     * The property spring.security.oauth2.resourceserver.jwt.accepted-audience used in @Value annotation is not
     * a default spring property, but is added as a custom property here
     */
    public SecurityConfiguration(
            OAuth2ResourceServerProperties oAuth2ResourceServerProperties,
            @Value("${spring.security.oauth2.resourceserver.jwt.accepted-audience}") List<String> acceptedAudience) {
        this.oAuth2ResourceServerProperties = oAuth2ResourceServerProperties;
        this.acceptedAudience = acceptedAudience;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/fhir/metadata", "/actuator/**").permitAll()
            .anyRequest().fullyAuthenticated().and().oauth2ResourceServer().jwt().decoder(jwtDecoder());
    }


    /**
     * IMPORTANT: needed to add audience (aud claim) validation.
     * only issuer is validated by default, in addition to signature and expiry)
     */
    private JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(
                oAuth2ResourceServerProperties.getJwt().getIssuerUri());
        jwtDecoder.setJwtValidator(oAuth2TokenValidator());
        return jwtDecoder;
    }

    private OAuth2TokenValidator<Jwt> oAuth2TokenValidator() {
        OAuth2TokenValidator<Jwt> issuerValidator =
                JwtValidators.createDefaultWithIssuer(
                        oAuth2ResourceServerProperties.getJwt().getIssuerUri());

        OAuth2TokenValidator<Jwt> audienceValidator = token ->
                token.getAudience().stream().anyMatch(acceptedAudience::contains) ?
                        OAuth2TokenValidatorResult.success() :
                        OAuth2TokenValidatorResult.failure(
                                new OAuth2Error("invalid_token",
                                        String.format("None of required audience values '%s' found in token",
                                                acceptedAudience),
                                        null));

        return new DelegatingOAuth2TokenValidator<>(issuerValidator, audienceValidator);
    }
}