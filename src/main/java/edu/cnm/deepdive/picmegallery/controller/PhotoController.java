package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
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
 *
 */
@RestController
@RequestMapping("/photos")
@ExposesResourceFor(Photo.class)
public class PhotoController {


  private final PhotoService photoService;

  @Autowired
  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Photo post(@RequestBody Photo photo,Event event) {
    return photoService.save(photo, event);

  }

  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Photo> get( @PathVariable Long id){
    return photoService.get(id);
  }

  @GetMapping(value = {"/{id/photos}"}, produces = MediaType.APPLICATION_JSON_VALUE)
 public List<Photo> getAll( @PathVariable Long id){
    return photoService.getAll(id);
 }

  @DeleteMapping(value = {"/{id}/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@RequestBody Photo photo, Long id) {
    photoService.delete(photo, id);
  }


}
