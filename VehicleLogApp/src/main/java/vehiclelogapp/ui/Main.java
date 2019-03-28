
package vehiclelogapp.ui;

import java.util.Scanner;
import vehiclelogapp.domain.*;


public class Main {
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        System.out.println("Who R U?");
        String name = reader.nextLine();
        System.out.println("Hello " + name ) ;
    }
    
    
}
