package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.EventService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Class is a @RestController that handles the endpoints for communication between the client side to the serverside for the Events.
 */
@RestController
@RequestMapping("/events")
@ExposesResourceFor(Event.class)
public class EventController {

  /**
   * This private final field is a reference to EventService.
   */
  private final EventService eventService;

  /**
   * This Constructor creates an EventService object.
   * @param eventService is an EventService object.
   */
  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  /**
   * This is a post method that creates a new Event in the PicMe Database.
   * @param event is the event being created.
   * @param auth is an authentication object.
   * @param passkey is the associated passkey needed to get access to an Event.
   * @param name is the name of the Event.
   * @return the created Event.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Event post(@RequestBody Event event, Authentication auth, String passkey, String name) {
    return eventService.save(event,(User) auth.getPrincipal(), passkey,name);
  }

  /**
   * This get method returns access to an Event in the PicMe Database, for those who did not originate the Event.
   * @param id is the associated Event id.
   * @param passkey is the associated passkey.
   * @return access to the specified Event in the PicMeDatabase.
   */
  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Event get(@PathVariable long id, @RequestHeader(value = "Passkey", required = true) String passkey, Authentication auth){
    return eventService.get(id, passkey)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * This method gets the event specified for the User who created this event.
   * @param id the associated event id
   * @param auth the authentication object
   * @return the event for the creator.
   */
  @GetMapping(value = {"/{id}/creator"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Event getEvent(@PathVariable long id,Authentication auth){
    return eventService.get(id, (User) auth.getPrincipal())
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * This method gets the photos associated with an Event in the PicMe Database, for those who did not originate the Event.
   * Hence a passkey is required.
   * @param id id the primary key associated with the specific event object.
   * @param passkey is the associated passkey for an event.
   * @param auth auth the auth object and source of authentication for a specified user.
   * @return  a List of photos
   */
  @GetMapping(value = {"/{id}/photos"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> getPhotos(@PathVariable long id, @RequestHeader(value = "Passkey", required = true) String passkey, Authentication auth){
    return eventService.get(id, passkey)
        .map(Event::getPhotos)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * This method gets the photos associated with an Event in the PicMe Database, for the Event originator/creator.
   * @param id the primary key associated with the specific event object.
   * @param auth the auth object and source of authentication for a specified user.
   * @return a List of photos
   */
  @GetMapping(value = {"/{id}/photos/creator"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> getPhotos(@PathVariable long id,Authentication auth){
    return eventService.get(id, (User) auth.getPrincipal())
        .map(Event::getPhotos)
        .orElseThrow(NoSuchElementException::new);
  }
  /**
   * This method gets all Events in the PicMeDatabase for a specified User.
   * @return a List of Events in the PicMeDatabase for a specified User
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Event> getAllUserEvents(User user) {return eventService.getAllUserEvents(user);
  }

  /**
   * This delete method deletes an event by the specified event id.
   * @param event the passed in event object
   * @param id the primary key associated with the specific event object.
   */
  @DeleteMapping(value = {"/{id}/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@RequestBody Event event, long id) {
    eventService.delete(event, id);
  }
}
