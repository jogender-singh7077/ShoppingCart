package com.mindtree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
  private static final Logger logger = 
            LoggerFactory.getLogger(SpringBootServletInitializer.class);

  public static void main(String[] args) {
    logger.debug("Starting shopping cart application");
    SpringApplication.run(App.class, args);
  }
    
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(App.class);
  }
}
