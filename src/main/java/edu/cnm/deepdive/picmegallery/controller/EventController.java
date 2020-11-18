package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import edu.cnm.deepdive.picmegallery.service.EventService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@ExposesResourceFor(Event.class)
public class EventController {

  private final EventService eventService;

  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Event post(@RequestBody Event event, Authentication auth, String passkey, String name) {
    return eventService.save(event,(User) auth.getPrincipal(), passkey,name);
  }

  @GetMapping(value = {"/{id}/{passkey}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Event> get(@PathVariable Long id, @PathVariable String passkey){
    return eventService.get(id, passkey);
  }

}
