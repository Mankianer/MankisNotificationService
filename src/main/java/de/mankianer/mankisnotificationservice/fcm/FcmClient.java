package de.mankianer.mankisnotificationservice.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import de.mankianer.mankisnotificationservice.fcm.config.FcmSettings;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FcmClient {

  public FcmClient(FcmSettings settings) {
    Path p = Paths.get(settings.getServiceAccountFile());
    try (InputStream serviceAccount = Files.newInputStream(p)) {
      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://smarthome-d4996.firebaseio.com")
          .build();

      FirebaseApp.initializeApp(options);
    }
    catch (IOException e) {
      log.error("fcm init error", e);
    }
  }

  public void send(String msg, String topic)
      throws InterruptedException, ExecutionException {

    Message message = Message.builder().setTopic(topic)
        .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
            .setNotification(new WebpushNotification("Home Server Nachricht",
                "" + msg))
            .build())
        .build();

    String response = FirebaseMessaging.getInstance().sendAsync(message).get();
    System.out.println("Sent message: " + response);
  }

  public void subscribe(String clientToken, String topic) {
    try {
      TopicManagementResponse response = FirebaseMessaging.getInstance()
          .subscribeToTopicAsync(Collections.singletonList(clientToken), topic).get();
      log.info("{} tokens were subscribed successfully",response.getSuccessCount());
    }
    catch (InterruptedException | ExecutionException e) {
      log.error("subscribe error", e);
    }
  }
}
