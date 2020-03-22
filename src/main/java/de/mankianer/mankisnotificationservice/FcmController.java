package de.mankianer.mankisnotificationservice;

import de.mankianer.mankisnotificationservice.config.FcmSettings;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FcmController {

  public static final String TOPIC = "smarthome";

  @Autowired
  private FcmSettings fcmSettings;

  private FcmClient fcmClient;

  @PostConstruct
  private void init(){
    String serviceAccountFile = fcmSettings.getServiceAccountFile();
    log.info("File: {}", serviceAccountFile);
    fcmClient = new FcmClient(fcmSettings);
  }

  public void register(String token){
    fcmClient.subscribe(token, TOPIC);
  }

  public void sendNotification(String msg){
    try {
      fcmClient.send(msg, TOPIC);
    } catch (InterruptedException | ExecutionException e) {
      log.error("Msg: '{}' konnte nicht mit Fcm versendet werden.");
    }
  }
}
