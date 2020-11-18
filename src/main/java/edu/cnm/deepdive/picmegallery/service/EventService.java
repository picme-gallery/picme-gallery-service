package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.EventRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;

  @Autowired
  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public Event save(Event event, User user, String passkey, String name) {
    if (event.getId() == null || event.getId() == 0) {
      event.setUser(user);
      event.setPasskey(passkey);
      event.setName(name);
    }
    return eventRepository.save(event);
  }

  public Optional<Event> get(Long id, String passkey) {
    return eventRepository.findByIdAndPasskey(id, passkey);

  }

  public List<Event> getAllUserEvents(User user) {
    return eventRepository.findEventsByUser(user);
  }

  public void delete(Event event, Long id) {
    if (event.getId().equals(id)) {
      eventRepository.delete(event);
    }

  }
}