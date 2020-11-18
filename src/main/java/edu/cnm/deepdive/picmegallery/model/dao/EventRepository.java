package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {


  Optional<Event> findById(Long id);

  Optional<Event> findByName(String name);

  Optional<Event> findByNameAndAddress(String name, String address);

  Optional<Event> findByTime(Date time);

  Optional<Event> findByAddress(String address);

  Optional<Event> findByLatitudeAndLongitude(Double latitude, Double longitude);

  Optional<Event> findByIdAndPasskey(Long id, String passkey);

  Optional<Event> findAllBy();

  Optional<Event> deleteEventById(Long id);
}
