package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This interface extends {@link JpaRepository}.
 * This interface handles the queries that deal with photos.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

  /**
   * Finds photos by the event that they are associated with.
   * @param id is the primary key of an event.
   * @return A collection of photos associated with an event.
   */
  Optional <List<Photo>> findPhotosByEvent(Long id);

  /**
   * Finds a Photo that it is associated with a user.
   * @param user The user that took the photo.
   * @return A photo associated with a specific user.
   */
  Optional<Photo> findPhotosByUser(User user);

  /**
   * Finds a Photo by the the time stamp that is created when a photo is uploaded.
   * @param uploaded Time stamp of when a photo is uploaded,
   * @return A photo.
   */
  Optional<Photo> findPhotosByUploaded(Date uploaded);

  /**
   * Finds a photo using the location of where a photo was taken.
   * @param latitude part of the coordinates of where a photo was taken.
   * @param longitude part of the coordinates of where a photo was taken.
   * @return A photo that was taken in a specific location.
   */
  Optional<Photo> findPhotoByLatitudeAndLongitude(Double latitude, Double longitude);

  /**
   * Finds a photo by the event that it is associated with.
   * @param id is the primary key of an event.
   * @return A photo associated with an event.
   */
  Optional<Photo> findPhotoByEvent(Long id);
}
