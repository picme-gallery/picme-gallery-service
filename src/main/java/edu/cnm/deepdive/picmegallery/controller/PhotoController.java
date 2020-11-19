package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.PhotoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This Class is a @RestController which handles the endpoints for communication between the client side to the serverside.
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
   * @return Returns a saved photo.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Photo post(@RequestBody Photo photo,Event event) {
    return photoService.save(photo, event);

  }

  /**
   * Deletes a photo associated with a specific primary key.
   * @param photo is a Photo object.
   * @param id is a Photo objects primary key.
   */
  @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@RequestBody Photo photo, long id) {
  photoService.delete(photo, id);
  }

  /**
   * Gets the photos associated with a User.
   * @param user is a User object.
   * @return A Collection of Photos associated with a user
   */
  @GetMapping(value = {"/{user}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> getAllPhotosByUser (@PathVariable User user){
     return photoService.getAllPhotosByUser(user);
  }

}
