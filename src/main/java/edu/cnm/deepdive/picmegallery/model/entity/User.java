package edu.cnm.deepdive.picmegallery.model.entity;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class User {

  private Long Id;

  private Date created;

  private Date updated;

  private String oauthKey;

}
