package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table( indexes = @Index(columnList = "uploaded")   )
public class Photo {

  //created a photo entity for our PicMe Database, gave it a primary key and auto-generated value
  // this key is mandatory and can't be changed
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long Id;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false, updatable = false)
  private Event event;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  //an attribute of the photo entity; it is to be used for tracking where photos were taken
  //so that we can use them to tie to an Event Entity within our database.
  @Nullable
  @Column(updatable = false)
  private Double latitude;

  @Nullable
  @Column(updatable = false)
  private Double longitude;


  @Nullable
  private String caption;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date uploaded;

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

  public void setUploaded(@NonNull Date uploaded) {
    this.uploaded = uploaded;
  }
}
