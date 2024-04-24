package sample.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Animations.AnimationMoveButtonsPane;
import sample.Animations.AnimationMoveMiddleButtons;
import sample.DataBaseHandler;
import sample.Flight;
import sample.Ticket;
import sample.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AdminButton;

    @FXML
    private AnchorPane ButtonsPane;

    @FXML
    private Button EmployerButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button MainButton;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Text MainWelcomeText;

    @FXML
    private Button MiddleButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private AnchorPane ProfilePane;

    @FXML
    private Button TicketButton;

    @FXML
    private ChoiceBox<String> TicketChoiseFlight;

    @FXML
    private Button TicketChoiseSeat;

    @FXML
    private AnchorPane TicketPage;

    @FXML
    private Button TicketPayButton;

    @FXML
    private Text TicketPriceText;

    @FXML
    private Text TicketText;

    @FXML
    private Button profile_AddMoneyButton;

    @FXML
    private Button profile_ApplyDataButton;

    @FXML
    private TextArea profile_DataText;

    @FXML
    private TextField profile_MoneyText;

    @FXML
    private TextField profile_NameText;

    @FXML
    private TextField profile_SurnameText;

    @FXML
    void onButtonPaneMouseEntered(MouseEvent event) { AnimationMoveButtonsPane.moveDown(ButtonsPane); }

    @FXML
    void onButtonPaneMouseExited(MouseEvent event) { AnimationMoveButtonsPane.moveUp(ButtonsPane); }

    public static User current_user;
    public static String numberOfCurrentFlight;
    public static String selectedSeatNumber;
    public static Ticket currentTicket;
    public static int currentPrice;

    private static String middleButtonsFlag = "up";

    private ObservableList<String> flights;

    @FXML
    void initialize() {
        initSettings();

        MainWelcomeText.setText(String.format("Здравствуйте, %s!\nВы вошли как %s.",current_user.getFullName(), current_user.getRole()));
        MainButton.setOnAction(event -> onMainButtonClick());
        ProfileButton.setOnAction(event -> onProfileButtonClick());
        ExitButton.setOnAction(event -> onExitButtonClick());
        MiddleButton.setOnAction(event -> onMiddleButtonClick());
        EmployerButton.setOnAction(event -> onEmployerButtonClick());
        AdminButton.setOnAction(event -> onAdminButtonClick());
        TicketButton.setOnAction(event -> onTicketButtonClick());
        TicketPayButton.setOnAction(event -> {onTicketPayButtonClick(); ticketUpdate();});

        profile_ApplyDataButton.setOnAction(event -> profileOnUpdateDataButtonClick());
        profile_AddMoneyButton.setOnAction(event -> profileOnAddMoneyButtonClick());

        TicketChoiseSeat.setOnAction(event -> onTicketSeatChoiceClick());

        assert AdminButton != null : "fx:id=\"AdminButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ButtonsPane != null : "fx:id=\"ButtonsPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert EmployerButton != null : "fx:id=\"EmployerButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MainButton != null : "fx:id=\"MainButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MainPane != null : "fx:id=\"MainPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MainWelcomeText != null : "fx:id=\"MainWelcomeText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert MiddleButton != null : "fx:id=\"MiddleButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ProfileButton != null : "fx:id=\"ProfileButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ProfilePane != null : "fx:id=\"ProfilePane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketButton != null : "fx:id=\"TicketButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketChoiseFlight != null : "fx:id=\"TicketChoiseFlight\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketChoiseSeat != null : "fx:id=\"TicketChoiseSeat\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketPage != null : "fx:id=\"TicketPage\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketPayButton != null : "fx:id=\"TicketPayButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketPriceText != null : "fx:id=\"TicketPriceText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert TicketText != null : "fx:id=\"TicketText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_AddMoneyButton != null : "fx:id=\"profile_AddMoneyButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_ApplyDataButton != null : "fx:id=\"profile_ApplyDataButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_DataText != null : "fx:id=\"profile_DataText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_MoneyText != null : "fx:id=\"profile_MoneyText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_NameText != null : "fx:id=\"profile_NameText\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profile_SurnameText != null : "fx:id=\"profile_SurnameText\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

    void initSettings(){
        ButtonsPane.setLayoutY(-150);
        current_user = LoginWindowController.current_user;
        if(current_user.getRole().equals("guest")) showEmployerButtons(false);
        onMainButtonClick();

        EmployerButton.setDisable(true);
        AdminButton.setDisable(true);
    }

    public void openNewScene(String window) {
        Stage currentStage = (Stage) MainPane.getScene().getWindow();
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

    public void openNewSceneWithoutClosing(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL); // Установка модального режима
            newStage.setScene(new Scene(root));
            newStage.showAndWait(); // Показать окно и ждать, пока оно не будет закрыто
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showEmployerButtons(boolean flag){
        MiddleButton.setVisible(flag);
        EmployerButton.setVisible(flag);
        AdminButton.setVisible(flag);

    }

    void paintAllButtonsBlue(){
        MainButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
        TicketButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
        MiddleButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
        ProfileButton.setStyle("-fx-background-color: #13a3ed; -fx-background-radius: 15;");
    }

    void hideAllPanes(){
        MainPane.setVisible(false);
        ProfilePane.setVisible(false);
        TicketPage.setVisible(false);
    }

    public void onMainButtonClick(){
        AnimationMoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
        middleButtonsFlag = "up";

        paintAllButtonsBlue();
        hideAllPanes();
        MainButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
        MainPane.setVisible(true);
        MainWelcomeText.setText(String.format("Здравствуйте, %s!\nВы вошли как %s.",current_user.getFullName(), current_user.getRole()));

    }

    public void onProfileButtonClick(){
        AnimationMoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
        middleButtonsFlag = "up";

        paintAllButtonsBlue();
        hideAllPanes();
        ProfileButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
        ProfilePane.setVisible(true);
        profileUpdate();

    }

    public void onExitButtonClick(){
        current_user = null;
        numberOfCurrentFlight = null;
        selectedSeatNumber = null;
        currentTicket = null;
        currentPrice = 0;
        openNewScene("/sample/Templates/LoginWindow.fxml");
    }

    public void onMiddleButtonClick(){
        if (middleButtonsFlag.equals("up")) {
            paintAllButtonsBlue();
            MiddleButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
            AnimationMoveMiddleButtons.moveMiddleButtonsDown(EmployerButton,AdminButton);
            middleButtonsFlag = "down";
        }else{
            paintAllButtonsBlue();
            AnimationMoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
            middleButtonsFlag = "up";
        }
    }

    public void onEmployerButtonClick(){
        openNewSceneWithoutClosing("/sample/Templates/EmployerWindow.fxml");
    }

    public void onTicketButtonClick(){
        AnimationMoveMiddleButtons.moveMiddleButtonsUp(EmployerButton,AdminButton);
        middleButtonsFlag = "up";

        paintAllButtonsBlue();
        hideAllPanes();
        TicketButton.setStyle("-fx-background-color: #ff0074; -fx-background-radius: 15;");
        TicketPage.setVisible(true);

        ticketUpdate();
    }

    public void onAdminButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        if (!current_user.getRole().equals("admin")){
            alert.setContentText("Отказано в доступе!");
            alert.showAndWait();
            return;
        }
        openNewSceneWithoutClosing("/sample/Templates/AdminWindow.fxml");}

    // PROFILE FUNC'S

    public void profileUpdate(){
        profile_DataText.setText(String.format("Логин: %s\nИмя: %s\nФамилия: %s\nБаланс: %.2fруб\nРоль: %s\n",
                current_user.getLogin(),
                current_user.getName(),current_user.getSurname(),
                current_user.getMoney(), current_user.getRole()));
    }

    public void profileOnUpdateDataButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Информация обновлена!");
        alert.showAndWait();

        String name = profile_NameText.getText();
        if(name.equals("")) name = current_user.getName();
        String surname = profile_SurnameText.getText();
        if(surname.equals("")) surname = current_user.getSurname();

        current_user.setName(name);
        current_user.setSurname(surname);

        try { new DataBaseHandler().updateUserByLogin(current_user); }
        catch (SQLException e) { e.printStackTrace(); }
        profileUpdate();

    }

    public void profileOnAddMoneyButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        double money = Double.parseDouble(profile_MoneyText.getText());
        if(money <= 0) {
            alert.setContentText("Операция отклонена!");
            alert.showAndWait();
            return;
        }
        alert.setContentText("Операция выполнена!");
        alert.showAndWait();

        current_user.setMoney(current_user.getMoney() + money);

        try { new DataBaseHandler().updateUserByLogin(current_user); }
        catch (SQLException e) { e.printStackTrace(); }
        profileUpdate();
    }

    // TICKET FINC'S

    public void onTicketSeatChoiceClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String chosenflight = TicketChoiseFlight.getValue();
        if (chosenflight.equals("")) {
            alert.setContentText("Выберите рейс!");
            alert.showAndWait();
            return;
        }
        numberOfCurrentFlight = chosenflight.split(" ")[0];

        openNewSceneWithoutClosing("/sample/Templates/B737.fxml");
        //System.out.println("(MAIN) Chosen Seat: " + selectedSeatNumber);

        if(selectedSeatNumber == null){
            alert.setContentText("Выберите место!");
            alert.showAndWait();
            return;
        }
        if(current_user.getFullName().equals(current_user.getLogin())){
            alert.setContentText("Для продолжения заполните данные аккаунта!");
            alert.showAndWait();
            return;
        }

        TicketText.setText(String.format("ФИО / %s\nРейс № / %s\nМесто / %s\nОтправление / %s\nПрибытие / %s",
                current_user.getFullName(),numberOfCurrentFlight,selectedSeatNumber,
                new DataBaseHandler().getDepartureTimeByFlightNumber(numberOfCurrentFlight),
                new DataBaseHandler().getArrivalTimeByFlightNumber(numberOfCurrentFlight)));

        TicketPriceText.setText(String.format("Цена: %d рублей",currentPrice));

        currentTicket = new Ticket(current_user.getUserId(),numberOfCurrentFlight,
                current_user.getName(),current_user.getSurname(),selectedSeatNumber,currentPrice);
    }

    public static void onTicketPayButtonClick(){
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        if(currentPrice == 0){
            alert.setContentText("Билет не выбран!");
            alert.showAndWait();
            return;
        }

        if(currentPrice > current_user.getMoney()){
            alert.setContentText("Недостаточно средств!");
            alert.showAndWait();
            return;
        }

        System.out.println(numberOfCurrentFlight.replace(" ",""));
        Flight flight = new DataBaseHandler().getFlightByFlightNumber(numberOfCurrentFlight.replace(" ",""));

        if(flight == null){
            alert.setContentText("Ошибка!");
            alert.showAndWait();
            return;
        }

        alert.setContentText("Покупка завершена!");
        alert.showAndWait();

        current_user.setMoney(current_user.getMoney() - currentPrice);
        try { new DataBaseHandler().updateUserByLogin(current_user); } catch (SQLException e) { e.printStackTrace(); }

        new DataBaseHandler().addTicket(currentTicket);

        flight.setCountOfSeats(flight.getCountOfSeats() - 1);
        new DataBaseHandler().updateFlight(flight);

    }

    public void ticketUpdate(){
        List<Flight> all_flights = new DataBaseHandler().getAllFlights();
        List<String> all_flights1 = new ArrayList<String>();
        for(Flight flight : all_flights){
            all_flights1.add(String.format("%s | %s-%s, %d мест(а)",flight.getNumber(), flight.getFlightFrom(),flight.getFlightTo(),flight.getCountOfSeats()));
        }
        flights = FXCollections.observableArrayList(all_flights1);
        TicketChoiseFlight.setItems(flights);
        TicketChoiseFlight.setValue("");
        TicketText.setText("ФИО / \n" +
                "Рейс № /\n" +
                "Место /\n" +
                "Отправление /\n" +
                "Прибытие /");
        TicketPriceText.setText("Цена: ~ рублей");
    }
}
