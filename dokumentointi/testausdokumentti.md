# Testausdokumentti
Sovellusta on testattu automaattisin JUnit-yksikkötestein ja integraatiotestein sekä manuaalisesti järjestelmätestein.

## Yksikkö- ja integraatiotestaus

### Tietokannan testaus
DAO-luokkien eli tietokantasyöttöjen ja -lukujen toimintaa testattiin h2-tietokannanhallintajärjestelmän mahdollistamalla muistiin luodulla tilapäisellä tietokannalla.

### Sovelluslogiikan testaus


### Testauskattavuus

## Järjestelmätestaus


### Asennus ja konfigurointi
Sovelluksen asennusta ja konfigurointia on testattu OSX-ympäristössä käyttöohjeessa esitetyllä tavalla asentamalla sovellus ja sen vaatima config.properties-tiedosto. Sovellusta on testattu ja se toimii tilanteissa, joissa tietokantatiedostoa ei ole vielä olemassa (ensimmäinen käynnistyskerta) sekä tilanteissa, joissa tietokantatiedosto on olemassa.

### Toiminnallisuudet
Toiminnallisuuksia on testattu laajasti erilaisin syöttein manuaalisesti sekä automaattisin testein. Syötteeksi on annettu sekä sallittuja että epäkelpoja syötteitä kuten tyhjiä kenttiä tai kirjaimia numerokenttiin. Sovellus myös hylkää tapahtumasyötön syötteet, joissa kilometrilukema on pienempi kuin aikaisemman saman ajoneuvon tapahtuman.

Kaikki vaatimusmäärittelyssä ja käyttöohjeessa määritellyt toiminnallisuudet on testattu sekä manuaalisesti että yksikkötestein.

## Sovellukseen jääneet laatuongelmat

Varsinaisia bugeja eli ohjelman luotettavuuteen ja toimintaan liittyviä ongelmia sovelluksesta ei ole testauksessa löytynyt.

Yksikkötesti, joka testasi viimeisimmän kilometrilukeman oikeellisuutta ajoneuvon syötön ja tapahtuman syötön jälkeen ei jostain syystä onnistunut, ts. sen suoritus ei mennyt läpi. Koska toiminnallisuus kuitenkin todettiin manuaalisin testein varmaksi, todennäköisin syy testin toimimattomuuteen lienee testien kirjoittajan kokemattomuus tietokantatestauksesta.


