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
 * This interface handles searching for photos using different parameters including id, Event, uploaded, and location.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {


  /**
   * Finds the photos that are associated with a user.
   * @param user The user that took the photo.
   * @return A collection of photos associated with a specific user.
   */
  List<Photo> findPhotosByUser(User user);

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


}
