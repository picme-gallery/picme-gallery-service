package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.PhotoRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import java.util.List;
import java.util.Optional;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This
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

    public Optional<Photo> get(Long id) {
      return  photoRepository.findPhotoById(id);
    }

  public Iterable<Photo> getAll(Long id) {
    return photoRepository.findAllByEvent_Id(id);
  }

  public void delete(Photo photo, Long id) {
    if (photo.getId().equals(id)) {
      photoRepository.delete(photo);
    }
  }

//  public void deleteAll(Iterable<? extends Photo> entities) {
//    for (Photo entity : entities) {
//      delete(entity, );
//    }
//  }

}
