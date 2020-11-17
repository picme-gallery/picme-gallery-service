package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findFirstByOauthKey(String oauthKey);

  Optional<User> findUsersByEvents(Event event);

  Optional<User> findUserByPhotosContaining(Photo photo);

  //TODO check if useful with Todd
  Optional<User> findUserByDisplayName(String displayName);
}
