import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RestaurantClient {

    String serverAddress = "127.0.0.1";
    int serverPort = 33333;
    String[] clientsName = new String[3];
    private HelloRestaurantClient hello;
    public RestaurantClient(String username,String password,HelloRestaurantClient hello) {
        this.hello=hello;
        try {
            
            //System.out.println("Entry of try in restaurant client ");
            Scanner scanner = new Scanner(System.in);
           // System.out.print("Enter name of the Restaurantclient: ");
            clientsName[0]="Restaurant";
            //scanner.nextLine();
            clientsName[1]=username;
            //System.out.println("Enter the password of the restaurant: ");
            clientsName[2]= password;

            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write(clientsName);
            new ReadThreadRestaurantClient(socketWrapper,hello);
            
            //new WriteThreadRestaurantClient(socketWrapper, clientsName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // @Override
    // public void start(Stage primaryStage) throws Exception {
    //     // TODO Auto-generated method stub
    //      FXMLLoader loader = new FXMLLoader();  //Obj of FXMLLoader
    //     loader.setLocation(getClass().getResource("login.fxml"));  //where i will load 
    //     Parent root = loader.load();
    //     //TableViewController controller = loader.getController();
    //     //controller.load();
    //     primaryStage.setTitle("Log in view");
    //     primaryStage.setScene(new Scene(root, 400, 300));
    //     primaryStage.show();

    // }

    // public static void main(String username, String password) {

    //     new RestaurantClient(serverAddress, serverPort, username, password);

    // }
    // public static void main(String[] args) {
    //     launch(args);
    // }
}
