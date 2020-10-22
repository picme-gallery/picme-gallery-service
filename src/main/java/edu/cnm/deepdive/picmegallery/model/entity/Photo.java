package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "photo_id", nullable = false, updatable = false)
  private Long Id;

  private Double latitude;

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
}
