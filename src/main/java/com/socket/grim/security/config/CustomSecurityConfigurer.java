package com.socket.grim.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

  private boolean isSecure;

    @Override
    public void init(HttpSecurity http) throws Exception {
      super.init(getBuilder());
        http.setSharedObject(String.class, "CustomSecurityConfigurer was applied");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      super.configure(getBuilder());
      System.out.println("configure method started...");
      if(isSecure) {
        System.out.println("https is required");
      } else {
        System.out.println("https is optional");
      }
    }

    public CustomSecurityConfigurer setFlag(boolean isSecure) {
      this.isSecure = isSecure;
      return this;
    }
}
