importScripts('https://www.gstatic.com/firebasejs/7.10.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.10.0/firebase-messaging.js');

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
messaging.usePublicVapidKey('BDFK5JhhRKMUGsoWyIk9AMQgujSF_MPtZcQTSGizvV-UMPfsReSupnNQWUxlk40gWTh8ve2U-mv68ZnrC89MH0w');

self.addEventListener('push', async event => {
  console.log('fcm in msg')
  console.log(event)
});