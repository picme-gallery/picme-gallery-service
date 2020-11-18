package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.PhotoRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    //TODO Ask Todd if we need to implement anything else in photo

    return photoRepository.save(photo);
  }

}
