package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.PhotoRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.Photo;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 * This is a @Service class and holds the additional logic for the queries involving a photo in the PicMe database.
 */
@Service
public class PhotoService {

  private static final String UNTITLED_FILENAME = "untitled";
  /**
   * This field is a reference to PhotoRepository.
   */
  private final PhotoRepository photoRepository;
  private final StorageService storageService;

  /**
   * This constructor initializes a PhotoRepository object.
   * @param photoRepository is a PhotoRepository object.
   */
  @Autowired
  public PhotoService(PhotoRepository photoRepository, StorageService storageService) {
    this.photoRepository = photoRepository;
    this.storageService = storageService;
  }

  /**
   * Saves a new photo associated with a specific event into the PicMe database.
   * @param photo is a new photo object.
   * @param event is the event a photo was taken at.
   * @return A saved Photo.
   */
  public Photo save(Photo photo, Event event) {

    if (photo.getId() == null || photo.getId() == 0) {
      photo.setEvent(event);
    }
    return photoRepository.save(photo);
  }

  /**
   * Deletes a specific photo.
   * @param photo is the photo that is being deleted.
   */
  public void delete(Photo photo, User user) {

    if (user.getId().equals(photo.getUser().getId())) {
      photoRepository.delete(photo);
    } else {
      throw new ForbiddenException();
    }
  }

  public Optional<Photo> get(long id){
    return photoRepository.findById(id);
  }
  /**
   * Gets all the photos associated with a user.
   * @param user is the person who took the photo.
   * @return A list of photos that a user has taken.
   */
 public List<Photo> getAllPhotosByUser(User user) {
    return photoRepository.findPhotosByUser(user);
 }

  public static class PhotoNotFoundException extends ResponseStatusException {

    private static final String NOT_FOUND_REASON = "Photo not found";

    public PhotoNotFoundException() {
      super(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);
    }

  }

  public static class ForbiddenException extends ResponseStatusException {

    private static final String REASON = "Operation not allowed";

    public ForbiddenException() {
      super(HttpStatus.FORBIDDEN, REASON);
    }

  }
  /**
   * Selects and returns all images.
   */
  public Iterable<Photo> list() {
    return photoRepository.getAllByOrderByUploadedDesc();
  }

  /**
   * Persists (creates or updates) the specified {@link Photo} instance to the database, updating
   * and returning the instance accordingly. (The instance is updated in-place, but the reference to
   * it is also returned.)
   *
   * @param photo Instance to be persisted.
   * @return Updated instance.
   */
  public Photo save(@NonNull Photo photo) {
    return photoRepository.save(photo);
  }

  /**
   * Stores the Photo data to the file store, then constructs and returns the corresponding instance
   * of {@link Photo}. The latter includes the specified {@code title} and {@code description}
   * metadata, along with a reference to {@code contributor}.
   *
   * @param file        Uploaded file content.
   * @param user Uploading {@link User}.
   * @return {@link Photo} instance referencing and describing the uploaded content.
   * @throws IOException                         If the file content cannot&mdash;for any
   *                                             reason&mdash;be written to the file store.
   * @throws HttpMediaTypeNotAcceptableException If the MIME type of the uploaded file is not on the
   *                                             whitelist.
   */
  public Photo store(
      @NonNull MultipartFile file, @NonNull User user) throws IOException, HttpMediaTypeNotAcceptableException {
    String originalFilename = file.getOriginalFilename();
    String contentType = file.getContentType();
    String reference = storageService.store(file);
    Photo photo = new Photo();
    photo.setUser(user);
    photo.setName((originalFilename != null) ? originalFilename : UNTITLED_FILENAME);
    photo.setContentType(
        (contentType != null) ? contentType : MediaType.APPLICATION_OCTET_STREAM_VALUE);
    photo.setPath(reference);
    return save(photo);
  }

  /**
   * Uses the opaque reference contained in {@code image} to return a consumer-usable {@link
   * Resource} to previously uploaded content.
   *
   * @param photo {@link Photo} entity instance referencing the uploaded content.
   * @return {@link Resource} usable in a response body (e.g. for downloading).
   * @throws IOException If the file content cannot&mdash;for any reason&mdash;be read from the file
   *                     store.
   */
  public Resource retrieve(@NonNull Photo photo) throws IOException {
    return storageService.retrieve(photo.getPath());
  }


}
