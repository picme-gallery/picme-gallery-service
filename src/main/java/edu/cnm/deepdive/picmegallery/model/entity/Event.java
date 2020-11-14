package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Event is a @Entity class with the following fields {@link ##id}, {@link ##name}, {@link ##address},
 * {@link ##description}, {@link ##eventTime}, {@link ##latitude}, {@link ##longitude}, {@link ##password},
 * {@link ##photos} and {@link ##photos}
 *
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
// Index of event time and event updated
@Table(indexes = {
    @Index(columnList = "event_time"),
    @Index(columnList = "event_updated")
})
public class Event {

  /**
   * This field is id or "event_id".
   * This field is the primary key for Event .
   *
   */
  // Primary key of Event entity
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false, updatable = false)
  private Long id;

  /**
   * This field is event Time or "event_id".
   * This field is an attribute of Event and is the the time an Event is taking place.
   */
  // Date stamp of when an event started
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "event_time", nullable = false, updatable = false)
  private Date eventTime;

  /**
   *This field is a date stamp of when an event and corresponding attributes are updated.
   */
  // Date stamp of when an event and corresponding attributes are updated
  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column( nullable = false, updatable = false)
  private Date updated;

  /**
   * This field is the name of the event.
   */
  //Name of the event
  @Column( nullable = false)
  private String name;

  /**
   * This field is the physical address of the event.
   */
  // Physical address of the event
  private String address;

  /**
   * This field is a modifiable and searchable description of the event.
   */
  // Modifiable and searchable description of the event.
  private String description;

  /**
   * This field is part of the coordinates of the event.
   */
  // Coordinates of the event
  @Column(updatable = false)
  private Double latitude;

  /**
   * This field is part of the coordinates of the event.
   */
  // Coordinates of the event
  @Column(updatable = false)
  private Double longitude;

  /**
   * This field is the password to access event
   */
  // Password to access event
  @Column(nullable = false)
  private String password;

  /**
   * This field is the @OneToMany side of the relationship between Event and Photo.
   */
  // When we delete an event we delete all photos associated with the event.
  @NonNull
  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  private final List<Photo> photos = new LinkedList<>();

  /**
   * This field is one side of the @ManytoMany  relationship between Event and User.
   */
  // Created a list of users associated with each event so that we can see who is a part of which event.
  @NonNull
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  @OrderBy("created ASC")
  private List<User> users = new LinkedList<>();

  // Getters and setters below

  /**
   *  Gets the Events id.
   * @return the Events id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the Events start time
   * @return the Events start time.
   */
  @NonNull
  public Date getEventTime() {
    return eventTime;
  }

  /**
   * Gets the time Event was updated.
   * @return the Events start time.
   */
  @NonNull
  public Date getUpdated() {
    return updated;
  }

  /**
   * Gets the Events name.
   * @return the Events name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the Events name.
   */
  public void setName(String eventName) {
    this.name = eventName;
  }

  /**
   * Gets the Events address.
   * @return the Events start time.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the Events address.
   */
  public void setAddress(String eventAddress) {
    this.address = eventAddress;
  }

  /**
   * Gets the events description.
   * @return the events description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the Event description.
   */
  public void setDescription(String eventDescription) {
    this.description = eventDescription;
  }

  /**
   * Gets the Latitude of an Event.
   * @return latitude of an event.
   */
  @Nullable
  public Double getLatitude() {
    return latitude;
  }
  /**
   * Sets the Latitude of an Event.
   */
  public void setLatitude(@Nullable Double latitude) {
    this.latitude = latitude;
  }
  /**
   * Gets the longitude of an Event.
   * @return longitude of aa Event.
   */
  @Nullable
  public Double getLongitude() {
    return longitude;
  }

  /**
   * Sets the Longitude of an Event.
   */
  public void setLongitude(@Nullable Double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets the password of an Event.
   * @return password of aa Event.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of an Event.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets all the photos related to an event.
   * @return all the photos related to an event.
   */
  @NonNull
  public List<Photo> getPhotos() {
    return photos;
  }

  /**
   * Gets all the user associated with an event.
   * @return the current user.
   */
  @NonNull
  public List<User> getUsers() {
    return users;
  }
}
