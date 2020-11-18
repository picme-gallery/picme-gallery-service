package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.EventService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Class is a @RestController that handles the endpoints for communication between the client side to the serverside for the Event.
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
   * This get method returns access to an Event in the PicMe Database.
   * @param id is the associated Event id.
   * @param passkey is the associated passkey.
   * @return access to the specified Event in the PicMeDatabase.
   */
  @GetMapping(value = {"/{id}/{passkey}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Event> get(@PathVariable Long id, @PathVariable String passkey){
    return eventService.get(id, passkey);
  }

  /**
   * This method gets all Events in the PicMeDatabase for a spscified User.
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Event> getAllUserEvents(User user) {return eventService.getAllUserEvents(user);
  }

  @DeleteMapping(value = {"/{id}/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@RequestBody Event event, Long id) {
    eventService.delete(event, id);
  }
}
