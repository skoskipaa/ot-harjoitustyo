# VehicleLogApp
Sovelluksen avulla on mahdollista ylläpitää ajopäiväkirjaa usealle ajoneuvolle. Sovellukseen voi lisätä ajoneuvoja ja ajotapahtumia sekä listata näitä.

Sovellus käyttää h2-tietokannanhallintajärjestelmää ja sen tietokanta luodaan tiedostoon logbook.mv.db.

## Dokumentaatio
[Työaikakirjanpito](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/skoskipaa/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

Testit voidaan ajaa komennolla
*mvn test*

Testikattavuusraportti on mahdollista luoda komennolla
*mvn test jacoco:report*

Checkstyle voidaan suorittaa komennolla
*mvn jxr:jxr checkstyle:checkstyle*

Ohjelma voidaan suorittaa komentoriviltä komennolla
*mvn compile exec:java -Dexec.mainClass=vehiclelogapp.ui.Main*

Suoritettava jar-tiedosto (VehicleLogApp-1.0-SNAPSHOT.jar) luodaan target-hakemistoon komennolla
*mvn package*


