
import java.io.IOException;
import java.util.Vector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class HelloCustomerClient  extends Application {
    private Stage stage;
    private Vector<Restaurant> restaurantlist;
    String customer_name;
    SocketWrapper socketWrapper;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        // FXMLLoader loader = new FXMLLoader();  //Obj of FXMLLoader
        // loader.setLocation(getClass().getResource("login.fxml"));  //where i will load 
        // Parent root = loader.load();
        // LoginController controller = loader.getController();
        // controller.setMain(this);
        // //controller.load();
        // stage.setTitle("Login pannel of Restaurant");
        // stage.setScene(new Scene(root, 500, 300));
        // stage.show();
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerLogin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CustomerLoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }


    public  void showHomePage(Vector<Restaurant>restaurantList,String name) throws IOException {   //for backward perpose
        this.restaurantlist=restaurantList;
        //this.socketWrapper=socketWrapper;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Customer.fxml"));
        Parent root = loader.load();
        this.customer_name=name;
        //Loading the controller
        HomeCustomerController controller = loader.getController();
        controller.setmain(this);
        controller.init(restaurantlist,name,socketWrapper);
        //controller.setmain(this);
        //controller.load();


        // Set the primary stage
        stage.setTitle("Customer Home");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }


    public  void showHomePage(Vector<Restaurant>restaurantList,String name,SocketWrapper socketWrapper) throws Exception {
        this.restaurantlist=restaurantList;
        this.socketWrapper=socketWrapper;
        System.out.println(socketWrapper);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Customer.fxml"));
        Parent root = loader.load();
        this.customer_name=name;
        //Loading the controller
        HomeCustomerController controller = loader.getController();
        controller.setmain(this);
        controller.init(restaurantlist,name,socketWrapper);
        //controller.setmain(this);
        //controller.load();


        // Set the primary stage
        stage.setTitle("Customer Home");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }


    public void showSearchRestauantOptions() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchRestaurant.fxml"));
        Parent root = loader.load();

        //loading the controller
        SearchOptionController controller = loader.getController();
        controller.setMain(this);
        controller.init(restaurantlist,customer_name);

        // Set the primary stage
        stage.setTitle("Search Restaurant Options");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct."); 
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
