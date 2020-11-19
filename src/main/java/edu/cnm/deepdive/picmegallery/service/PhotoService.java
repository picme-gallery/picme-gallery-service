package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.PhotoRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is an @Service class and holds the additional logic for the quarries involving a photo to the PicMe database.
 */
@Service
public class PhotoService {

  private final PhotoRepository photoRepository;

  @Autowired
  public PhotoService(PhotoRepository photoRepository) {
    this.photoRepository = photoRepository;
  }

  public Photo save(Photo photo, Event event) {

    if (photo.getId() == null || photo.getId() == 0) {
      photo.setEvent(event);
    }
    return photoRepository.save(photo);
  }

  public void delete(Photo photo, Long id) {
    if (photo.getId().equals(id)) {
      photoRepository.delete(photo);
    }
  }

 public List<Photo> getAllPhotosByUser(User user) {
    return photoRepository.findPhotosByUser(user);
 }

}
