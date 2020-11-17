package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

  Optional<Photo> findPhotosByEvent(Event event);

  Optional<Photo> findPhotosByUser(User user);

  Optional<Photo> findPhotosByUploaded(Date uploaded);

  Optional<Photo> findPhotoByLatitudeAndLongitude(Double latitude, Double longitude);

}
