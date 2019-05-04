package vehiclelogapp.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vehiclelogapp.domain.VehicleLogService;

/**
 * Graafisen käyttöliittymän toteuttava luokka
 *
 */
public class GraphicInterface extends Application {

    private VehicleLogService service;
    private Scene addVehicle;
    private Scene addEntry;
    private Scene listEntries;
    private ObservableList<String> vehicleMenuItems;

    /**
     * Lukee konfiguraatiotiedoston ja luo yhteyden toimintalogiikkaan. Luo
     * listaolion valikkoja varten. Mikäli tietokannan käyttäjänimi tai salasana
     * väärä, ohjelman suoritus keskeytetään.
     *
     * @throws FileNotFoundException Heittää poikkeuksen, jos tiedostoa ei
     * löydy.
     * @throws IOException Heittää poikkeuksen, mikäli tiedoston luku
     * epäonnistuu.
     */
    @Override
    public void init() throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String db = properties.getProperty("database");
        String user = properties.getProperty("user");
        String pw = properties.getProperty("password");

        try {
            service = new VehicleLogService(db, user, pw);
        } catch (SQLException ex) {
            System.out.println("\n** Tietokannan avaaminen epäonnistui. Väärä salasana tai käyttäjänimi konfiguraatiotiedostossa. **\n");
            System.exit(0);
        }

        vehicleMenuItems = FXCollections.observableArrayList();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ComboBox cBVehicleMenu = new ComboBox();
        cBVehicleMenu.setVisibleRowCount(5);
        cBVehicleMenu.setPromptText("Ajoneuvo");
        cBVehicleMenu.setTooltip(new Tooltip("Valitse ajoneuvo"));
        ComboBox cBEntryMenu = new ComboBox();
        cBEntryMenu.setVisibleRowCount(5);
        cBEntryMenu.setPromptText("Ajoneuvo");
        cBEntryMenu.setTooltip(new Tooltip("Valitse ajoneuvo"));

        setMenus();
        cBVehicleMenu.setItems(vehicleMenuItems);
        cBEntryMenu.setItems(vehicleMenuItems);

        Button addVehicleBtn = new Button("Lisää ajoneuvo");
        Button addEntryBtn = new Button("Lisää tapahtuma");
        Button listVehiclesBtnMain = new Button("Listaa kaikki ajoneuvot");
        Button listEntriesBtnMain = new Button("Listaa ajoneuvon tapahtumat");
        Button searchEntriesBtn = new Button("Hae tapahtumia");
        Button exitBtn = new Button("Lopeta");
        Label greetingText = new Label("Tervetuloa! Valitse toiminto!");

        Label plateLbl = new Label("Anna rekisteritunnus");
        TextField plateField = new TextField();
        plateField.setMaxWidth(150);
        Label odometerLbl = new Label("Anna matkamittarin aloituslukema");
        TextField odoFieldVehicleScene = new TextField();
        odoFieldVehicleScene.setMaxWidth(150);
        Label errorMsg = new Label();
        Button submitVehicleBtn = new Button("Lisää ajoneuvo järjestelmään");
        Button cancelVehicleBtn = new Button("Cancel");

        Label msg = new Label();
        ChoiceBox chooseEntryTypeCb = new ChoiceBox(FXCollections.observableArrayList("AJO",
                "HUOLTO", "TANKKAUS", "MUU"));
        chooseEntryTypeCb.setValue("AJO");
        chooseEntryTypeCb.setTooltip(new Tooltip("Tapahtuman tyyppi"));
        Label kmLbl = new Label("Matkamittarin lukema:");
        TextField odoFieldEntryScene = new TextField();
        odoFieldEntryScene.setMinWidth(200);
        Label driverLbl = new Label("Kuljettaja:");
        TextField driverField = new TextField();
        driverField.setMinWidth(300);
        Label typeLbl = new Label("Anna selite tai asiakas:");
        TextField typeField = new TextField();
        kmLbl.setMinWidth(200);
        typeField.setMinWidth(200);
        Button submitEntryBtn = new Button("Tallenna");
        Button cancelEntryBtn = new Button("Cancel");
        Button selectVehicleForEntryBtn = new Button("Näytä ajoneuvon tiedot");

