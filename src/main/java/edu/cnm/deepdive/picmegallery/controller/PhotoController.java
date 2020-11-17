package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.UserService;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photos")
@ExposesResourceFor(Photo.class)
public class PhotoController {

//  private final PhotoService photoService;
//
//  public PhotoController(PhotoService photoService) {
//    this.photoService = photoService;
//  }
//
//  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
//  public User me(Authentication auth) {
//    return (User) auth.getPrincipal();
//  }

}
