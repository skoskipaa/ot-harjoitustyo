# Käyttöohje

## Asennus ja konfigurointi

Lataa tiedosto [vehiclelogapp.jar](https://github.com/skoskipaa/ot-harjoitustyo/releases/tag/viikko6).
Käynnistyshakemistossa tulee olla myös tiedosto [config.properties](https://github.com/skoskipaa/ot-harjoitustyo/releases/tag/viikko6), jossa määritellään käytettävä tietokantatiedosto. Oletustiedoston sisältö on seuraava:

database=jdbc:h2:./logbook  
user=sa  
password=  

Tietokantatiedoston nimi on siis oletuksena *logbook*, käyttäjänimi *sa* ja salasana tyhjä. Nämä voi halutessaan määritellä konfigurointitiedostoon itse. Mikäli käyttäjänimeä tai salasanaa muutetaan tiedostossa tietokannan luomisen jälkeen, ohjelman suoritus keskeytyy. Mikäli tiedostoon määritellään uusi tiedostonnimi, luo sovellus uuden tiedoston käynnistettäessä.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komentoriviltä komennolla  
**java -jar VehicleLogApp.jar**


## Päävalikko

Ohjelma käynnistyy päävalikkoon. Päävalikosta voidaan valita joko uuden ajoneuvon syöttö järjestelmään, uuden tapahtuman syöttö järjestelmässä jo olevalle ajoneuvolle, tapahtumien haku, ajoneuvon tapahtumien listaus tai kaikkien järjestelmän ajoneuvojen listaus.

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/paavalikko.png">

Kun halutaan listata tietyn ajoneuvon tapahtumat, valitaan ajoneuvo alasvetovalikosta ja painetaan nappia "Listaa ajoneuvon tapahtumat". Tapahtumat tulostuvat uuteen ikkunaan.

Kun ohjelma suljetaan "Lopeta"-napista, auki olevat ikkunat sulkeutuvat.

## Ajoneuvon syöttö

Päävalikosta siirrytään ajoneuvon syöttöön "Lisää ajoneuvo"-napilla.

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/anlisays.png">

Ajoneuvon syöttönäkymässä annetaan lisättävän ajoneuvon rekisteritunnus sekä matkamittarin lukema syöttöhetkellä. Cancel-painike peruu syöttötapahtuman ja palaa aloitusnäkymään.

## Tapahtuman syöttö

Tapahtuman syöttöön siirrytään "Lisää tapahtuma"-napilla.

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/tapsyotto.png">

Alasvetovalikosta valitaan ajoneuvo, jolle tapahtuma halutaan syöttää. Ajoneuvon viimeisimmän matkamittarilukeman saa näkyviin "Näytä ajoneuvon tiedot"-painikkeella. Oikean yläkulman alasvetovalikosta voidaan valita tapahtumalle tyyppi, joka on oletuksena "AJO". Tekstikenttiin annetaan matkamittarin lukema (suurempi kuin edellinen), kuljettajan nimi sekä vapaa selite. Tämän kentän perusteella voidaan tehdä hakuja tapahtumista. Cancel peruu tapahtuman syötön ja palaa aloitusnäkymään.

## Tapahtumien haku

Tapahtumia voidaan hakea vapaalla tekstihaulla tapahtumatyyppiin liittyen. Haettava teksti kirjoitetaan hakukenttään ja hakutulokset ilmestyvät uuteen ikkunaan.

<img src="https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kuvat/haku2.png">


