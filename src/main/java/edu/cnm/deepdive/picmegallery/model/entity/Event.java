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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * Event is a @Entity class with the following fields id, name, address,
 * description, time, latitude, longitude, passkey, and photos.
 * All of the fields in this class are attributes of Event and help form the structure of the PicMe Gallery database.
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
// Index of event time and event updated
@Table(indexes = {
    @Index(columnList = "time"),
    @Index(columnList = "updated")
})
public class Event {

  /**
   * This field is the primary key for an Event.
   */
  // Primary key of Event entity
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false, updatable = false)
  private Long id;

  /**
   * This field is the @ManyToOne relationship between Event and User.
   * This is the user associated with creating an Event entity.
   */

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn( name = "user_id", nullable = false)
  private User user;

  /**
   * This field is the time an event is created.
   */
  // Date stamp of when an event started
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date time;

  /**
   *This field is a date stamp of when an event and corresponding attributes are updated.
   */
  // Date stamp of when an event and corresponding attributes are updated
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
  @Nullable
  @Column(updatable = false)
  private Double latitude;

  /**
   * This field is part of the coordinates of the event.
   */
  // Coordinates of the event
  @Nullable
  @Column(updatable = false)
  private Double longitude;

  /**
   * This field is the passkey to access event
   */
  // Passkey to access event
  @Column(nullable = false)
  private String passkey;

  /**
   * This field is the @OneToMany side of the relationship between Event and Photo.
   */
  // When we delete an event we delete all photos associated with the event.
  @NonNull
  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  private final List<Photo> photos = new LinkedList<>();

  /**
   * This field is one side of the @ManytoMany relationship between Event and User.
   */
  // Created a list of users associated with each event so that we can see who is a part of which event.
  @NonNull
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  @OrderBy("created ASC")
  private final List<User> users = new LinkedList<>();

  // Getters and setters below

  /**
   *  Gets the Event's id.
   */
  public Long getId() {
    return id;
  }

  /**
   *  Gets the User who created the event.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the sets the User who created the event.
   */
  public void setUser( User user) {
    this.user = user;
  }

  /**
   * Gets the Event's start time
   */
  public Date getTime() {
    return time;
  }

  /**
   * Gets the time the Event was updated.
   */
  public Date getUpdated() {
    return updated;
  }

  /**
   * Gets the Event's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the Event's name.
   */
  public void setName( String name) {
    this.name = name;
  }

  /**
   * Gets the Event's address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the Event's address.
   */
  public void setAddress(String eventAddress) {
    this.address = eventAddress;
  }

  /**
   * Gets the Event's description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the Event's description.
   */
  public void setDescription(String eventDescription) {
    this.description = eventDescription;
  }

  /**
   * Gets the latitude of an Event.
   */
  @Nullable
  public Double getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude of an Event.
   */
  public void setLatitude(@Nullable Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets the longitude of an Event.
   */
  @Nullable
  public Double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude of an Event.
   */
  public void setLongitude(@Nullable Double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets the passkey of an Event.
   */
  public String getPasskey() {
    return passkey;
  }

  /**
   * Sets the passkey of an Event.
   */
   public void setPasskey( String passkey) {
    this.passkey = passkey;
  }

  /**
   * Gets all the photos related to an event.
   */
  @NonNull
  public List<Photo> getPhotos() {
    return photos;
  }

  /**
   * Gets all the user associated with an event.
   */
  @NonNull
  public List<User> getUsers() {
    return users;
  }
}
