package de.mankianer.mankisnotificationservice;

import de.mankianer.mankisnotificationservice.fcm.FcmController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/notification")
public class NotificationWebService {

  @Autowired
  private FcmController fcmController;

  @GetMapping("/fcm/send")
  public void sendNotification(@RequestParam(value = "msg", defaultValue = "test") String msg){
    log.info("in msg to fcm: {}", msg);
    fcmController.sendNotification(msg);
  }

}
