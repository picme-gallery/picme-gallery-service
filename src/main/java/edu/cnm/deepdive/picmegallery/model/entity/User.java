package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
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

  //private Date updated;
//TODO Ask Nick follow-up questions about the implementation of our Oauth key for  sign-in features
  @Column(name = "oauth_key" )
  private String oauthKey;

}
