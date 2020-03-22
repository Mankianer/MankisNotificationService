package de.mankianer.mankisnotificationservice;

import de.mankianer.mankisnotificationservice.config.FcmSettings;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/api/notification")
public class NotificationWebService {

  @Autowired
  private FcmController fcmController;

  @GetMapping("/send")
  public void sendNotification(@RequestParam(value = "msg", defaultValue = "test") String msg){
    log.info("in msg: {}", msg);
    fcmController.sendNotification(msg);
  }

}
