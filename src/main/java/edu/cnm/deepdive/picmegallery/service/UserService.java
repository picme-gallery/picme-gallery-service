package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.UserRepository;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * The class is an @Service class that implements
 */

@Service
public class UserService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
/**
 *
 This private final field is a reference to UserRepository.
  */
  private final UserRepository userRepository;

  /**
   * This Constructor creates an
   * UserRepository object.
   * UserRepository is a UserRepository object.
   */
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getOrCreate(String oauthKey, String userName) {
    return userRepository.findFirstByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setDisplayName(userName);
          user.setConnected(new Date());
          return userRepository.save(user);
        });
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    Collection<SimpleGrantedAuthority> grants =
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    return new UsernamePasswordAuthenticationToken(
        getOrCreate(jwt.getSubject(),jwt.getClaimAsString("name")),
        jwt.getTokenValue(),
        grants
    );
  }
}
