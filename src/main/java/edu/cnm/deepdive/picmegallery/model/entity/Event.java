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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    uniqueConstraints =
        @UniqueConstraint(columnNames = "event_time")
)
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  //TODO remove setter
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "event_time", nullable = false, updatable = false)
  private Date eventTime;

  private Double latitude;

  private Double longitude;

  @Column(name = "event_name",nullable = false, updatable = true)
  private String eventName;

 @Column(nullable = false, updatable = true)
  private String password;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  @NonNull
  private List<Photo> photos = new LinkedList<>();

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

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
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
}
