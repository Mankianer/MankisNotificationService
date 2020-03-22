package de.mankianer.mankisnotificationservice.fcm.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "fcm")
@Component
@Data
public class FcmSettings {
  private String serviceAccountFile;
}
