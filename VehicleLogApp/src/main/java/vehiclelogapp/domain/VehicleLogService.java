package vehiclelogapp.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import vehiclelogapp.dao.DaoService;
import vehiclelogapp.dao.EntryDao;
import vehiclelogapp.dao.VehicleDao;

/**
 * Sovelluslogiikasta vastaava luokka.
 *
 */
public class VehicleLogService {

    private VehicleDao vehicleDao;
    private EntryDao entryDao;
    private DaoService daoService;

    public VehicleLogService(String database, String user, String pw) {

        this.entryDao = new EntryDao(database, user, pw);
        this.vehicleDao = new VehicleDao(database, user, pw);
        this.daoService = new DaoService(database, user, pw);

    }

    /**
     * Lisää ajoneuvon järjestelmään. Lisäyksen jälkeen metodi tekee ensimmäisen
     * tapahtumasyötön.
     *
     * @see vehiclelogapp.domain.VehicleLogService#addEntry(java.lang.String,
     * int, java.lang.String, java.lang.String)
     * @param licensePlate Lisättävän ajoneuvon rekisterinumero
     * @param kilometers Matkamittarin lukema
     * @return true, mikäli ajoneuvon lisääminen onnistuu, muuten false.
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public boolean addVehicle(String licensePlate, int kilometers) throws SQLException {
        if (isNotValid(licensePlate)) {
            return false;
        }
        licensePlate = licensePlate.toUpperCase().trim();
        Integer key = vehicleDao.getVehicleId(licensePlate);
        if (key != null) {
            return false;
        }
        Vehicle vehicleToAdd = new Vehicle(licensePlate, kilometers);
        Vehicle v = vehicleDao.create(vehicleToAdd);
        if (v == null) {
            return false;
        } else {
            addEntry(licensePlate, kilometers, "admin", "");
        }
        return true;
    }

    /**
     * Lisää järjestelmässä olevalle ajoneuvolle tapahtuman.
     *
     * @param licensePlate Ajoneuvon rekisteritunnus
     * @param km Matkamittarin lukema
     * @param driver Kuljettajan nimi
     * @param entryType Tapahtumaan liittyvä selite
     * @return true, mikäli tapahtuman lisääminen onnistui, muuten false.
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public boolean addEntry(String licensePlate, int km, String driver, String entryType) throws SQLException {
        if (isNotValid(licensePlate)) {
            return false;
        }
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        licensePlate = licensePlate.toUpperCase().trim();

        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        if (vehicleId == null) {
            return false;
        }
        int lastOdom = entryDao.latestOdometerForVehicle(vehicleId);
        int sum = km - lastOdom;
        String dr = driver.toUpperCase().trim();
        Entry entryToAdd = new Entry(vehicleId, km, date, dr, entryType.toUpperCase(), sum);
        Entry e = entryDao.create(entryToAdd);

        if (e == null) {
            return false;
        }
        return true;
    }

    /**
     * Hakee järjestemästä kaikki sinne syötetyt ajoneuvot.
     *
     * @return Palauttaa ajoneuvojen rekisteritunnukset listana.
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public ArrayList<String> listVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = vehicleDao.list();
        ArrayList<String> queryResults = new ArrayList<>();
        vehicles.forEach((v) -> {
            queryResults.add(v.toString());
        });

        return queryResults;
    }

    /**
     * Hakee järjestelmästä kaikki yhdelle ajoneuvolle syötetyt tapahtumat.
     * Muotoillaan Entry-luokan toString()-metodilla.
     *
     * @see vehiclelogapp.domain.Entry#toString()
     * @param licensePlate Ajoneuvon rekisteritunnus
     * @return Palauttaa kaikki tapahtumat listana.
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public ArrayList<String> listEntriesForVehicle(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();
        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        if (vehicleId == null) {
            return null;
        }

        ArrayList<Entry> entries = entryDao.listEntriesForVehicle(vehicleId);
        ArrayList<String> queryResults = new ArrayList<>();
        entries.forEach((e) -> {
            queryResults.add(e.toString());
        });
        return queryResults;
    }

    /**
     * Hakee tapahtumat hakusanalla.
     *
     * @param key Hakusana
     * @return Lista tapahtumista sekä yhteenlaskettu matkojen summa.
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public ArrayList<String> searchEntries(String key) throws SQLException {
        if (isNotValid(key)) {
            return new ArrayList<>();
        }
        String searchKey = key.toUpperCase();
        ArrayList<Entry> entries = entryDao.listEntriesByType(searchKey);
        if (entries.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<String> results = new ArrayList<>();
        int sum = 0;
        for (Entry e : entries) {
            results.add(e.toString());
            sum = sum + e.getLastTrip();
        }
        String totalKm = "Kilometrejä yhteensä: " + sum;
        results.add(totalKm);
        return results;
    }

    /**
     * Hakee ajoneuvon viimeisimmän matkamittarin lukeman.
     *
     * @param licensePlate Haettavan ajoneuvon rekisteritunnus.
     * @return Kilometrilukema
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public int getLatestOdometer(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();
        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        if (vehicleId != null) {
            int result = entryDao.latestOdometerForVehicle(vehicleId);
            return result;
        }
        return 0;
    }

    /**
     * Apumetodi, joka tarkistaa, että ajoneuvo on syötetty järjestelmään.
     *
     * @param licensePlate Tarkistettavan ajoneuvon rekisteritunnus
     * @return true, mikäli ajoneuvo löytyy, muuten false.
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public boolean vehicleExists(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();
        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);

        if (vehicleId == null) {
            return false;
        }

        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static boolean isNotValid(String s) {
        return (s.isEmpty() || s.equals(" "));
    }
}
