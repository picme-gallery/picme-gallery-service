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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
 @Table(
     name = "user_profile",
     uniqueConstraints = @UniqueConstraint(columnNames = {"oauth_key"}))
public class User {

  // Created a primary key for our entity, it can be changed and must have a value
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long id;

  // Created timestamp of when the user entity is created, to be used later
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  // Time that user profile is updated
  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;
  // Time that user profile connects to our service
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date connected;
  // Customizable user display name, default is set from google account
  @NonNull
  @Column(name = "display_name")
  private String displayName;
  // Google sign in
  @NonNull
  @Column(name = "oauth_key" )
  private String oauthKey;

  // Added user list so that we can have access who has uploaded each photo
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  @NonNull
  private final List<Photo> photos = new LinkedList<>();

  // Made a list that joins event id and user id so that we can look up what users are associated with which event
  // This is a way to keep track what users have downloaded
  @NonNull
  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "event_id"))
  @OrderBy("name ASC")
  private final List<Event> events = new LinkedList<>();


  // Getters for id


  public Long getId() {
    return id;
  }

  // Getters and setters for everything else

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  @NonNull
  public Date getConnected() {
    return connected;
  }

  public void setConnected(@NonNull Date connected) {
    this.connected = connected;
  }

  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(@NonNull String oauthKey) {
    this.oauthKey = oauthKey;
  }

  @NonNull
  public List<Photo> getPhotos() {
    return photos;
  }

  @NonNull
  public List<Event> getEvents() {
    return events;
  }
}
