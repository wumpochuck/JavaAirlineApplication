package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Animations.AnimationMoveButtonsPane;
import sample.DataBaseHandler;
import sample.Flight;
import sample.Ticket;
import sample.User;

public class EmployerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AnalyticsButton;

    @FXML
    private ChoiceBox<String> AnalyticsChooceBox;

    @FXML
    private AnchorPane AnalyticsPane;

    @FXML
    private PieChart AnalyticsPie;

    @FXML
    private Button BackButton;

    @FXML
    private AnchorPane ButtonsPane;

    @FXML
    private Button FlghtChangeButton;

    @FXML
    private TextField FlightToText;

    @FXML
    private Button FlightAddButton;

    @FXML
    private TextField FlightArrText;

    @FXML
    private Button FlightClearButton;

    @FXML
    private Button FlightDeleteButton;

    @FXML
    private TextField FlightDepText;

    @FXML
    private TextField FlightFromText;

    @FXML
    private TextField FlightNumberText;

    @FXML
    private AnchorPane FlightPane;

    @FXML
    private ChoiceBox<String> FlightStatusChoiceBox;

    @FXML
    private TableView<Flight> FlightTable;

    @FXML
    private TableColumn<Flight, String> FlightTableArr;

    @FXML
    private TableColumn<Flight, String> FlightTableDep;

    @FXML
    private TableColumn<Flight, String> FlightTableFrom;

    @FXML
    private TableColumn<Flight, String> FlightTableNumber;

    @FXML
    private TableColumn<Flight, Integer> FlightTablePlaces;

    @FXML
    private TableColumn<Flight, String> FlightTableStatus;

    @FXML
    private TableColumn<Flight, String> FlightTableTo;

    @FXML
    private Button FlightsButton;

    @FXML
    private AnchorPane TicketPane;

    @FXML
    private TableColumn<Ticket, Integer> TicketColumnIDUser;

    @FXML
    private TableColumn<Ticket, String> TicketColumnName;

    @FXML
    private TableColumn<Ticket, String> TicketColumnNumber;

    @FXML
    private TableColumn<Ticket, Integer> TicketColumnPrice;

    @FXML
    private TableColumn<Ticket, String> TicketColumnSeat;

    @FXML
    private TableColumn<Ticket, String> TicketColumnSurname;

    @FXML
    private Button TicketFindIDButton;

    @FXML
    private TextField TicketIDFind;

    @FXML
    private TableView<Ticket> TicketTable;

    @FXML
    private Button TicketsButton;


    @FXML
    void onButtonPaneMouseEntered(MouseEvent event) { AnimationMoveButtonsPane.moveDown(ButtonsPane); }

    @FXML
    void onButtonPaneMouseExited(MouseEvent event) { AnimationMoveButtonsPane.moveUp(ButtonsPane); }

    private ObservableList<String> FlightsStatuses = FXCollections.observableArrayList(Arrays.asList("Ожидание","Регистрация","Посадка","В полёте", "Прибыл"));

    @FXML
    void initialize() {
        initSettings();

        BackButton.setOnAction(event -> onExitButtonClick());
        TicketsButton.setOnAction(event -> onTicketButtonClick());
        FlightsButton.setOnAction(event -> onFlightButtonClick());
        AnalyticsButton.setOnAction(event -> onAnalyticsButtonClick());

        FlightAddButton.setOnAction(event -> onFlightAddButtonClick());
        FlightDeleteButton.setOnAction(event -> onFlightDeleteButtonClick());
        FlightClearButton.setOnAction(event -> onFlightClearButtonClick());
        FlghtChangeButton.setOnAction(event -> onFlightChangeButtonClick());

        TicketFindIDButton.setOnAction(event -> onFindIdButtonClick());

        AnalyticsChooceBox.setOnAction(event -> onAnalyticsChooseFlightsBoxClick());

        /*
        assert AnalyticsButton != null : "fx:id=\"AnalyticsButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert AnalyticsChooceBox != null : "fx:id=\"AnalyticsChooceBox\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert AnalyticsPane != null : "fx:id=\"AnalyticsPane\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert AnalyticsPie != null : "fx:id=\"AnalyticsPie\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert ButtonsPane != null : "fx:id=\"ButtonsPane\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlghtChangeButton != null : "fx:id=\"FlghtChangeButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightToText != null : "fx:id=\"FlghtToText\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightAddButton != null : "fx:id=\"FlightAddButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightArrText != null : "fx:id=\"FlightArrText\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightClearButton != null : "fx:id=\"FlightClearButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightDeleteButton != null : "fx:id=\"FlightDeleteButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightDepText != null : "fx:id=\"FlightDepText\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightFromText != null : "fx:id=\"FlightFromText\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightNumberText != null : "fx:id=\"FlightNumberText\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightPane != null : "fx:id=\"FlightPane\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightStatusChoiceBox != null : "fx:id=\"FlightStatusChoiceBox\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTable != null : "fx:id=\"FlightTable\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTableArr != null : "fx:id=\"FlightTableArr\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTableDep != null : "fx:id=\"FlightTableDep\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTableFrom != null : "fx:id=\"FlightTableFrom\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTableNumber != null : "fx:id=\"FlightTableNumber\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTablePlaces != null : "fx:id=\"FlightTablePlaces\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTableStatus != null : "fx:id=\"FlightTableStatus\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightTableTo != null : "fx:id=\"FlightTableTo\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert FlightsButton != null : "fx:id=\"FlightsButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert TicketPane != null : "fx:id=\"TicketPane\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert TicketTable != null : "fx:id=\"TicketTable\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        assert TicketsButton != null : "fx:id=\"TicketsButton\" was not injected: check your FXML file 'EmployerWindow.fxml'.";
        */
    }

    public void initSettings(){
        ButtonsPane.setLayoutY(-150);
        onFlightButtonClick();

        FlightTableNumber.setCellValueFactory(new PropertyValueFactory<Flight,String>("number"));
        FlightTableFrom.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightFrom"));
        FlightTableTo.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightTo"));
        FlightTableDep.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureTime"));
        FlightTableArr.setCellValueFactory(new PropertyValueFactory<Flight,String>("arrivalTime"));
        FlightTablePlaces.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("countOfSeats"));
        FlightTableStatus.setCellValueFactory(new PropertyValueFactory<Flight,String>("status"));
        updateFlightsTable();

        FlightStatusChoiceBox.setItems(FlightsStatuses);

        TicketColumnIDUser.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("userId"));
        TicketColumnNumber.setCellValueFactory(new PropertyValueFactory<Ticket,String>("flightNumber"));
        TicketColumnName.setCellValueFactory(new PropertyValueFactory<Ticket,String>("passengerName"));
        TicketColumnSurname.setCellValueFactory(new PropertyValueFactory<Ticket,String>("passengerSurname"));
        TicketColumnSeat.setCellValueFactory(new PropertyValueFactory<Ticket,String>("seatNumber"));
        TicketColumnPrice.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("price"));
        updateTicketsTable();

    }

    public void hideAllPanes(){
        AnalyticsPane.setVisible(false);
        FlightPane.setVisible(false);
        TicketPane.setVisible(false);
    }

    public void paintAllButtonsBlue(){
        FlightsButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
        TicketsButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
        AnalyticsButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
    }

    public void onFlightButtonClick(){
        hideAllPanes();
        paintAllButtonsBlue();
        FlightsButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
        FlightPane.setVisible(true);

        updateFlightsTable();
    }

    public void onTicketButtonClick(){
        hideAllPanes();
        paintAllButtonsBlue();
        TicketsButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
        TicketPane.setVisible(true);

        updateTicketsTable();
    }

    public void onAnalyticsButtonClick(){
        hideAllPanes();
        paintAllButtonsBlue();
        AnalyticsButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
        AnalyticsPane.setVisible(true);

        AnalyticsUpdate();
    }

    public void onExitButtonClick(){
        Stage currentStage = (Stage) BackButton.getScene().getWindow();
        currentStage.close();
        //openNewScene("/sample/Templates/MainWindow.fxml");
    }

    public void openNewScene(String window) {
        Stage currentStage = (Stage) ButtonsPane.getScene().getWindow();
        currentStage.close();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    // FLIGHTs FUNC'S

    public void updateFlightsTable() {
        ObservableList<Flight> flightData = FXCollections.observableArrayList(new DataBaseHandler().getAllFlights());
        FlightTable.setItems(flightData);
    }

    public void onFlightAddButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String number = FlightNumberText.getText();
        String from = FlightFromText.getText();
        String to = FlightToText.getText();
        String dep = FlightDepText.getText();
        String arr = FlightArrText.getText();
        int count = 124;
        String status = FlightStatusChoiceBox.getValue();

        alert.setContentText("Рейс добавлен!");
        alert.showAndWait();

        Flight newFlight = new Flight(number,from,to,dep,arr,count,status);
        new DataBaseHandler().addFlightToTable(newFlight);

        updateFlightsTable();
    }

    public void onFlightDeleteButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Flight chosen_flight = FlightTable.getSelectionModel().getSelectedItem();
        if(chosen_flight == null){
            alert.setContentText("Выберите рейс!");
            alert.showAndWait();
            return;
        }
        new DataBaseHandler().deleteFlightByNumber(chosen_flight.getNumber());

        updateFlightsTable();
    }

    public void onFlightClearButtonClick(){
        FlightNumberText.setText("");
        FlightFromText.setText("");
        FlightToText.setText("");
        FlightDepText.setText("");
        FlightArrText.setText("");
        FlightStatusChoiceBox.setValue("");

        updateFlightsTable();
    }

    public void onFlightChangeButtonClick() {
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Flight chosenFlight = FlightTable.getSelectionModel().getSelectedItem();
        if (chosenFlight == null) {
            alert.setContentText("Выберите рейс!");
            alert.showAndWait();
            return;
        }

        String number = FlightNumberText.getText().equals("") ? chosenFlight.getNumber() : FlightNumberText.getText();
        String from = FlightFromText.getText().equals("") ? chosenFlight.getFlightFrom() : FlightFromText.getText();
        String to = FlightToText.getText().equals("") ? chosenFlight.getFlightTo() : FlightToText.getText();
        String dep = FlightDepText.getText().equals("") ? chosenFlight.getDepartureTime() : FlightDepText.getText();
        String arr = FlightArrText.getText().equals("") ? chosenFlight.getArrivalTime() : FlightArrText.getText();
        String status = FlightStatusChoiceBox.getValue().isEmpty() ? "Ожидание" : FlightStatusChoiceBox.getValue();


        chosenFlight.setNumber(number);
        chosenFlight.setFlightFrom(from);
        chosenFlight.setFlightTo(to);
        chosenFlight.setDepartureTime(dep);
        chosenFlight.setArrivalTime(arr);
        chosenFlight.setStatus(status);

        new DataBaseHandler().updateFlight(chosenFlight);

        updateFlightsTable();

        alert.setContentText("Данные рейса обновлены!");
        alert.showAndWait();
    }

    // TICKET FUNC'S

    public void updateTicketsTable(){
        ObservableList<Ticket> ticketsData = FXCollections.observableArrayList(new DataBaseHandler().getAllTickets());
        TicketTable.setItems(ticketsData);
    }

    public void onFindIdButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String id = TicketIDFind.getText();
        if(id.equals("") || id == null){
            alert.setContentText("Ввведите id!");
            alert.showAndWait();
            updateTicketsTable();
            return;
        }

        ObservableList<Ticket> ticketsData = FXCollections.observableArrayList(new DataBaseHandler().getAllTicketsByUserId(id));
        TicketTable.setItems(ticketsData);
    }
    // ANALYTICS FUNC's

    public void AnalyticsUpdate(){
        List<Flight> all_flights = new DataBaseHandler().getAllFlights();
        List<String> all_flights1 = new ArrayList<String>();
        for(Flight flight : all_flights){
            all_flights1.add(String.format("%s | %s-%s",flight.getNumber(), flight.getFlightFrom(),flight.getFlightTo()));
        }
        ObservableList<String> flights = FXCollections.observableArrayList(all_flights1);
        AnalyticsChooceBox.setItems(flights);
        AnalyticsChooceBox.setValue("");
    }

    public void onAnalyticsChooseFlightsBoxClick(){
        String chosenflight = AnalyticsChooceBox.getValue();
        if (chosenflight == null) return;
        if (chosenflight.equals("")) {

            return;
        }
        chosenflight = chosenflight.split(" ")[0].replace(" ","");

        int occupiedSeats = new DataBaseHandler().getOccupiedSeatsByFlightNumber(chosenflight);
        int totalSeats = new DataBaseHandler().getTotalSeatsByFlightNumber(chosenflight);
        updatePieChart(occupiedSeats,totalSeats);

    }

    // Метод для обновления данных в PieChart
    private void updatePieChart(int occupiedSeats, int totalSeats) {

        AnalyticsPie.getData().clear(); // Очищаем предыдущие данные

        // Добавляем новые данные в PieChart
        AnalyticsPie.getData().addAll(
                new PieChart.Data("Занято: "+ Integer.toString(occupiedSeats), occupiedSeats),
                new PieChart.Data("Свободно: "+ Integer.toString(totalSeats), totalSeats)
        );
    }

}

