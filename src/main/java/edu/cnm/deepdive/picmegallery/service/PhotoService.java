package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.PhotoRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * This is a @Service class and holds the additional logic for the queries involving a photo in the PicMe database.
 */
@Service
public class PhotoService {

  /**
   * This field is a reference to PhotoRepository.
   */
  private final PhotoRepository photoRepository;

  /**
   * This constructor initializes a PhotoRepository object.
   * @param photoRepository is a PhotoRepository object.
   */
  @Autowired
  public PhotoService(PhotoRepository photoRepository) {
    this.photoRepository = photoRepository;
  }

  /**
   * Saves a new photo associated with a specific event into the PicMe database.
   * @param photo is a new photo object.
   * @param event is the event a photo was taken at.
   * @return A saved Photo.
   */
  public Photo save(Photo photo, Event event) {

    if (photo.getId() == null || photo.getId() == 0) {
      photo.setEvent(event);
    }
    return photoRepository.save(photo);
  }

  /**
   * Deletes a specific photo.
   * @param photo is the photo that is being deleted.

   */
  public void delete(Photo photo, User user) {

    if (user.getId().equals(photo.getUser().getId())) {
      photoRepository.delete(photo);
    } else {
      throw new ForbiddenException();
    }
  }

  public Optional<Photo> get(long id){
    return photoRepository.findById(id);
  }
  /**
   * Gets all the photos associated with a user.
   * @param user is the person who took the photo.
   * @return A list of photos that a user has taken.
   */
 public List<Photo> getAllPhotosByUser(User user) {
    return photoRepository.findPhotosByUser(user);
 }

  public static class PhotoNotFoundException extends ResponseStatusException {

    private static final String NOT_FOUND_REASON = "Photo not found";

    public PhotoNotFoundException() {
      super(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);
    }

  }

  public static class ForbiddenException extends ResponseStatusException {

    private static final String REASON = "Operation not allowed";

    public ForbiddenException() {
      super(HttpStatus.FORBIDDEN, REASON);
    }

  }
}
