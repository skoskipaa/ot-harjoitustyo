
package vehiclelogapp.ui;

import java.util.Scanner;
import vehiclelogapp.domain.VehicleLogService;


public class Main {
    
    public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        VehicleLogService service = new VehicleLogService();
        
        TextInterface ui = new TextInterface(reader, service);
        ui.startApplication();
        
       
    }
    
    
}
