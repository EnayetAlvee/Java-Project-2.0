
import java.util.Scanner;
import java.util.Vector;

import javafx.application.Platform;

public class Client {
    String[] cn = new String[3];
    private Vector<Restaurant> restaurants = new Vector<Restaurant>();

    String serverAddress = "127.0.0.1";
    int serverPort = 33333;
    HelloCustomerClient client=new HelloCustomerClient();
    public Client(String username,String password,HelloCustomerClient hello) {
        try {
            this.client=hello;
            System.out.print("Enter name of the client: ");
            //Scanner scanner = new Scanner(System.in);
            String Name = username;
            cn[0] = "Customer";
            cn[1] = Name;
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write(cn);
            // new ReadThreadClient(socketWrapper);

            Object o = socketWrapper.read();
            if (o instanceof String) {
                System.out.println("inside else of ReadThread client");
                String s = (String) o;
                System.out.println(s);
                if (s.equals("Invalid name or password")) {
                    socketWrapper.closeConnection();
                }
            } else {   //client connection done
                System.out.println("inside the client read thread");
                restaurants = (Vector<Restaurant>) o;
                System.out.println("recieved restaurants");
                for (Restaurant x : restaurants) {
                    x.DisplayRestaurantFoods();
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                client.showHomePage(restaurants,cn[1],socketWrapper);//cn[1] is the name of the customer
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    });

                }
            }
            new WriteThreadClient(socketWrapper, Name, restaurants);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    // public static void main(String args[]) {

    // new Client(serverAddress, serverPort);
    // }
}
