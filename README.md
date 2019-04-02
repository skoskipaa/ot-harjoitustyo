# VehicleLogApp
Sovelluksen avulla on mahdollista ylläpitää ajopäiväkirjaa usealle ajoneuvolle. Sovellukseen voi lisätä ajoneuvoja ja ajotapahtumia sekä listata näitä.

Sovellus käyttää h2-tietokannanhallintajärjestelmää ja sen tietokanta sijaitsee tiedostossa logbook.mv.db. Tietokannan voi alustaa uudelleen suorittamalla Main-luokassa sijaitsevan setUpDatabase()-metodin.


## Dokumentaatio
[Työaikakirjanpito](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

## Komentorivitoiminnot

Testit voidaan ajaa komennolla
*mvn test*

Testikattavuusraportti on mahdollista luoda komennolla
*mvn test jacoco:report*

Ohjelma voidaan suorittaa komentoriviltä komennolla
*mvn compile exec:java -Dexec.mainClass=vehiclelogapp.ui.Main*


