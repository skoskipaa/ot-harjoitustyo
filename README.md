# VehicleLogApp
Sovelluksen avulla on mahdollista ylläpitää ajopäiväkirjaa usealle ajoneuvolle. Sovellukseen voi lisätä ajoneuvoja ja ajotapahtumia sekä listata näitä.

Sovellus käyttää h2-tietokannanhallintajärjestelmää ja sen tietokanta sijaitsee tiedostossa logbook.mv.db. Tietokannan voi tämänhetkisessä versiossa tyhjentää ja alustaa uudelleen painamalla tietokannanalustusnappia käyttöliittymässä. Päänäytöllä syötetään ensin ajoneuvon tunnus ja valitaan sitten haluttu toiminto. Kaikki järjestelmässä olevat ajoneuvot saa myös listattua napista.


## Dokumentaatio
[Työaikakirjanpito](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)


## Komentorivitoiminnot

Testit voidaan ajaa komennolla
*mvn test*

Testikattavuusraportti on mahdollista luoda komennolla
*mvn test jacoco:report*

Checkstyle voidaan suorittaa komennolla
*mvn jxr:jxr checkstyle:checkstyle*

Ohjelma voidaan suorittaa komentoriviltä komennolla
*mvn compile exec:java -Dexec.mainClass=vehiclelogapp.ui.Main*


