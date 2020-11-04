package edu.cnm.deepdive.picmegallery.model.entity;

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
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(indexes = @Index(columnList = "uploaded"))
public class Photo {

  // Created a photo entity for our PicMe Database, gave it a primary key and auto-generated value
  // This key is mandatory and can't be changed
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long Id;

  // Ties an Event Entity within our database
  // FK to help id which event a photo is associated with
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "event_id", nullable = false, updatable = false)
  private Event event;

  // Ties a User Entity within our database
  // FK to help id which user is associated with a photo
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  // Attribute of the photo entity; it is to be used for tracking where photos were taken
  @Nullable
  @Column(updatable = false)
  private Double latitude;
  // Attribute of the photo entity; it is to be used for tracking where photos were taken
  @Nullable
  @Column(updatable = false)
  private Double longitude;

  // An option for the user to add a caption to a photo
  @Nullable
  private String caption;

  // A time stamp to see when a user uploaded a photo
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date uploaded;

  // Getters and setters
  public Long getId() {
    return Id;
  }

  @Nullable
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(@Nullable Double latitude) {
    this.latitude = latitude;
  }

  @Nullable
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(@Nullable Double longitude) {
    this.longitude = longitude;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Nullable
  public String getCaption() {
    return caption;
  }

  public void setCaption(@Nullable String caption) {
    this.caption = caption;
  }

  @NonNull
  public Date getUploaded() {
    return uploaded;
  }

}
