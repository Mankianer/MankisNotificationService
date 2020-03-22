async function requast() {

  const registration = await navigator.serviceWorker.register('/sw.js');
  await navigator.serviceWorker.ready;
  firebase.initializeApp({
    apiKey: "AIzaSyAHfG3P95ph6wvkxCl8z2Qb50Lc_OQ1GBE",
    authDomain: "smarthome-d4996.firebaseapp.com",
    databaseURL: "https://smarthome-d4996.firebaseio.com",
    projectId: "smarthome-d4996",
    storageBucket: "smarthome-d4996.appspot.com",
    messagingSenderId: "808933556558",
    appId: "1:808933556558:web:c9494b666a59cf8058de1f"
  });
  const messaging = firebase.messaging();
  messaging.usePublicVapidKey(
      'BDFK5JhhRKMUGsoWyIk9AMQgujSF_MPtZcQTSGizvV-UMPfsReSupnNQWUxlk40gWTh8ve2U-mv68ZnrC89MH0w');

  messaging.useServiceWorker(registration);

  try {
    await messaging.requestPermission();
  } catch (e) {
    console.log('Unable to get permission', e);
    return;
  }

  const currentToken = await messaging.getToken();
  fetch('/fcm/register', {method: 'post', body: currentToken});

  messaging.onTokenRefresh(async () => {
    console.log('token refreshed');
    const newToken = await messaging.getToken();
    fetch('/register', {method: 'post', body: currentToken});
  });
}
