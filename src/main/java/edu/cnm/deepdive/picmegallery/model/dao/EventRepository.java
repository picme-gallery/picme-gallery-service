package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * This interface extends the {@link JpaRepository} and Event entity.
 * This interface handles searching for events using different parameters including id, event name,
 *  address, time, latitude, longitude. It also, allows us to get access to a specific event with {@link
 *  ##findByIdAndPasskey(Long, String)}. Furthermore, we can delete an event too.
 */
public interface EventRepository extends JpaRepository<Event, Long> {


  /**
   * This JPA hibernate query allows users to find an event by its name.
   * @param name is the input parameter for this query, type String.
   * @return this optionally returns an Event, if it matches by name, if not it doesn't return an Event.
   */
  Optional<Event> findByName(String name);

  /**
   * This JPA hibernate query allows users to find an event by its name & address.
   * @param name is one input parameter for this query, type String.
   * @param address is one input parameter for this query, type String.
   * @return this optionally returns an Event, if it matches by name, if not it doesn't return an Event.
   */
  Optional<Event> findByNameAndAddress(String name, String address);

  /**
   * This JPA hibernate query allows users to find an event by the time the event was created.
   * @param time is one input parameter for this query, type String.
   * @return this optionally returns an Event, if it matches by the time query, if not it doesn't return.
   */
  Optional<Event> findByTime(Date time);

  /**
   * This Jpa hibernate query allows users to find an event by the address.
   * @param address is an input parameter for this query, type String.
   * @return this optionally returns an Event, if it matches by the address, if not it doesn't return.
   */
  Optional<Event> findByAddress(String address);

  /**
   * This Jpa hibernate query allows users to find an event by the latitude and longitude.
   * @param latitude is an input parameter for this query, type Double.
   * @param longitude is an input parameter for this query, type Double.
   * @return this optionally returns an Event, if it matches by the the latitude and longitude, if not it doesn't return.
   */
  Optional<Event> findByLatitudeAndLongitude(Double latitude, Double longitude);

  /**
   * This Jpa hibernate query allows users to find an event by the id and passkey.
   * @param id is an input parameter for this query, type String.
   * @param passkey is an input parameter for this query, type String.
   * @return this optionally returns an Event, if it matches the
   */
  Optional<Event> findByIdAndPasskey(long id, String passkey);

  /**
   * This Jpa hibernate query allows users to find all events they're a part of
   * @return a list of events
   */
   List<Event> findEventsByUser(User user);

  /**
   * This query allows users to delete an event by Id
   * @param id that's associated with an Event
   */
  void deleteEventById(Long id);


  Optional<Event> findByIdAndUser(long id, User user);
}
