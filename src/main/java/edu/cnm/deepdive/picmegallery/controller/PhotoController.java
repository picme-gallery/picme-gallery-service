package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.EventService;
import edu.cnm.deepdive.picmegallery.service.PhotoService;
import edu.cnm.deepdive.picmegallery.service.PhotoService.PhotoNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * This Class is a @RestController which handles the endpoints for communication between the client
 * side to the serverside.
 */
@RestController
@RequestMapping("/events/{eventId}/photos")
@ExposesResourceFor(Photo.class)
public class PhotoController {

  /**
   * This field is a reference to PhotoService.
   */
  private final PhotoService photoService;

  private final EventService eventService;

  /**
   * This Constructor creates a PhotoService object.
   *
   * @param photoService is a PhotoService object.
   * @param eventService
   */
  @Autowired
  public PhotoController(PhotoService photoService,
      EventService eventService) {
    this.photoService = photoService;
    this.eventService = eventService;
  }


  /**
   * Deletes a photo associated with a specific primary key.
   *
   * @param auth , is the Authentication object used for authentication, converted with Jwt.
   * @param id   is a Photo objects primary key.
   */
  @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id, Authentication auth) {
    photoService.get(id)
        .ifPresentOrElse((photo) -> photoService.delete(photo, (User) auth.getPrincipal()), () -> {
          throw new PhotoNotFoundException();
        });
  }

  /**
   * Gets the photos associated with a User.
   * @param user is a User object.
   * @return A Collection of photos associated with a user
   *//*
  @GetMapping(value = {"/{user}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> getAllPhotosByUser(@PathVariable User user) {
    return photoService.getAllPhotosByUser(user);
  }*/

  /* *//**
   * Selects and returns all images.
   *
   * @param auth Authentication token with {@link User} principal.
   * @return Selected images.
   *//*
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Photo> list(Authentication auth) {
    return photoService.list();
  }*/

  /**
   * Stores uploaded file content along with a new {@link Photo} instance referencing the content.
   *
   * @param file MIME content of single file upload.
   * @param auth Authentication token with {@link User} principal.
   * @return Instance of {@link Photo} created &amp; persisted for the uploaded content.
   */
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Creator")
  public ResponseEntity<Photo> postByCreator(
      @PathVariable long eventId,
      @RequestParam MultipartFile file,
      @RequestParam(required = false) Double latitude,
      @RequestParam(required = false) Double longitude,
      @RequestParam(required = false) String caption,
      Authentication auth) {
    return eventService.get(eventId, (User) auth.getPrincipal())
        .map((event) -> securePost(event, file, latitude, longitude, caption,
            (User) auth.getPrincipal()))
        .orElseThrow(PhotoNotFoundException::new);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Passkey")
  public ResponseEntity<Photo> postByPasskey(
      @PathVariable long eventId,
      @RequestHeader("Passkey") String passkey,
      @RequestParam MultipartFile file,
      @RequestParam(required = false) Double latitude,
      @RequestParam(required = false) Double longitude,
      @RequestParam(required = false) String caption,
      Authentication auth) {
    return eventService.get(eventId, passkey)
        .map((event) -> securePost(event, file, latitude, longitude, caption,
            (User) auth.getPrincipal()))
        .orElseThrow(PhotoNotFoundException::new);
  }

  private ResponseEntity<Photo> securePost(Event event, MultipartFile file, Double latitude,
      Double longitude, String caption, User user) {
    try {
      Photo photo = photoService.store(file, user, event, latitude, longitude, caption);
      return ResponseEntity.created(photo.getHref()).body(photo);
    } catch (IOException e) {
      throw new StorageException(e);
    } catch (HttpMediaTypeNotAcceptableException e) {
      throw new MimeTypeNotAllowedException();
    }
  }

  /**
   * Returns the file content of the specified {@link Photo} resource. The original filename of the
   * image is included in the {@code filename} portion of the {@code content-disposition} response
   * header, while the MIME type is returned in the {@code content-type} header.
   *
   * @param id   Unique identifier of {@link Photo} resource.
   * @param auth Authentication token with {@link User} principal.
   * @return Image content.
   */
  @GetMapping(value = "/{id}/content", headers = "Creator")
  public ResponseEntity<Resource> getContentByCreator(
      @PathVariable long eventId, @PathVariable long id, Authentication auth) {
    return eventService.get(eventId, (User) auth.getPrincipal())
        .map((event) -> getSecuredContent(id))
        .orElseThrow(PhotoNotFoundException::new);
  }

  @GetMapping(value = "/{id}/content", headers = "Passkey")
  public ResponseEntity<Resource> getContentByCreator(
      @PathVariable long eventId, @PathVariable long id,@RequestHeader("Passkey") String passkey, Authentication auth) {
    return eventService.get(eventId, passkey)
        .map((event) -> getSecuredContent(id))
        .orElseThrow(PhotoNotFoundException::new);
  }


  public ResponseEntity<Resource> getSecuredContent(long id) {
    return photoService.get(id)
        .map((photo) -> {
          try {
            Resource file = photoService.retrieve(photo);
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, dispositionHeader(photo.getName()))
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.contentLength()))
                .header(HttpHeaders.CONTENT_TYPE, photo.getContentType())
                .body(file);
          } catch (IOException e) {
            throw new StorageException(e);
          }
        })
        .orElseThrow(PhotoNotFoundException::new);
  }

  private String dispositionHeader(String filename) {
    return String.format("attachment; filename=\"%s\"", filename);
  }

  /**
   * Selects and returns a single {@link Photo}, as specified by {@code id}. File content is not
   * returned in the response.
   *
   *
   * @param auth Authentication token with {@link User} principal.
   * @return Instance of {@link Photo} identified by {@code id}.
   *//*
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Photo> getAllForUser(Authentication auth) {
    return photoService.getAllPhotosByUser((User)auth.getPrincipal());

  }*/
}
