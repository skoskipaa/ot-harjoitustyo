# Arkkitehtuurikuvaus

## Sovelluksen rakenne

Sovelluksen kerrosarkkitehtuuri on kolmitasoinen. Graafinen käyttöliittymä sijaitsee pakkauksessa vehiclelogapp.ui, sovelluslogiikka pakkauksessa vehiclelogapp.domain ja tietojen pysyväistallennus h2-tietokantaan pakkauksessa vehiclelogapp.dao. Käyttöliittymän kautta hallitaan sovelluslogiikkaa, joka puolestaan käyttää dao-pakkauksen luokkia talletukseen.

## Käyttöliittymä

Käyttöliittymässä on neljä erilaista näkymää Scene-olioina toteutettuna: 

* Päänäkymä
* Ajoneuvon syöttönäkymä
* Tapahtuman syöttönäkymä
* Haku

Näistä näkymistä näytetään yksi kerrallaan. Lisäksi käyttöliittymässä on erikseen avautuva ikkuna hakuihin (ajoneuvot, ajoneuvon tapahtumat, tapahtumien avoin haku) liittyvien tulosten näyttämistä varten. Käyttöliittymä on toteutettuna sovelluksen luokassa [vehiclelogapp.ui.GraphicInterface](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/VehicleLogApp/src/main/java/vehiclelogapp/ui/GraphicInterface.java).

init()-metodissa luetaan config.properties-tiedostosta käytettävään tietokantatiedostoon liittyvät määritteet, luodaan VehicleLogService-olio sekä ObservableList-olio, jota käytetään sovelluksen valikoissa.

Käyttöliittymä kutsuu luokan [VehicleLogService](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/VehicleLogApp/src/main/java/vehiclelogapp/domain/VehicleLogService.java) metodeja, jotka toteuttavat sovelluslogiikan. 

## Sovelluslogiikka

Sovelluslogiikka on VehicleLogService-luokasta luodun olion vastuulla. Luokan metodeilla toteutetaan kaikki sovelluksen toiminnallisuudet tietojen hakuun ja tietokantaan tallennukseen. VehicleLogService-olio käyttää tietokantatoimintoihin dao-pakkauksen Dao-rajapinnan toteuttavia luokkia VehicleDao ja EntryDao, jotka otetaan käyttöön konstruktorikutsun yhteydessä -  samoin kuin DaoService, joka hoitaa tietokannan luomisen ja yhteyden tietokantaan.

Ohjelman osien suhdetta kuvaa seuraavanlainen pakkauskaavio:

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/pakkauskaavio.png" width=700>

## Päätoiminnallisuudet

Sovelluksen tärkeimmät toiminnallisuudet toteutetaan metodeissa

* addVehicle(String licensePlate, int kilometers)
* addEntry(String licensePlate, int km, String driver, String entryType)
* listEntriesForVehicle(String licensePlate)
* searchEntries(String key)

Sovelluslogiikan toimintaa kuvaa sekvenssikaavio ajoneuvon tapahtumien listauksesta:

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavio2.png" width=700>

Graafisessa käyttöliittymässä tapahtumankäsettelijä reagoi listEntries-napin painamiseen kutsumalla sovelluslogiikan metodia listEntriesForVehicle(), joka saa parametrikseen valikosta valitun ajoneuvon rekisteritunnuksen. VehicleLogService kutsuu ensin VehicleDaon metodia getVehicleId(), ja sitten VehicleDaon palauttamalla id:llä EntryDaon metodia getEntriesForVehicle(), joka palauttaa VehicleLogServicelle listan Entry-olioita. Sovelluslogiikka muuntaa listan Stringeiksi (Entry-luokan toString()-metodilla) ja paluttaa listan käyttöliittymälle, joka näyttää tulokset käyttäjälle uudessa ikkunassa.

Sovelluksen muidenkin toiminnallisuuksien toimintalogiikka on samankaltainen. Käyttöliittymä reagoi käyttäjän pyyntöihin ja kutsuu sovelluslogiikkaa. Sovelluslogiikka toteuttaa pyynnön tallennusluokkia hyväksikäyttäen ja palauttaa käyttäjälle näytettävän sisällön käyttöliittymälle, joka tulostaa sen.

## Tietojen tallennus

Tietojen pysyväistallennus on toteutettu pakkauksessa vehiclelogapp.dao. Tallennus tapahtuu h2-tietokantajärjestelmään luokissa VehicleDao sekä EntryDao. Lisäksi pakkauksessa on tietokannan luontiin liittyvä apuluokka ServiceDao. Luokat on toteutettu noudattaen Data Access Object -suunnittelumallia, joten tallennusmuoto on korvattavissa muunlaisella toteutuksella sovelluslogiikkaa muokkaamatta.

Tallennustiedoston tiedot ovat hakemiston juuressa sijaitsevassa [config.properties](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/VehicleLogApp/config.properties)-konfiguraatiotiedostossa, jossa määritellään tallennustiedoston nimi, käyttäjätunnus ja salasana.

## Parannuksia ohjelman rakenteeseen (rakenteeseen jääneet heikkoudet)

Dao-pakkauksen luokkien metodeissa on edelleen runsaasti toisteisuutta. Tätä voitaisiin vähentää siirtämällä toistuvaa koodia DaoService-luokan metodeihin. Toinen vaihtoehto metodien lyhentämiseen ja selkiyttämiseen olisi ottaa käyttön Spring-sovelluskehyksen JdbcTemplate-luokka tietokantayhteyden hallintaan.

Koska käsiteltävä tietomäärä ei ole toistaiseksi suuri, tietokantahakuja voisi yksinkertaistaa jättämällä tietokantahakutulosten lajittelun ja rajauksen sovelluslogiikan vastuulle. Tällöin ylimääräiset rajatun haun metodit voisi poistaa erityisesti EntryDao-luokasta.

Syötteiden validointia tehdään sekä käyttöliittymässä että sovelluslogiikassa. Käyttäjälle saadaan annettua selkeämmät ja tarkemmat virheilmoitukset, kun validointia tehdään käyttöliittymässä, mutta aiheuttaa päällekkäisyyttä.


