package edu.cnm.deepdive.picmegallery.controller;

import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Convenience class extending {@link ResponseStatusException}, for use when a request references a
 * non-existing {@link Photo} entity instance.
 */
public class PhotoNotFoundException extends ResponseStatusException {

  private static final String NOT_FOUND_REASON = "Image not found";

  /**
   * Initializes this instance with a relevant message &amp; response status.
   */
  public PhotoNotFoundException() {
    super(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);
  }

}
