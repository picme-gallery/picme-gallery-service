package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.UserService;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a @RestController that handles endpoints for communication between the client
 * side and the serverside for the User.
 */

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {

  /**
   * This field is a reference to UserService.
   */
  private final UserService userService;

  /**
   *This Constructor creates a UserService object.
   * @param userService calls on UserService class.
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   *This is a get method that returns the current authenticated user.
   * @param auth is an authentication object.
   * @return Returns the current authenticated user.
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User me(Authentication auth) {
    return (User) auth.getPrincipal();
  }

}
