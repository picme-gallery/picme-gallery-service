package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository is an interface class that extends JpaRepository and User entity
 * This interface handles searching for users by different parameters including Google Oauth Key 2.0,
 * events, photos, and display names.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * @param oauthKey is a String input parameter from the user's google account.
   * @return Optionally returns a user found by their Oauth 2.0 key from Google.
   */
  Optional<User> findFirstByOauthKey(String oauthKey);

  /**
   * @param event is an input parameter of a user's event.
   * @return Optionally returns a user by events.
   */
  Optional<User> findUsersByEvents(Event event);

  /**
   * @param photo is an input parameter of a user's photo.
   * @return Optionally returns a user by a photo containing the user.
   */
  Optional<User> findUserByPhotosContaining(Photo photo);

  /**
   *
   * @param displayName is a String input parameter of a user's display name
   * @return Optionally returns a user by their display name.
   */
  //TODO check if useful with Todd
  Optional<User> findUserByDisplayName(String displayName);
}
