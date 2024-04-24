package sample.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DataBaseHandler;

public class B737_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button A01;

    @FXML
    private Button A02;

    @FXML
    private Button A03;

    @FXML
    private Button A04;

    @FXML
    private Button A05;

    @FXML
    private Button A06;

    @FXML
    private Button A07;

    @FXML
    private Button A08;

    @FXML
    private Button A09;

    @FXML
    private Button A11;

    @FXML
    private Button A12;

    @FXML
    private Button A13;

    @FXML
    private Button A14;

    @FXML
    private Button A15;

    @FXML
    private Button A16;

    @FXML
    private Button A17;

    @FXML
    private Button A18;

    @FXML
    private Button A19;

    @FXML
    private Button A20;

    @FXML
    private Button A21;

    @FXML
    private Button A22;

    @FXML
    private Button B04;

    @FXML
    private Button B05;

    @FXML
    private Button B06;

    @FXML
    private Button B07;

    @FXML
    private Button B08;

    @FXML
    private Button B09;

    @FXML
    private Button B10;

    @FXML
    private Button B11;

    @FXML
    private Button B12;

    @FXML
    private Button B13;

    @FXML
    private Button B14;

    @FXML
    private Button B15;

    @FXML
    private Button B16;

    @FXML
    private Button B17;

    @FXML
    private Button B18;

    @FXML
    private Button B19;

    @FXML
    private Button B20;

    @FXML
    private Button B21;

    @FXML
    private Button B22;

    @FXML
    private Button C01;

    @FXML
    private Button C02;

    @FXML
    private Button C03;

    @FXML
    private Button C04;

    @FXML
    private Button C05;

    @FXML
    private Button C06;

    @FXML
    private Button C07;

    @FXML
    private Button C08;

    @FXML
    private Button C09;

    @FXML
    private Button C10;

    @FXML
    private Button C11;

    @FXML
    private Button C12;

    @FXML
    private Button C13;

    @FXML
    private Button C14;

    @FXML
    private Button C15;

    @FXML
    private Button C16;

    @FXML
    private Button C17;

    @FXML
    private Button C18;

    @FXML
    private Button C19;

    @FXML
    private Button C20;

    @FXML
    private Button C21;

    @FXML
    private Button C22;

    @FXML
    private Button D01;

    @FXML
    private Button D02;

    @FXML
    private Button D03;

    @FXML
    private Button D04;

    @FXML
    private Button D05;

    @FXML
    private Button D06;

    @FXML
    private Button D07;

    @FXML
    private Button D08;

    @FXML
    private Button D09;

    @FXML
    private Button D10;

    @FXML
    private Button D11;

    @FXML
    private Button D12;

    @FXML
    private Button D13;

    @FXML
    private Button D14;

    @FXML
    private Button D15;

    @FXML
    private Button D16;

    @FXML
    private Button D17;

    @FXML
    private Button D18;

    @FXML
    private Button D19;

    @FXML
    private Button D20;

    @FXML
    private Button D21;

    @FXML
    private Button D22;

    @FXML
    private Button E04;

    @FXML
    private Button E05;

    @FXML
    private Button E06;

    @FXML
    private Button E07;

    @FXML
    private Button E08;

    @FXML
    private Button E09;

    @FXML
    private Button E10;

    @FXML
    private Button E11;

    @FXML
    private Button E12;

    @FXML
    private Button E13;

    @FXML
    private Button E14;

    @FXML
    private Button E15;

    @FXML
    private Button E16;

    @FXML
    private Button E17;

    @FXML
    private Button E18;

    @FXML
    private Button E19;

    @FXML
    private Button E20;

    @FXML
    private Button E21;

    @FXML
    private Button E22;

    @FXML
    private Button F01;

    @FXML
    private Button F02;

    @FXML
    private Button F03;

    @FXML
    private Button F04;

    @FXML
    private Button F05;

    @FXML
    private Button F06;

    @FXML
    private Button F07;

    @FXML
    private Button F08;

    @FXML
    private Button F09;

    @FXML
    private Button F11;

    @FXML
    private Button F12;

    @FXML
    private Button F13;

    @FXML
    private Button F14;

    @FXML
    private Button F15;

    @FXML
    private Button F16;

    @FXML
    private Button F17;

    @FXML
    private Button F18;

    @FXML
    private Button F19;

    @FXML
    private Button F20;

    @FXML
    private Button F21;

    @FXML
    private Button F22;

    @FXML
    private AnchorPane b737pane;

    @FXML
    private Button doneButton;

    @FXML
    private Button exit;

    @FXML
    private Button exitButton;

    @FXML
    void exit(ActionEvent event) {
        currentFlightNumber = null;
        MainWindowController.selectedSeatNumber = null;
        MainWindowController.currentPrice = 0;

        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }



    @FXML
    void userInfoForm(ActionEvent event) {

        paintAllButtons();

        Button clickedButton = (Button) event.getSource(); // Получаем кнопку, на которую нажали
        seatNumber = clickedButton.getId(); // Получаем идентификатор кнопки (номер места)

        // System.out.println("Chosen Seat: " + seatNumber);

        updateSeats();
        clickedButton.setStyle("-fx-background-color: #ff0074;");
    }

    public String currentFlightNumber;
    private String seatNumber;

    @FXML
    void initialize() {
        currentFlightNumber = MainWindowController.numberOfCurrentFlight;
        updateSeats();
        doneButton.setOnAction(event -> onDoneButtonClick());
    }

    void updateSeats(){
        List<String> namesOfSeats = new DataBaseHandler().getSeatNumbersByFlightNumber(currentFlightNumber);
        for (Node node : b737pane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if(!button.getText().equals("EXIT") && !button.getText().equals("ГОТОВО") && !button.getText().equals("ВЫХОД")) {
                    if (namesOfSeats.contains(button.getId())){
                        button.setStyle("-fx-background-color: ff8713;");
                        button.setDisable(true);
                    }
                }

            }
        }
    }

    void onDoneButtonClick(){
        MainWindowController.selectedSeatNumber = seatNumber;
        int number = Integer.parseInt(seatNumber.substring(1));
        //System.out.println(number);
        if(number <= 3){
            MainWindowController.currentPrice = 25000;
        } else if(number > 3 && number <= 10){
            MainWindowController.currentPrice = 15000;
        } else MainWindowController.currentPrice = 10000;

        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    void paintAllButtons(){
        for (Node node : b737pane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if(!button.getText().equals("EXIT") && !button.getText().equals("ГОТОВО") && !button.getText().equals("ВЫХОД")) button.setStyle("-fx-background-color: e3efee;");
            }
        }
    }

}