        Label searchLabel = new Label("Anna hakusana:");
        TextField searchField = new TextField();
        Button submitSearch = new Button("Etsi");
        Button cancelSearch = new Button("Cancel");

        BorderPane mainLayout = new BorderPane();
        HBox btnsMainBox = new HBox();
        btnsMainBox.setPadding(new Insets(20, 20, 20, 20));
        btnsMainBox.setSpacing(20);
        HBox listAndChoiceBox = new HBox();
        listAndChoiceBox.setPadding(new Insets(20, 20, 20, 20));
        listAndChoiceBox.setSpacing(5);
        HBox lastRowBox = new HBox();
        lastRowBox.setPadding(new Insets(20, 20, 20, 20));
        lastRowBox.setSpacing(85);

        btnsMainBox.getChildren().addAll(addVehicleBtn, addEntryBtn, searchEntriesBtn);
        listAndChoiceBox.getChildren().addAll(listEntriesBtnMain, cBVehicleMenu);
        lastRowBox.getChildren().addAll(listVehiclesBtnMain, exitBtn);

        mainLayout.setTop(btnsMainBox);
        mainLayout.setCenter(listAndChoiceBox);
        mainLayout.setBottom(lastRowBox);
        mainLayout.setPadding(new Insets(20, 20, 20, 20));

        Scene startScene = new Scene(mainLayout);

        GridPane addNewVehicle = new GridPane();
        addNewVehicle.add(plateLbl, 0, 0);
        addNewVehicle.add(plateField, 1, 0);
        addNewVehicle.add(odometerLbl, 0, 1);
        addNewVehicle.add(odoFieldVehicleScene, 1, 1);
        addNewVehicle.add(submitVehicleBtn, 0, 2);
        addNewVehicle.add(cancelVehicleBtn, 1, 2);
        addNewVehicle.add(errorMsg, 0, 3);
        addNewVehicle.setPrefSize(400, 200);
        addNewVehicle.setPadding(new Insets(20, 20, 20, 20));
        addNewVehicle.setVgap(10);
        addNewVehicle.setHgap(10);
        Scene vehicleScene = new Scene(addNewVehicle);

        GridPane addNewEntry = new GridPane();
        addNewEntry.add(cBEntryMenu, 0, 0);
        addNewEntry.add(selectVehicleForEntryBtn, 1, 0);
        addNewEntry.add(chooseEntryTypeCb, 2, 0);
        addNewEntry.add(msg, 1, 1);
        addNewEntry.add(kmLbl, 0, 2);
        addNewEntry.add(odoFieldEntryScene, 1, 2);
        addNewEntry.add(driverLbl, 0, 3);
        addNewEntry.add(driverField, 1, 3);
        addNewEntry.add(typeLbl, 0, 4);
        addNewEntry.add(typeField, 1, 4);
        addNewEntry.add(submitEntryBtn, 0, 5);
        addNewEntry.add(cancelEntryBtn, 1, 5);
        addNewEntry.setPrefSize(630, 200);
        addNewEntry.setPadding(new Insets(20, 20, 20, 20));
        addNewEntry.setVgap(10);
        addNewEntry.setHgap(10);
        Scene entryScene = new Scene(addNewEntry);

        GridPane searchEntries = new GridPane();
        searchEntries.add(searchLabel, 0, 0);
        searchEntries.add(searchField, 0, 1);
        searchEntries.add(submitSearch, 0, 2);
        searchEntries.add(cancelSearch, 1, 2);
        searchEntries.setPadding(new Insets(20, 20, 20, 20));
        searchEntries.setHgap(10);
        searchEntries.setVgap(10);
        Scene searchScene = new Scene(searchEntries);

        StackPane resultWndwLayout = new StackPane();
        ListView<String> list = new ListView<>();
        resultWndwLayout.getChildren().addAll(list);
        Scene secondWndwScene = new Scene(resultWndwLayout, 600, 200);
        Stage resultWindow = new Stage();
        ObservableList<String> data = FXCollections.observableArrayList();

        exitBtn.setOnAction(event -> {
            primaryStage.close();
            resultWindow.close();

        });

        addVehicleBtn.setOnAction(event -> {
            primaryStage.setScene(vehicleScene);

        });

