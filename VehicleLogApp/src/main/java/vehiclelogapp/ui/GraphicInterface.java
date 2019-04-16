package vehiclelogapp.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vehiclelogapp.domain.VehicleLogService;

public class GraphicInterface extends Application {

    private VehicleLogService service;
    private Scene mainScene;
    private Scene addVehicle;
    private Scene addEntry;
    private Scene listEntries;
    private String chooseVehicleEntry;
    private ObservableList<String> vehiclesMenuItems;

    @Override
    public void init() {
        
        service = new VehicleLogService();
        vehiclesMenuItems = FXCollections.observableArrayList();
       

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Valikot
        //ObservableList<String> vehiclesMenuItems = FXCollections.observableArrayList();
        ComboBox cBVehicleMenu = new ComboBox();
        cBVehicleMenu.setVisibleRowCount(5);
        cBVehicleMenu.setPromptText("Ajoneuvo");
        ComboBox cBEntryMenu = new ComboBox();
        cBEntryMenu.setVisibleRowCount(5);
        cBEntryMenu.setPromptText("Ajoneuvo");

        setMenus();

        cBVehicleMenu.setItems(vehiclesMenuItems);
        cBEntryMenu.setItems(vehiclesMenuItems);

        // Aloitusnäkymän komponentit
        Button addVehicleBtn = new Button("Lisää ajoneuvo");
        Button addEntryBtn = new Button("Lisää tapahtuma");
        Button listVehiclesBtnMain = new Button("Listaa kaikki ajoneuvot");
        Button listEntriesBtnMain = new Button("Listaa ajoneuvon tapahtumat");
        Button exitBtn = new Button("Lopeta");
        Label greetingText = new Label("Tervetuloa! Valitse toiminto!");

        // Ajoneuvosyötön komponentit
        Label plateLbl = new Label("Anna rekisteritunnus");
        TextField plateField = new TextField();
        plateField.setMaxWidth(150);
        Label odometerLbl = new Label("Anna matkamittarin aloituslukema");
        TextField odoFieldVehicleScene = new TextField();
        odoFieldVehicleScene.setMaxWidth(150);
        Label errorMsg = new Label();
        Button submitVehicleBtn = new Button("Lisää ajoneuvo järjestelmään");
        Button cancelVehicleBtn = new Button("Cancel");

        // Tapahtumasyötön komponentit
        Label msg = new Label();        // Edelliset kilometrit, rekkari
        Label kmLbl = new Label("Anna matkamittarin lukema");
        TextField odoFieldEntryScene = new TextField();
        odoFieldEntryScene.setMinWidth(300);
        Label driverLbl = new Label("Anna kuljettajan nimi");
        TextField driverField = new TextField();
        driverField.setMinWidth(300);
        Label typeLbl = new Label("Anna selite tai asiakas");
        TextField typeField = new TextField();
        typeField.setMinWidth(300);
        Button submitEntryBtn = new Button("Tallenna");
        Button cancelEntryBtn = new Button("Cancel");
        Button selectVehicleForEntry = new Button("Näytä ajoneuvon tiedot");

        // Päänäkymä
        BorderPane mainLayout = new BorderPane();
        HBox btnsMainBox = new HBox();
        btnsMainBox.setPadding(new Insets(20, 20, 20, 20));
        btnsMainBox.setSpacing(20);
        HBox listAndChoiceBox = new HBox();
        listAndChoiceBox.setPadding(new Insets(20, 20, 20, 20));
        listAndChoiceBox.setSpacing(5);
        HBox quitBox = new HBox();
        quitBox.setPadding(new Insets(20, 20, 20, 20));

        btnsMainBox.getChildren().addAll(addVehicleBtn, addEntryBtn, listVehiclesBtnMain);
        listAndChoiceBox.getChildren().addAll(listEntriesBtnMain, cBVehicleMenu);
        quitBox.getChildren().add(exitBtn);

        mainLayout.setTop(btnsMainBox);
        mainLayout.setCenter(listAndChoiceBox);
        mainLayout.setBottom(quitBox);
        mainLayout.setPadding(new Insets(20, 20, 20, 20));
        
        Scene mainScene = new Scene(mainLayout);
        

        // Auton syöttö
        GridPane addVehicle = new GridPane();
        addVehicle.add(plateLbl, 0, 0);
        addVehicle.add(plateField, 1, 0);
        addVehicle.add(odometerLbl, 0, 1);
        addVehicle.add(odoFieldVehicleScene, 1, 1);
        addVehicle.add(submitVehicleBtn, 0, 2);
        addVehicle.add(cancelVehicleBtn, 1, 2);
        addVehicle.add(errorMsg, 0, 3);
        addVehicle.setPrefSize(400, 200);
        addVehicle.setPadding(new Insets(20, 20, 20, 20));
        addVehicle.setVgap(10);
        addVehicle.setHgap(10);
        Scene vehicleScene = new Scene(addVehicle);

        // Tapahtuman syöttö
        GridPane addEntry = new GridPane();
        addEntry.add(cBEntryMenu, 0, 0);
        addEntry.add(selectVehicleForEntry, 1, 0);
        addEntry.add(msg, 1, 1);
        addEntry.add(kmLbl, 0, 2);
        addEntry.add(odoFieldEntryScene, 1, 2);
        addEntry.add(driverLbl, 0, 3);
        addEntry.add(driverField, 1, 3);
        addEntry.add(typeLbl, 0, 4);
        addEntry.add(typeField, 1, 4);
        addEntry.add(submitEntryBtn, 0, 5);
        addEntry.add(cancelEntryBtn, 1, 5);
        addEntry.setPrefSize(600, 200);
        addEntry.setPadding(new Insets(20, 20, 20, 20));
        addEntry.setVgap(10);
        addEntry.setHgap(10);
        Scene entryScene = new Scene(addEntry);

        // Ikkuna listausten näyttöön
        StackPane resultWndwLayout = new StackPane();
        ListView<String> list = new ListView<>();
        resultWndwLayout.getChildren().addAll(list);
        Scene secondWndwScene = new Scene(resultWndwLayout, 600, 200);
        Stage resultWindow = new Stage();
        ObservableList<String> data = FXCollections.observableArrayList();

        // Lopetusnappi
        exitBtn.setOnAction(event -> {
            primaryStage.close();
            resultWindow.close();

        });

        // Autonlisäysnäkymään-nappi
        addVehicleBtn.setOnAction(event -> {
            primaryStage.setScene(vehicleScene);

        });

        // Tapahtumanlisäysnäkymään-nappi
        addEntryBtn.setOnAction(event -> {
            primaryStage.setScene(entryScene);

        });

        // Peru auton lisäys -nappi
        cancelVehicleBtn.setOnAction(event -> {
            plateField.clear();
            odoFieldVehicleScene.clear();
            primaryStage.setScene(mainScene);
        });

        // Peru tapahtuman lisäys -nappi
        cancelEntryBtn.setOnAction(event -> {
            msg.setText("");
            odoFieldEntryScene.clear();
            typeField.clear();
            driverField.clear();
            primaryStage.setScene(mainScene);
        });

        // Kaikkien autojen listaus (uudessa ikkunassa)
        listVehiclesBtnMain.setOnAction(event -> {
            try {

                data.clear();
                ArrayList<String> query = service.listVehicles();
                query.forEach((s) -> {
                    data.add(s);
                });
                list.setItems(data);
                resultWindow.setTitle("Ajoneuvot");
                resultWindow.setScene(secondWndwScene);
                resultWindow.show();
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Valitun auton tapahtumien listaus
        listEntriesBtnMain.setOnAction(event -> {
            try {
                String lp = (String) cBVehicleMenu.getValue();
                if (lp != null) {

                    ArrayList<String> entryQuery = service.listEntriesForVehicle(lp);
                    data.clear();
                    entryQuery.forEach((s) -> {
                        data.add(s);
                    });
                    list.setItems(data);
                    resultWindow.setTitle(lp);
                    resultWindow.setScene(secondWndwScene);
                    resultWindow.show();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Ajoneuvon syöttö
        submitVehicleBtn.setOnAction(event -> {
            try {
                String lp = plateField.getText();
                String setOdo = odoFieldVehicleScene.getText();

                if (service.vehicleExists(lp)) {
                    errorMsg.setText("Tunnuksella löytyy jo ajoneuvo!");
                } else if (!isInteger(setOdo)) {
                    errorMsg.setText("Tarkista matkamittarin lukema!");
                } else {
                    int km = Integer.parseInt(setOdo);
                    service.addVehicle(lp, km);
                    service.addEntry(lp, km, "admin", "aloitussyöttö");
                    plateField.clear();
                    odoFieldVehicleScene.clear();
                    setMenus();
                    cBVehicleMenu.setItems(vehiclesMenuItems);
                    cBEntryMenu.setItems(vehiclesMenuItems);
                    primaryStage.setScene(mainScene);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        // Tapahtuman syöttö
        // Valitse-nappi ja kilsojen näyttö
        selectVehicleForEntry.setOnAction(event -> {
            String vehicleToGet = (String) cBEntryMenu.getValue();
            int read = 0;
            if (vehicleToGet != null) {

                try {
                    read = service.getLatestOdometer(vehicleToGet);
                } catch (SQLException ex) {
                    Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                msg.setText(vehicleToGet + ": " + "Ed. lukema: " + read + " km");
            }
        });

        // Submit -nappi -> tallennus (msg = virheviesti)
        submitEntryBtn.setOnAction(event -> {
            String veh = (String) cBEntryMenu.getValue();
            String kmsAsString = odoFieldEntryScene.getText();
            String dr = driverField.getText();
            String tp = typeField.getText();
            int lastOdoKm = 0;

            if (veh != null) {
            try {
                lastOdoKm = service.getLatestOdometer(veh);
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            if ((veh == null) || (!isInteger(kmsAsString)) || (isNotValid(dr))
                    || (isNotValid(tp))) {
                msg.setText("Tarkista tiedot!");
                
            } else if (lastOdoKm > Integer.parseInt(kmsAsString)) {
                msg.setText("Tarkista matkamittarin lukema!");

            } else {
                int km = Integer.parseInt(kmsAsString);
                try {
                    service.addEntry(veh, km, dr, tp);
                    odoFieldEntryScene.clear();
                    driverField.clear();
                    typeField.clear();
                    msg.setText("");
                    primaryStage.setScene(mainScene);
                } catch (SQLException ex) {
                    Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });

        // Aloitusnäkymän starttaus
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("LogBookApp");
        primaryStage.show();

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

    public static boolean isNotValid(String s) {
        return (s.isEmpty() || s.equals(" "));
    }

    public void setMenus() throws SQLException {

        ArrayList<String> carData = service.listVehicles();
        for (String s : carData) {
            vehiclesMenuItems.add(s);
        }

    }
}
