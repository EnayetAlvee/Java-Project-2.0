
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class CustomerLoginController {

    private HelloCustomerClient main;

    @FXML
    private TextField resNameUserText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = resNameUserText.getText();
        String password = passwordText.getText();
        new Client(userName, password,main);
        //LoginDTO loginDTO = new LoginDTO();
        //loginDTO.setUserName(userName);
        //loginDTO.setPassword(password);
        // try {
        //     main.getSocketWrapper().write(loginDTO);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    @FXML
    void resetAction(ActionEvent event) {
        resNameUserText.setText(null);
        passwordText.setText(null);
    }

    void setMain(HelloCustomerClient main) {
        this.main = main;
    }

}


