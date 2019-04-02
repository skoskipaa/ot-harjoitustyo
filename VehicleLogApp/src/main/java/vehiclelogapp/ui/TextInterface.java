package vehiclelogapp.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import vehiclelogapp.domain.*;

public class TextInterface {

    private Scanner reader;
    private VehicleLogService service;

    public TextInterface(Scanner lukija, VehicleLogService service) {
        this.reader = lukija;
        this.service = service;     

    }

    public void startApplication() throws SQLException {
        System.out.println("Tervetuloa!");
        System.out.println("Ajopäiväkirja\n");

        while (true) {
            System.out.println("");
            System.out.println("1...Syötä uusi auto");
            System.out.println("2...Syötä tapahtuma");
            System.out.println("3...Listaa autot");
            System.out.println("4...Listaa auton tapahtumat");
            System.out.println("x...Lopeta");

            String komento = reader.nextLine();
            if (komento.equals("x")) {
                break;
            }

            if (komento.equals("1")) {
                addVehicle();
            }

            if (komento.equals("2")) {
                addEntry();
            }

            if (komento.equals("3")) {
                listVehicles();
            }
            
            if (komento.equals("4")) {
                listEntriesForVehicle();
            }
                

        }

        System.out.println("Hei hei!");

    }
    public void addVehicle() throws SQLException {
        
        System.out.println("");
        System.out.print("Anna rekisteritunnus: ");
        String licencePlate = reader.nextLine();
        System.out.print("Anna aloituskilometrit: ");
        int km = Integer.parseInt(reader.nextLine());
        
        boolean add = service.addVehicle(licencePlate, km);
        if (!add) {
            System.out.println("Auton lisääminen ei onnistunut, tarkista tunnus.");
        }
    }
    
    public void addEntry() throws SQLException {
        
        System.out.println("");
        System.out.print("Mille autolle lisätään? ");
        String licensePlate = reader.nextLine();
        System.out.print("Anna matkamittarin lukema ajon päättyessä: ");
        int kilometers = Integer.parseInt(reader.nextLine());
        System.out.print("Anna kuljettaja: ");
        String driver = reader.nextLine();
        System.out.print("Anna ajon tarkoitus/kustantaja: ");
        String type = reader.nextLine();
        
        
        boolean add = service.addEntry(licensePlate, kilometers, driver, type);
        if (!add) {
            System.out.println("Tapahtuman lisääminen ei onnistunut, tarkista tunnus.");
        }
    }
    
    public void listVehicles() throws SQLException {
        
        ArrayList<Vehicle> vehicles = service.listVehicles();
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
    
    public void listEntriesForVehicle() throws SQLException {
        System.out.println("");
        System.out.print("Minkä auton tapahtumat listataan? ");
        String licensePlate = reader.nextLine();
        
        ArrayList<Entry> entries = service.listEntriesForVehicle(licensePlate);
        if (entries == null) {
            System.out.println("Autoa ei löytynyt!");
            return;
        }
        for (Entry e : entries) {
            System.out.println(e);
        }
        
    }

}
