package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
 @Table(uniqueConstraints =
 @UniqueConstraint(columnNames = {"oauth_key"}))
public class User {

  //created a primary key for our entity, it can be changed and must have a value
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long id;

  //created timestamp of when the user entity is created, to be used later
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  //TODO ask nick about updated
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

//TODO Ask Nick follow-up questions about the implementation of our Oauth key for  sign-in features
  @Column(name = "oauth_key" )
  private String oauthKey;

  // getters for id


  public Long getId() {
    return id;
  }

  // getters and setters for everything else

  @NonNull
  public Date getCreated() {
    return created;
  }

  public void setCreated(@NonNull Date created) {
    this.created = created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(@NonNull Date updated) {
    this.updated = updated;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }
}
