# Vaatimusmäärittely
## Sovelluksen tarkoitus
Tarkoituksena on luoda ajopäiväkirjasovellus esimerkiksi pienyritykselle muutaman ajoneuvon 
ajokilometrien, ajojen tarkoituksen ja muiden ajoneuvoihin liittyvien tapahtumien kirjaamiseen ja seurantaan. 
Sovellukseen on mahdollista syöttää useamman auton tietoja ja sillä voi olla useita käyttäjiä.

## Käyttäjät
Sovelluksen käyttäjillä on vain yksi rooli, peruskäyttäjä, eli kuka tahansa voi käynnistää sovelluksen, syöttää 
sovellukseen uuden ajoneuvon, kirjata sovellukseen ajotapahtumia sekä tehdä hakuja kirjautumatta.

## Käyttöliittymä
Sovelluksessa on graafinen käyttöliittymä, joka sisältää neljä näkymää: päävalikon, uuden ajoneuvon 
syöttönäkymän, tapahtuman syöttönäkymän sekä hakunäkymän. Ajoneuvon tapahtumalistaus, kaikkien järjestelmän ajoneuvojen listaus sekä haun tulokset tulostuvat erilliseen ikkunaan.

## Toimintaympäristö
Sovellus toimii Linux- ja OSX-käyttöjärjestelmillä. Sovelluksen tiedot tallennetaan paikallisesti levylle tietokantaan käyttäen h2-tietokannanhallintajärjestelmää.

## Perusversion toiminnallisuus
Käyttäjä voi
* Lisätä järjestelmään uuden ajoneuvon. Ajoneuvosta syötetään rekisteritunnus ja ajokilometrit.
* Lisätä uuden ajotapahtuman. Tapahtumaan syötetään matkamittarin lukema ajon päättyessä, ajon tarkoitus ja kuljettaja.
* Listata ajoneuvon tapahtumahistorian.
* Hakea ajotapahtumia vapaalla sanahaulla, eli esimerkiksi ajon tyypin tai laskutettavan asiakkaan perusteella. Ajoista lasketaan haun yhteydessä myös kilometrien summa.

## Jatkokehitys

Jatkossa sovellukseen voidaan lisätä myös esimerkiksi seuraavat toiminnallisuudet:
* Kirjautuminen
* Käyttäjäroolien lisääminen
* Tapahtuma-ajan muokkausmahdollisuus, alku- ja loppuaika erikseen
* Tapahtuman muokkausmahdollisuus jälkikäteen, esim. virheellisen syötön korjaus
* Haun monipuolistaminen (esim. kuljettajan perusteella)
* Alias-nimen lisääminen ajoneuvolle (käytettävyyden kannalta helpompi muistaa kuin rekisteritunnus)
* Tulevien tapahtumien (kuten katsastukset tai määräaikaishuollot) kirjaaminen
* Tulevista tapahtumista muistuttaminen




