# Arkkitehtuurikuvaus

## Sovelluksen rakenne

Sovelluksen kerrosarkkitehtuuri on kolmitasoinen. Graafinen käyttöliittymä sijaitsee pakkauksessa vehiclelogapp.ui, sovelluslogiikka pakkauksessa vehiclelogapp.domain ja tietojen pysyväistallennus h2-tietokantaan pakkauksessa vehiclelogapp.dao.

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


Ohjelman osien suhdetta kuvaa pakkauskaavio:

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/pakkauskaavio.png" width=700>

## Päätoiminnallisuudet
### Sekvenssikaavio ajoneuvon tapahtumien listauksesta

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavio2.png" width=700>

## Tietojen tallennus

Tietojen pysyväistallennus on toteutettu pakkauksessa vehiclelogapp.dao. Tallennus tapahtuu h2-tietokantajärjestelmään luokissa VehicleDao sekä EntryDao. Lisäksi pakkauksessa on tietokannan luontiin liittyvä apuluokka ServiceDao. Luokat on toteutettu noudattaen Data Access Object -suunnittelumallia, joten tallennusmuoto on korvattavissa muunlaisella toteutuksella sovelluslogiikkaa muokkaamatta.

Tallennustiedoston tiedot ovat hakemiston juuressa sijaitsevassa [config.properties](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/VehicleLogApp/config.properties)-konfiguraatiotiedostossa, jossa määritellään tallennustiedoston nimi, käyttäjätunnus ja salasana.

## Parannuksia ohjelman rakenteeseen (rakenteeseen jääneet heikkoudet)

Dao-pakkauksen luokkien metodeissa on edelleen runsaasti toisteisuutta. Tätä voitaisiin vähentää siirtämällä toistuvaa koodia DaoService-luokan metodeihin. Toinen vaihtoehto metodien lyhentämiseen ja selkiyttämiseen olisi ottaa käyttön Spring-sovelluskehyksen JdbcTemplate-luokka tietokantayhteyden hallintaan.



