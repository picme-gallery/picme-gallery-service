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

  List<Photo> findPhotosByEvent(Event event);

  //////////////////////////////making it like Nick/////////////////////////////////////////////
  List<Photo> findPhotosByEventAndOrderByUploadedDesc(Event event);
///////////////////////////////////////////////////////////////////////////////////////////////////
  // Nick's methods from the Image repository

  /**
   * Returns an {@link Optional Optional&lt;Image&gt;} containing an image with the specified {@code
   * id} and contributed by the specified {@link User}, if any exists.
   *
   * @param id          Unique identifier of image.
   * @param user {@link User} that uploaded the image.
   * @return {@link Optional} containing the selected image, if any; if not, an empty {@link
   * Optional} is returned.
   */
  Optional<Photo> findFirstByIdAndUser(Long id, User user);


  /**
   * Returns all Photos in created datetime (descending) order.
   */
  Iterable<Photo> getAllByOrderByUploadedDesc();

  /**
   * Selects and returns all Photos uploaded by {@code contributor} in descending order of datetime
   * created (uploaded).
   *
   * @param user {@link User} whose uploaded Photos are to be selected.
   * @return All Photos from {@code contributor}.
   */
  Iterable<Photo> findAllByUserOrderByUploadedDesc(User user);

}
