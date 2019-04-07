package vehiclelogapp.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vehiclelogapp.domain.VehicleLogService;

public class GraphicInterface extends Application {

    private Scene mainScene;
    private Scene addVehicle;
    private Scene addEntry;
    private Scene listEntries;
    private VehicleLogService service;
    private String lpText;

    @Override
    public void init() {
        service = new VehicleLogService();

    }

    @Override
    public void start(Stage stage) throws Exception {
        // main scene components
        TextField licensePlate = new TextField();
        licensePlate.setMaxWidth(150);
        Button vehicleButton = new Button("Lisää ajoneuvo");
        Button entryButton = new Button("Lisää tapahtuma");
        Button listVehiclesBtn = new Button("Listaa kaikki ajoneuvot");
        Button listEntriesBtn = new Button("Listaa ajoneuvon tapahtumat");
        Button exit = new Button("Lopeta");
        Label greetingText = new Label("Tervetuloa! Anna rekisteritunnus ja valitse toiminto");
        Label errorMessageMain = new Label();

        VBox welcome = new VBox();
        welcome.getChildren().add(greetingText);
        welcome.getChildren().add(licensePlate);
        welcome.getChildren().add(errorMessageMain);
        welcome.getChildren().add(vehicleButton);
        welcome.getChildren().add(entryButton);
        welcome.getChildren().add(listVehiclesBtn);
        welcome.getChildren().add(listEntriesBtn);
        welcome.getChildren().add(exit);
        welcome.setPrefSize(500, 300);
        welcome.setPadding(new Insets(20, 20, 20, 20));
        welcome.setSpacing(20);
        welcome.setAlignment(Pos.CENTER);
        mainScene = new Scene(welcome);

        // "add vehicle" scene components               
        Label plate = new Label();
        Label odometer = new Label("Anna matkamittarin aloituslukema");
        TextField odoField = new TextField();
        odoField.setMaxWidth(150);
        Label errorMsgVehicleScene = new Label();
        Button submitVehicle = new Button("Lisää ajoneuvo järjestelmään");
        Button btnCancelVehicle = new Button("Cancel");

        VBox vehicle = new VBox();
        vehicle.getChildren().add(plate);
        vehicle.getChildren().add(odometer);
        vehicle.getChildren().add(odoField);
        vehicle.getChildren().add(errorMsgVehicleScene);
        vehicle.getChildren().add(submitVehicle);
        vehicle.getChildren().add(btnCancelVehicle);
        vehicle.setPrefSize(500, 300);
        vehicle.setPadding(new Insets(20, 20, 20, 20));
        vehicle.setSpacing(20);
        vehicle.setAlignment(Pos.CENTER);
        addVehicle = new Scene(vehicle);

        // "add entry" scene components
        Label msg = new Label();
        Label kilometers = new Label("Anna matkamittarin lukema");
        TextField km = new TextField();
        km.setMaxWidth(150);
        Label driver = new Label("Anna kuljettajan nimi");
        TextField dr = new TextField();
        dr.setMaxWidth(150);
        Label type = new Label("Anna selite tai asiakas");
        TextField ty = new TextField();
        Label errorMsgEntryScene = new Label();
        Button submitEntry = new Button("Tallenna");
        Button btnCancelEntry = new Button("Cancel");

        VBox entry = new VBox();
        entry.getChildren().add(msg);
        entry.getChildren().add(kilometers);
        entry.getChildren().add(km);
        entry.getChildren().add(driver);
        entry.getChildren().add(dr);
        entry.getChildren().add(type);
        entry.getChildren().add(ty);
        entry.getChildren().add(errorMsgEntryScene);
        entry.getChildren().add(submitEntry);
        entry.getChildren().add(btnCancelEntry);
        entry.setPrefSize(500, 300);
        entry.setPadding(new Insets(20, 20, 20, 20));
        entry.setSpacing(20);
        entry.setAlignment(Pos.CENTER);
        addEntry = new Scene(entry);

        // secondary (query result) window components
        StackPane resultWndwLayout = new StackPane();
        ListView<String> list = new ListView<>();
        resultWndwLayout.getChildren().addAll(list);
        Scene scndWndw = new Scene(resultWndwLayout, 600, 200);
        Stage resultWindow = new Stage();
        ObservableList<String> data = FXCollections.observableArrayList();

        // buttons + actions
        // siirry auton lisäysnäkymään
        vehicleButton.setOnAction(event -> {
            try {
                lpText = licensePlate.getText();

                if (service.vehicleExists(lpText)) {
                    errorMessageMain.setText("Tunnuksella löytyy jo ajoneuvo!");
                } else {
                    licensePlate.clear();
                    errorMessageMain.setText("");
                    plate.setText("Ajoneuvo: " + lpText);
                    stage.setScene(addVehicle);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // siirry tapahtuman lisäysnäkymään
        entryButton.setOnAction(event -> {
            try {
                lpText = licensePlate.getText();
                int reading = service.getLatestOdometer(lpText);
                if (reading == 0) {
                    errorMessageMain.setText("Autoa ei löydy!");
                } else {

                    licensePlate.clear();
                    errorMessageMain.setText("");
                    String odo = Integer.toString(reading);
                    msg.setText("Ajoneuvo: " + lpText.toUpperCase() + ", matkamittarin edellinen lukema: " + odo);

                    stage.setScene(addEntry);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // listaa autot (uuteen ikkunaan)
        listVehiclesBtn.setOnAction(event -> {
            try {

                data.clear();
                ArrayList<String> query = service.listVehicles();
                query.forEach((s) -> {
                    data.add(s);
                });
                list.setItems(data);
                resultWindow.setTitle("Ajoneuvot");
                resultWindow.setScene(scndWndw);
                resultWindow.show();
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // listaa auton tapahtumat (uuteen ikkunaan)
        listEntriesBtn.setOnAction(event -> {
            try {
                String lp = licensePlate.getText();
                ArrayList<String> entryQuery = service.listEntriesForVehicle(lp);

                if (entryQuery == null) {
                    errorMessageMain.setText("Autoa ei löydy!");
                } else {
                    data.clear();
                    errorMessageMain.setText("");
                    entryQuery.forEach((s) -> {
                        data.add(s);
                    });
                    list.setItems(data);
                    resultWindow.setTitle(licensePlate.getText().toUpperCase());
                    resultWindow.setScene(scndWndw);
                    resultWindow.show();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // lopeta
        exit.setOnAction(event -> {
            resultWindow.close();
            stage.close();
        });

        // lisää auto tietokantaan              
        submitVehicle.setOnAction(event -> {
            try {
                String odometerField = odoField.getText();
                if (!isInteger(odometerField)) {
                    errorMsgVehicleScene.setText("Tarkista numero!");
                } else {
                    int odom = Integer.parseInt(odometerField);
                    boolean addToDatabase = service.addVehicle(lpText, odom);
                    if (!addToDatabase) {
                        errorMsgVehicleScene.setText("Lisäys epäonnistui, tarkista tiedot");
                    } else {
                        service.addEntry(lpText, odom, "admin", "aloitussyöttö");
                        odoField.clear();
                        errorMsgVehicleScene.setText("");
                        stage.setScene(mainScene);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // peru ajoneuvon lisäys
        btnCancelVehicle.setOnAction(event -> {
            odoField.clear();
            stage.setScene(mainScene);
        });

        // lisää tapahtuma tietokantaan         
        submitEntry.setOnAction(event -> {
            String currentOdometer = km.getText();
            String driverName = dr.getText();
            String typeOfJourney = ty.getText();

            if (!isInteger(currentOdometer)) {
                errorMsgEntryScene.setText("Tarkista matkamittarin lukema!");
            } else {

                try {
                    int odomCurrent = Integer.parseInt(currentOdometer);
                    if (odomCurrent < service.getLatestOdometer(lpText)) { 
                        errorMsgEntryScene.setText("Matkamittarin lukema on liian pieni!");
                    } else {
                        
                        service.addEntry(lpText, odomCurrent, driverName, typeOfJourney);
                        km.clear();
                        dr.clear();
                        ty.clear();
                        stage.setScene(mainScene);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // peru ajoneuvon lisäys
        btnCancelEntry.setOnAction(event -> {
            km.clear();
            dr.clear();
            ty.clear();
            stage.setScene(mainScene);
        });
        
        // start
        stage.setScene(mainScene);
        stage.setTitle("LogBookApp");
        stage.show();

    }

    // Santa's little helpers...
    
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

}
