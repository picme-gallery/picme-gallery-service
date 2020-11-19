package edu.cnm.deepdive.picmegallery.configuration;

import edu.cnm.deepdive.picmegallery.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * This class contains our configuration for the web security on the server-side through google
 * Oauth 2.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * This is a field of our UserService class.
   */
  private final UserService userService;
  /**
   * This is the Uri for google, the site we run our authentication through.
   */
  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;
  /**
   * The clientId associated to the web service side of our Google Oauth2.0 on the Google Cloud
   * Console
   */
  @Value("${spring.security.oauth2.resourceserver.jwt.client-id}")
  private String clientId;

  /**
   * Constructor of our securityConfiguration, utilizing the userService.
   *
   * @param userService UserService which holds the logic for authentication from Jwt.
   */
  @Autowired
  public SecurityConfiguration(UserService userService) {
    this.userService = userService;
  }


  /**
   * This method configures the http security protocol with the jwt and the converter in user
   * Service.
   *
   * @param http an HttpSecurity object
   * @throws Exception that is thrown when the user is not authenticated.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests((auth) ->
            //Add any endpoint specific access rules
            //this is order dependent
            //Permit all/ anonymous -> you probably should define things on your client-side things w/o
            // the authorization header
            //auth.antMatchers(HttpMethod.DELETE, "events/**").permitAll()
            auth.anyRequest().authenticated()
        )
        .oauth2ResourceServer().jwt()
        .jwtAuthenticationConverter(userService);
  }

  /**
   * a decoder method that takes the Oauth2.0 validation token and decodes it from the issuerUri, in
   * our case google. Then it checks the validation token against those in Google's record on the
   * google cloud console. If the clientId and the Google cloud console issuerUri match up then it
   * returns a validated decoder.
   *
   * @return a JwtDecoder object.
   */
  @Bean
  public JwtDecoder jwtDecoder() {
    NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUri);
    OAuth2TokenValidator<Jwt> audienceValidator =
        new JwtClaimValidator<List<String>>(JwtClaimNames.AUD, (aud) -> aud.contains(clientId));
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
    OAuth2TokenValidator<Jwt> combinedValidator =
        new DelegatingOAuth2TokenValidator<Jwt>(withIssuer, audienceValidator);
    decoder.setJwtValidator(combinedValidator);
    return decoder;
  }
}
