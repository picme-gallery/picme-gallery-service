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
import org.springframework.lang.NonNull;

@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false, updatable = false)
  private Long id;

  private Date eventTime;

  private Double latitude;

  private Double longitude;

  private String eventName;

  private String password;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("uploaded DESC")
  @NonNull
  private List<Photo> photos = new LinkedList<>();
}
