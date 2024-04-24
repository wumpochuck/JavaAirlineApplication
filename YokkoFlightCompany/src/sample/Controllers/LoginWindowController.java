package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Animations.AnimationShake;
import sample.Animations.AnimationSwitchWindow;
import sample.DataBaseHandler;
import sample.User;

public class LoginWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane LoginPane;

    @FXML
    private AnchorPane RegistrationPane;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField1;

    @FXML
    private TextField loginField2;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private PasswordField passwordField3;

    @FXML
    private Button registerButton;

    @FXML
    private Button switchRegistrationButton;

    public static User current_user;

    @FXML
    void initialize() {
        initSettings();

        switchRegistrationButton.setOnAction(event -> onSwitchToRegistration());
        backButton.setOnAction(event -> onSwitchToLogin());
        loginButton.setOnAction(event -> { try { onLoginButtonClick(); } catch (SQLException throwables) { throwables.printStackTrace(); } });
        registerButton.setOnAction(event -> { try { onRegisterButtonClick(); } catch (SQLException throwables) { throwables.printStackTrace(); } });

        assert LoginPane != null : "fx:id=\"LoginPane\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert RegistrationPane != null : "fx:id=\"RegistrationPane\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginField1 != null : "fx:id=\"loginField1\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginField2 != null : "fx:id=\"loginField2\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField1 != null : "fx:id=\"passwordField1\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField2 != null : "fx:id=\"passwordField2\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField3 != null : "fx:id=\"passwordField3\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert switchRegistrationButton != null : "fx:id=\"switchRegistrationButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }

    private void initSettings(){
        LoginPane.setVisible(true);
        LoginPane.setDisable(false);
        RegistrationPane.setVisible(true);
        RegistrationPane.setDisable(true);
    }

    public void openNewScene(String window) {
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
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

    private void onSwitchToRegistration() {
            AnimationSwitchWindow.switchToRegistration(LoginPane, RegistrationPane);
    }

    private void onSwitchToLogin() {
            AnimationSwitchWindow.switchToLogin(LoginPane, RegistrationPane);
    }

    public void onLoginButtonClick() throws SQLException {
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String login = loginField1.getText();
        String password = passwordField1.getText();
        // Если неправильное имя пользователя
        if(new DataBaseHandler().getUserByLogin(login) == null){
            alert.setContentText("Такого пользователя не существует!");
            alert.showAndWait();
            return;
        }
        User DataBaseUser = new DataBaseHandler().getUserByLogin(login);
        // Если неправильный пароль
        if(!password.equals(DataBaseUser.getPassword())){
            PlayShakeAnimation();
            return;
        }

        alert.setContentText("Вы успешно вошли!");
        alert.showAndWait();

        current_user = new User();
        current_user.setUserId(DataBaseUser.getUserId());
        current_user.setLogin(DataBaseUser.getLogin());
        current_user.setPassword(DataBaseUser.getPassword());
        current_user.setName(DataBaseUser.getName());
        current_user.setSurname(DataBaseUser.getSurname());
        current_user.setMoney(DataBaseUser.getMoney());
        current_user.setRole(DataBaseUser.getRole());

        openNewScene("/sample/Templates/MainWindow.fxml");
    }

    public void onRegisterButtonClick() throws SQLException {
        // Создание "сообщения"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String login = loginField2.getText();
        String password = passwordField2.getText();
        String passwordRepeat = passwordField3.getText();
        // Если такой пользователь уже существует
        if(new DataBaseHandler().getUserByLogin(login) != null){
            alert.setContentText("Такой пользователь уже существует!");
            alert.showAndWait();
            return;
        }
        // Если 2 пароля введены неверно
        if(!password.equals(passwordRepeat)){
            alert.setContentText("Пароли должны совпадать!");
            alert.showAndWait();
            new AnimationShake(passwordField3).play();
            return;
        }
        current_user = new User(login,password);
        new DataBaseHandler().addUser(current_user);

        alert.setContentText("Вы успешно зарегистрировались!");
        alert.showAndWait();
    }

    public void PlayShakeAnimation(){
        new AnimationShake(passwordField1).play();
    }



}
