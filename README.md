Denne applikasjonen lar deg å logge inn til melding side hvor du kan motta meldinger fra andre brukere eller
sende meldinger til andre brukere som finnes i DB "Bruker" tabellen.<br>

Loginn form laget veldig simple, uten noe Authontication sjekk.
Eneste sjekk ved log inn er på brukernavn, hvis brukernavn finnes i tabellen Bruker da han logger seg inn og kan se alle meldingene som ble sendt til bruker,
hvis brukeren finnes ikke i Bruker tabellen, da opprettes en ny bruker.<br>

På FrontEnd ble brukt Thymeleaf, Datatables.js plugin for JQuery, Ajax og Javascript.

Ved start av applikasjonen kjøres migration " create tabbeler " og "insert rader" 
som oppretter to test brukere "Guvanch" og "test";
og to meldingene som ble sendt til hverandre.

## Kjør applikasjonen
```bash
$ mvn spring-boot:run
```

## Login side
 på  http://localhost:8080 i nettleseren og fyll inn en brukernavn