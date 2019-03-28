package vehiclelogapp.ui;

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

    public void startApplication() {
        System.out.println("Tervetuloa!");
        System.out.println("Ajopäiväkirja\n");

        while (true) {
            System.out.println("");
            System.out.println("1...Syötä uusi auto");
            System.out.println("2...Syötä tapahtuma");
            System.out.println("3...Listaa auton tapahtumat");
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
                list();
            }

        }

        System.out.println("Hei hei!");

    }
    public void addVehicle() {
        
        System.out.println("");
        System.out.print("Anna rekisteritunnus (ilman viivaa): ");
        String licencePlate = reader.nextLine();
        System.out.print("Anna aloituskilometrit: ");
        int km = Integer.parseInt(reader.nextLine());
        
        boolean add = service.addVehicle(licencePlate, km);
        if (!add) {
            System.out.println("Auto on jo olemassa! Ei tehty mitään.");
        }
    }
    
    public void addEntry() {
        System.out.println("");
        System.out.print("Mille autolle lisätään? ");
        String tunnus = reader.nextLine();
        System.out.print("Anna kilometrit: ");
        int kilometrit = Integer.parseInt(reader.nextLine());
        System.out.print("Anna kuljettaja: ");
        String kuski = reader.nextLine();
        System.out.print("Anna ajon tarkoitus/kustantaja: ");
        String tarkoitus = reader.nextLine();
        
        boolean tapahtuma = service.addEntry(tunnus, kilometrit, kuski, tarkoitus);
        if (!tapahtuma) {
            System.out.println("Autoa ei löytynyt, yritä uudelleen!");
        }
    }
    
    public void list() {
        System.out.println("Minkä auton tapahtumat tulostetaan?");
        String licensePlate = reader.nextLine();
        ArrayList<Entry> tuloste = service.list(licensePlate);
        
        for (Entry e : tuloste) {
            System.out.println(e.toString());
        }
        
    }

}
