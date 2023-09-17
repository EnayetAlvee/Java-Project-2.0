
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class HelloRestaurantClient extends Application {
    private Stage stage;
    private Restaurant r;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        FXMLLoader loader = new FXMLLoader();  //Obj of FXMLLoader
        loader.setLocation(getClass().getResource("login.fxml"));  //where i will load 
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setMain(this);
        //controller.load();
        stage.setTitle("Login pannel of Restaurant");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }


    public  void showHomePage(Restaurant R) throws Exception {
        this.r=R;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        //Loading the controller
        HomeController controller = loader.getController();
        controller.setMain(this);
        controller.init("hi from alvee",r);
        controller.setMain(this);
        controller.load();
        // Set the primary stage
        stage.setTitle("Restaurant Home");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.
setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
