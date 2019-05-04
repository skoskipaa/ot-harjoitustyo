# VehicleLogApp
Sovelluksen avulla on mahdollista ylläpitää ajopäiväkirjaa usealle ajoneuvolle. Sovellukseen voi lisätä ajoneuvoja ja ajotapahtumia sekä listata näitä.

Sovellus käyttää h2-tietokannanhallintajärjestelmää ja luo sitä varten tietokannan tiedostoon, joka määritellään konfiguraatiotiedostossa.

## Dokumentaatio
[Käyttöohje](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Työaikakirjanpito](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/skoskipaa/ot-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md)

## Releaset

[Viikko 5](https://github.com/skoskipaa/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/skoskipaa/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Testaus

Testit voidaan suorittaa komennolla  
**mvn test**  

Testikattavuusraportti on mahdollista luoda komennolla  
**mvn test jacoco:report**  
Raportti löytyy tiedostosta *target/site/jacoco/index.html*

### Checkstyle

Checkstyle voidaan suorittaa komennolla  
**mvn jxr:jxr checkstyle:checkstyle**  
Raportti löytyy tiedostosta *target/site/checkstyle.html*

### JavaDoc

JavaDoc voidaan luoda komennolla  
**mvn javadoc:javadoc**  
Luotu JavaDoc löytyy tiedostosta *target/site/apidocs/index.html*

### Ohjelman suoritus

Ohjelma voidaan suorittaa komentoriviltä komennolla  
**mvn compile exec:java -Dexec.mainClass=vehiclelogapp.ui.Main**  

Suoritettava jar-tiedosto (VehicleLogApp-1.0-SNAPSHOT.jar) luodaan target-hakemistoon komennolla  
**mvn package**  
Suoritushakemistosta tulee löytyä myös tiedosto *config.properties*, joka sisältää tietokantatiedoston määrittelyn.



