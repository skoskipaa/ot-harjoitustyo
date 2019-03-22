# Projektin alustava vaatimusmäärittely
## Sovelluksen tarkoitus
Tarkoituksena on luoda ajopäiväkirjasovellus esimerkiksi pienyritykselle muutaman ajoneuvon 
ajokilometrien, ajojen tarkoituksen ja mahdollisesti myös huoltojen ja katsastusten seurantaan. 
Sovellukseen on mahdollista syöttää useamman auton tietoja ja sillä voi olla useita käyttäjiä.

## Käyttäjät
Alkuvaiheessa sovelluksen käyttäjillä on vain yksi rooli, peruskäyttäjä, eli kuka tahansa voi syöttää 
sovellukseen uuden ajoneuvon ja kirjata sovellukseen ajotapahtumia. Myöhemmin sovellukseen voidaan lisätä 
pääkäyttäjä, jonka vastuulla on uusien ajoneuvojen syöttäminen. Tällöin sovellukseen lisätään myös 
kirjautuminen, jotta eri käyttäjäroolien ylläpito on mahdollista.

## Käyttöliittymä
Sovellukseen tulee graafinen käyttöliittymä, joka sisältää kolme näkymää: päävalikon, uuden ajoneuvon 
syöttönäkymän ja tapahtuman syöttönäkymän. Päävalikossa on myös mahdollista listata ajoneuvon 
tapahtumahistoria.

## Toimintaympäristö
Sovellus toimii Linux- ja OSX-käyttöjärjestelmillä. Sovelluksen tiedot tallennetaan paikallisesti koneen
levylle tiedostoon tai tietokantaan.

## Perusversion toiminnallisuus
Käyttäjä voi
* Lisätä järjestelmään uuden ajoneuvon. Ajoneuvosta syötetään rekisteritunnus ja ajokilometrit.
* Lisätä uuden ajotapahtuman. Tapahtumaan syötetään ajokilometrit, ajon tarkoitus ja kuljettaja.
* Listata ajoneuvon tapahtumahistorian.

## Jatkokehitys
Aikataulun salliessa sovellukseen voidaan lisätä seuraavat toiminnallisuudet:
* Erilaisten huoltotapahtumien kirjaaminen
* Tulevien tapahtumien (kuten katsastukset tai määräaikaishuollot) kirjaaminen
* Tulevista tapahtumista muistuttaminen
* Kirjautuminen
* Käyttäjäroolien lisääminen
* Ajojen tulostus esimerkiksi laskutettavan asiakkaan perusteella


