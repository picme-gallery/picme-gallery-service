package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.EventRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;

  @Autowired
  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

 public Event save(Event event, User user, String passkey) {
    if (event.getId() == null || event.getId() == 0){
      event.setUser(user);
      //TODO Need to implement a passkey
      //TODO Ask Todd
      event.setPasskey(passkey);
    }
    return eventRepository.save(event);
 }


}