        addEntryBtn.setOnAction(event -> {
            primaryStage.setScene(entryScene);

        });

        searchEntriesBtn.setOnAction(event -> {
            primaryStage.setScene(searchScene);
        });

        cancelSearch.setOnAction(event -> {
            searchField.clear();
            primaryStage.setScene(startScene);
        });

        cancelVehicleBtn.setOnAction(event -> {
            plateField.clear();
            odoFieldVehicleScene.clear();
            primaryStage.setScene(startScene);
        });

        cancelEntryBtn.setOnAction(event -> {
            msg.setText("");
            odoFieldEntryScene.clear();
            typeField.clear();
            driverField.clear();
            primaryStage.setScene(startScene);
        });

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

        submitSearch.setOnAction(event -> {
            try {
                String searchKey = searchField.getText();
                data.clear();
                ArrayList<String> searchRes = service.searchEntries(searchKey);
                searchRes.forEach((s) -> {
                    data.add(s);
                });
                list.setItems(data);
                resultWindow.setTitle("Haku: " + searchKey);
                resultWindow.setScene(secondWndwScene);
                resultWindow.show();
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

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

        submitVehicleBtn.setOnAction(event -> {
            try {
                String lp = plateField.getText();
                String setOdo = odoFieldVehicleScene.getText();

                if (service.vehicleExists(lp)) {
                    errorMsg.setText("Tunnuksella löytyy jo ajoneuvo!");
                } else if (isNotValid(lp)) {
                    errorMsg.setText("Anna tunnus!");
                } else if (!isInteger(setOdo)) {
                    errorMsg.setText("Tarkista matkamittarin lukema!");
                } else {
                    int km = Integer.parseInt(setOdo);
                    service.addVehicle(lp, km);
                    plateField.clear();
                    odoFieldVehicleScene.clear();
                    errorMsg.setText("");
                    setMenus();
                    cBVehicleMenu.setItems(vehicleMenuItems);
                    cBEntryMenu.setItems(vehicleMenuItems);
                    primaryStage.setScene(startScene);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        selectVehicleForEntryBtn.setOnAction(event -> {
            String vehicleToGet = (String) cBEntryMenu.getValue();
            int read = 0;
            if (vehicleToGet != null) {

                try {
                    read = service.getLatestOdometer(vehicleToGet);
                } catch (SQLException ex) {
                    Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                msg.setText(vehicleToGet + ": " + "Viimeisin lukema: " + read + " km");
            }
        });

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
                msg.setText("Tarkista tiedot, älä jätä tyhjiä kenttiä!");

            } else if (lastOdoKm > Integer.parseInt(kmsAsString)) {
                msg.setText("Tarkista matkamittarin lukema!");

            } else {
                int km = Integer.parseInt(kmsAsString);
                try {
                    String type = (String) chooseEntryTypeCb.getValue() + ": " + tp;
                    service.addEntry(veh, km, dr, type);
                    odoFieldEntryScene.clear();
                    driverField.clear();
                    typeField.clear();
                    msg.setText("");
                    primaryStage.setScene(startScene);
                } catch (SQLException ex) {
                    Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        primaryStage.setScene(startScene);
        primaryStage.setTitle("LogBookApp");
        primaryStage.show();

    }

    /**
     * Syötteen validointiin käytetty apumetodi. Toteutettu myös tässä luokassa,
     * jotta virheilmoitukset saadaan selkeämmiksi.
     *
     * @param s syöte
     * @return true, mikäli merkkijono on luku
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Syötteen validointiin käytetty apumetodi. Toteutettu myös tässä luokassa,
     * jotta virheilmoitukset saadaan selkeämmiksi.
     *
     * @param s syöte
     * @return true, mikäli merkkijono ei ole tyhjä
     */
    public static boolean isNotValid(String s) {
        return (s.isEmpty() || s.equals(" "));
    }

    /**
     * Asettaa ajoneuvot alasvetovalikoihin.
     *
     * @throws SQLException mikäli tietokannan luku epäonnistuu
     */
    public void setMenus() throws SQLException {
        ArrayList<String> carData = service.listVehicles();
        carData.stream().filter((s) -> (!vehicleMenuItems.contains(s))).forEachOrdered((s) -> {
            vehicleMenuItems.add(s);
        });
    }
}
