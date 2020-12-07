package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.PhotoService;
import edu.cnm.deepdive.picmegallery.service.PhotoService.PhotoNotFoundException;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * This Class is a @RestController which handles the endpoints for communication between the client
 * side to the serverside.
 */
@RestController
@RequestMapping("/photos")
@ExposesResourceFor(Photo.class)
public class PhotoController {

  /**
   * This field is a reference to PhotoService.
   */
  private final PhotoService photoService;

  /**
   * This Constructor creates a PhotoService object.
   * @param photoService is a PhotoService object.
   */
  @Autowired
  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  /**
   * Creates a new Photo in the PicMe database with the Event associated.
   * @param photo is a Photo object.
   * @param event is a Event object.
   * @return A saved photo.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Photo post(@RequestBody Photo photo, Event event, Authentication auth) {
    photo.setUser((User)auth.getPrincipal());
    return photoService.save(photo, event);

  }

  /**
   * Deletes a photo associated with a specific primary key.
   * @param auth , is the Authentication object used for authentication, converted with Jwt.
   * @param id is a Photo objects primary key.
   */
  @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id, Authentication auth) {
    photoService.get(id)
        .ifPresentOrElse((photo) -> photoService.delete(photo,(User) auth.getPrincipal()), () -> {
          throw new PhotoNotFoundException();
        });
  }

  /**
   * Gets the photos associated with a User.
   * @param user is a User object.
   * @return A Collection of photos associated with a user
   */
  @GetMapping(value = {"/{user}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> getAllPhotosByUser(@PathVariable User user) {
    return photoService.getAllPhotosByUser(user);
  }

}
