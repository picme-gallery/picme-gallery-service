package edu.cnm.deepdive.picmegallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * This class has the main method which starts the spring boot application.
 */
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@SpringBootApplication
public class PicmeGalleryApplication {

  /**
   * This method is the main method that starts the application.
   * @param args is the command line arguments we pass in to the main method.
   */
  public static void main(String[] args) {
    SpringApplication.run(PicmeGalleryApplication.class, args);
  }

}
