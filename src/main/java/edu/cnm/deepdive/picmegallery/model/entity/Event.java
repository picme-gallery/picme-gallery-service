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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table( indexes = {
    @Index(columnList = "event_time"),
    @Index(columnList = "event_updated")
})
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "event_time", nullable = false, updatable = false)
  private Date eventTime;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "event_updated", nullable = false, updatable = false)
  private Date eventUpdated;

  @Column(name = "event_name",nullable = false)
  private String eventName;

  @Column(name = "event_address")
  private String eventAddress;

  @Column(name = "event_description")
  private String eventDescription;

  @Nullable
  @Column(updatable = false)
  private Double latitude;

  @Nullable
  @Column(updatable = false)
  private Double longitude;


 @Column(nullable = false)
  private String password;

 // When we delete an event we delete all photos associated with the event.
  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  @NonNull
  private List<Photo> photos = new LinkedList<>();

  //created a list of users associated with each event so that we can see who is apart of which event.
  @NonNull
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  @OrderBy("created ASC")
  private final List<User> users = new LinkedList<>();

  public Long getId() {
    return id;
  }

  @NonNull
  public Date getEventTime() {
    return eventTime;
  }

  public void setEventTime(@NonNull Date eventTime) {
    this.eventTime = eventTime;
  }

  @NonNull
  public Date getEventUpdated() {
    return eventUpdated;
  }

  public void setEventUpdated(@NonNull Date eventUpdated) {
    this.eventUpdated = eventUpdated;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getEventAddress() {
    return eventAddress;
  }

  public void setEventAddress(String eventAddress) {
    this.eventAddress = eventAddress;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @NonNull
  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(@NonNull List<Photo> photos) {
    this.photos = photos;
  }

  @NonNull
  public List<User> getUsers() {
    return users;
  }
}
