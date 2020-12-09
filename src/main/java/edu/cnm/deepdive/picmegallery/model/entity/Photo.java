package edu.cnm.deepdive.picmegallery.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.net.URI;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Photo is a @Entity class with the following fields id, event, uploaded,
 * longitude, latitude, uploaded, and caption.
 * All of the fields in this class are attributes of Photo and help form the structure of the PicMe Gallery database.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(indexes = @Index(columnList = "uploaded"))
public class Photo {

  private static EntityLinks entityLinks;

  /**
   * This field is the primary key for photo.
   */
  // Created a photo entity for our PicMe Database, gave it a primary key and auto-generated value
  // This key is mandatory and can't be changed
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "photo_id", nullable = false, updatable = false)
  private Long id;

  /**
   * This field ties an Event Entity within our database.
   * This field is the @ManyToOne side of the relationship between Event and Photo.
   */
  // Ties an Event Entity within our database
  // FK to help id which event a photo is associated with
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "event_id", nullable = false, updatable = false)
  private Event event;

  /**
   * This field is the @ManyToOne side of the relationship between Photo and User.
   */
  // Ties a User Entity within our database
  // FK to help id which user is associated with a photo
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  /**
   * This field is part of the coordinates of the event.
   */
  // Attribute of the photo entity; it is to be used for tracking where photos were taken
  @Nullable
  @Column(updatable = false)
  private Double latitude;

  /**
   * This field is part of the coordinates of the event.
   */
  // Attribute of the photo entity; it is to be used for tracking where photos were taken
  @Nullable
  @Column(updatable = false)
  private Double longitude;

  /**
   * This field is an option for the user to add a caption to a photo.
   */
  // An option for the user to add a caption to a photo
  @Nullable
  private String caption;

  /**
   * This field is a time stamp to see when a user uploaded a photo.
   */
  // A time stamp to see when a user uploaded a photo
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date uploaded;

  @NonNull
  @Column(nullable = false, updatable = false)
  @JsonIgnore
  private String path;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String contentType;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String name;

  /**
   * Gets the Photo id
   * @return the photo id.
   */
  // Getters and setters
  public Long getId() {
    return id;
  }

  /**
   * Gets the latitude of a photo.
   * @return the latitude of a photo.
   */
  @Nullable
  public Double getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude where a photo was taken.
   */
  public void setLatitude(@Nullable Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets the longitude of a photo.
   * @return the longitude of a photo.
   */
  @Nullable
  public Double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude where a photo was taken.
   */
  public void setLongitude(@Nullable Double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets the Event associated with the Photo.
   * @return the Event associated with the Photo.
   */
  public Event getEvent() {
    return event;
  }

  /**
   * Sets the Event that is associated with a Photo.
   */
  public void setEvent(Event event) {
    this.event = event;
  }

  /**
   * Gets the user that that is associated with a Photo.
   * @return the user that is associated with a Photo.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user that is associated with a Photo.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets the caption of a Photo.
   * @return the caption of a Photo.
   */
  @Nullable
  public String getCaption() {
    return caption;
  }

  /**
   * Sets the caption of a Photo.
   */
  public void setCaption(@Nullable String caption) {
    this.caption = caption;
  }

  /**
   * Gets the time stamp of when a photo is uploaded.
   * @return the time stamp of when a photo is uploaded.
   */
  @NonNull
  public Date getUploaded() {
    return uploaded;
  }

  @NonNull
  public String getPath() {
    return path;
  }

  public void setPath(@NonNull String path) {
    this.path = path;
  }

  @NonNull
  public String getContentType() {
    return contentType;
  }

  public void setContentType(@NonNull String contentType) {
    this.contentType = contentType;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  /////////////////////////////////////
  //get hrefs
  /**
   * Returns the location of REST resource representation of this image.
   */
  public URI getHref() {
    //noinspection ConstantConditions
    return (id != null) ? entityLinks.linkForItemResource(Photo.class, id).toUri() : null;
  }
}
