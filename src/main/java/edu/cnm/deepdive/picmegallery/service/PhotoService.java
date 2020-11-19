package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.PhotoRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   * @param id is the primary key of the photo that is being deleted.
   */
  public void delete(Photo photo, Long id) {
    if (photo.getId().equals(id)) {
      photoRepository.delete(photo);
    }
  }

  /**
   * Gets all the photos associated with a user.
   * @param user is the person who took the photo.
   * @return A list of photos that a user has taken.
   */
 public List<Photo> getAllPhotosByUser(User user) {
    return photoRepository.findPhotosByUser(user);
 }

}
