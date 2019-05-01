# Testausdokumentti
Sovellusta on testattu automaattisin JUnit-yksikkötestein ja integraatiotestein sekä manuaalisesti järjestelmätestein.

## Yksikkö- ja integraatiotestaus

### Tietokannan testaus
DAO-luokkien eli tietokantasyöttöjen ja -lukujen toimintaa testattiin h2-tietokannanhallintajärjestelmän mahdollistamalla muistiin luodulla tilapäisellä tietokannalla.

### Sovelluslogiikan testaus
Sovelluslogiikkaa testattiin integraatiotestein luokalla VehicleLogServiceTest. Luokalla testattiin sovelluslogiikan toteuttamia perustoiminnallisuuksia kuten uuden ajoneuvon ja tapahtuman syöttöä järjestelmään VehicleLogService-olion kautta.

EntryTest ja VehicleTest sisältävät yksikkötestejä luokille Entry ja Vehicle.

### Testauskattavuus
Sovelluksen testauksen rivikattavuus on 89% ja haarautumakattavuus 78%. Käyttöliittymäkerros jätettiin pois automaattisen testauksen piiristä, eli käyttöliittymää testattiin manuaalisesti.

Koska DAO-luokkia testattiin sovelluslogiikan kautta eikä suoraan, testaamatta jäi haarautumia, jotka olisivat johtuneet esimerkiksi tietokantasyötön epäonnistumisesta tai tietokannan virheellisestä luomisesta.




## Järjestelmätestaus
Järjestelmätestaus on toteutettu manuaalisesti. Järjestelmätestauksessa testattiin ohjelman käytettävyyttä, luotettavuutta ja syötteiden validointia.

### Asennus ja konfigurointi
Sovelluksen asennusta ja konfigurointia on testattu OSX-ympäristössä käyttöohjeessa esitetyllä tavalla asentamalla sovellus ja sen vaatima config.properties-tiedosto. Sovellusta on testattu ja se toimii tilanteissa, joissa tietokantatiedostoa ei ole vielä olemassa (ensimmäinen käynnistyskerta) sekä tilanteissa, joissa tietokantatiedosto on olemassa.

### Toiminnallisuudet
Toiminnallisuuksia on testattu laajasti erilaisin syöttein manuaalisesti sekä automaattisin testein. Syötteeksi on annettu sekä sallittuja että epäkelpoja syötteitä kuten tyhjiä kenttiä tai kirjaimia numerokenttiin. Sovellus myös hylkää tapahtumasyötön syötteet, joissa kilometrilukema on pienempi kuin aikaisemman saman ajoneuvon tapahtuman.

Kaikki vaatimusmäärittelyssä ja käyttöohjeessa määritellyt toiminnallisuudet on testattu sekä manuaalisesti että yksikkötestein.

## Sovellukseen jääneet laatuongelmat

Varsinaisia bugeja eli ohjelman luotettavuuteen ja toimintaan liittyviä ongelmia sovelluksesta ei ole testauksessa löytynyt.

Testi, joka testasi viimeisimmän kilometrilukeman oikeellisuutta ajoneuvon syötön ja tapahtuman syötön jälkeen ei jostain syystä onnistunut, ts. sen suoritus ei mennyt läpi. Koska toiminnallisuus kuitenkin todettiin manuaalisin testein varmaksi, todennäköisin syy testin toimimattomuuteen lienee testien kirjoittajan kokemattomuus tietokantatestauksesta ja testi poistettiin sovelluksesta.


