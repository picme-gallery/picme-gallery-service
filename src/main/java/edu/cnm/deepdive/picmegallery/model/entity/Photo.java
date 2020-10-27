package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

public class Photo {

  //created a photo entity for our PicMe Database, gave it a primary key and auto-generated value
  // this key is mandatory and can't be changed
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long Id;

  //an attribute of the photo entity; it is to be used for tracking where photos were taken
  //so that we can use them to tie to an Event Entity within our database.
  @Nullable
  private Double latitude;

  @NonNull
  private Double longitude;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false, updatable = false)
  private Event event;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  private String caption;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date uploaded;

  public Long getId() {
    return Id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
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

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
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
