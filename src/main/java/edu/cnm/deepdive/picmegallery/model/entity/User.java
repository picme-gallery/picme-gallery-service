package edu.cnm.deepdive.picmegallery.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 * User is a @Entity class with the following fields id, created, updated,
 * connected, displayName, and oauthKey.
 * All of the fields in this class are attributes of User and help form the structure of the PicMe Gallery database.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
 @Table(
     name = "user_profile",
     uniqueConstraints = @UniqueConstraint(columnNames = {"oauth_key"}))
public class User {

  /**
   * This field is a primary key.
   */
  // Created a primary key for our entity, it can be changed and must have a value
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long id;

  /**
   * This field is the timestamp of when the User is created.
   */
  // Created timestamp of when the user entity is created, to be used later
  @Column(nullable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  /**
   * Timestamp of when the user profile is updated.
   */
  // Time that user profile is updated
  @Column(nullable = false)
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  /**
   * Timestamp of when the user last connected to our service.
   */
  // Time that user profile connects to our service
  @Temporal(TemporalType.TIMESTAMP)
  private Date connected;

  /**
   * User's display name.
   */
  // Customizable user display name, default is set from google account
  @Column(name = "display_name")
  private String displayName;

  /**
   * Oauth 2.0 key from Google.
   */
  // Google sign in
  @JsonIgnore
  @Column(name = "oauth_key",nullable = false )
  private String oauthKey;

  /**
   * This is the @OneToMany side of the relation between User & Photo.
   * Contains a list of photos ordered by date uploaded, showing who uploaded each photo.
   */
  // Added user list so that we can have access who has uploaded each photo
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  //TODO resolve Json Ignore
  @JsonIgnore
  @NonNull
  private final List<Photo> photos = new LinkedList<>();

  /**
   * This is one side of the @ManyToMany relationship between Event and User
   * Allows us to see which users are associated with which event.
   *  Also, allows us to keep track what photos users have downloaded.
   */
  // Made a list that joins event id and user id so that we can look up what users are associated with which event
  // This is a way to keep track what users have downloaded
  @NonNull
  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  //TODO resolve Json Ignore
  @JsonIgnore
  @JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "event_id"))
  @OrderBy("name ASC")
  private final List<Event> events = new LinkedList<>();


  // Getter for id

  /**
   * Gets the User Id.
   * @return
   */
  public Long getId() {
    return id;
  }

  // Getters and setters for everything else

  /**
   * Gets the date a User is created in our application.
   * @return
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Gets the time/date a User profile is updated.
   * @return
   */
  public Date getUpdated() {
    return updated;
  }

  /**
   * Gets the last time a User connected to the PicME service
   * @return
   */
  public Date getConnected() {
    return connected;
  }

  /**
   * Sets or updates the time the User connected last to our service
   * @param connected
   */
  public void setConnected( Date connected) {
    this.connected = connected;
  }

  /**
   * Gets the User's display name which is different from the one associated
   * with their Google account
   * @return
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Allows the User to set their display name on the PicMe app to something different from
   * their Google account
   * @param displayName
   */
  public void setDisplayName( String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets the User's oauth key they used to sign in and access PicMe Gallery's services
   * @return
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets or allows us to update the User's OauthKey if they modify their Google Account
   * @param oauthKey
   */
  public void setOauthKey( String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   * Returns a list of photos associated with (uploaded by) the current user.
   * @return
   */
  @NonNull
  public List<Photo> getPhotos() {
    return photos;
  }

  /**
   *  This gets us a list of Events that the current user is part of/ included in.
   * @return
   */
  @NonNull
  public List<Event> getEvents() {
    return events;
  }
}
