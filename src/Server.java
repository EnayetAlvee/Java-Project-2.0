import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Server {

    private static final String INPUT_FILE_NAME = "E:\\JAVA_FINAL\\Project2\\restaurant.txt";
    private static final String INPUT_FILE_NAME1 = "E:\\JAVA_FINAL\\Project2\\menu.txt";
    //List<Restaurant> restaurantList = new ArrayList<>();
    Vector<Restaurant> restaurantList = new Vector<>();
    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;

    Server() throws Exception {

        clientMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            // System.out.println("printsljfl");
            // System.out.println(line);
            String[] array = line.split(",", -1);
            String[] category = new String[3];

            for (int i = 6; i < array.length; i++) {
                // System.out.println(array[i]);
                category[i - 6] = array[i];
            }
            Restaurant tempRestaurant = new Restaurant(Integer.parseInt(array[0]),
                    array[1], Double.parseDouble(array[2]),
                    array[3],
                    array[4], Integer.parseInt(array[5]), category);
            restaurantList.add(tempRestaurant);
        }
        // myOperations.setrestaurantList(restaurantList);
        br.close();
        br = new BufferedReader(new FileReader(INPUT_FILE_NAME1));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            // System.out.println(line);
            String[] array = line.split(",", -1);
            int resId = Integer.parseInt(array[0]);
            for (Restaurant x : restaurantList) {
                if (x.getId() == resId) {
                    x.addFood(array[1], array[2], Double.parseDouble(array[3]));
                }

            }
        }
        for (int i = 0; i < restaurantList.size(); i++) {
            restaurantList.get(i).DisplayRestaurant();
        }

        /*
         * public void addFoodToMenu(int resId, String category, String foodname, double
         * price) { // by id
         * if (!isResAvailable(resId)) {
         * System.out.
         * println("No such resturant is found .enter Restaurant Name Correctly");
         * return;
         * }
         * 
         * 
         * }
         */
       
        //Server Port ans Server socket is opeining
         try {
            System.out.println("Serer is connneting .........................");
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept(); // waiting for client
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }

    }


    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket); // create socket wrapper of client
        Object myObject = socketWrapper.read();

        if (myObject != null) {
            System.out.println("serve starts");
            String[] str = (String[]) myObject;
            String clientName = str[1];
            System.out.println(str[0]+" "+str[1]+" "+str[2]);
            clientMap.put(clientName.toLowerCase(), socketWrapper);
            int flag = 0;
            if (str[0].equals("Restaurant")) {
                System.out.println("inside if of Restaurant");
                for (Restaurant x : restaurantList) {
                    if (x.getName().equalsIgnoreCase(str[1]) && x.getpassword().equals(str[2])) {
                        System.out.println("password mathced");
                        socketWrapper=clientMap.get(clientName.toLowerCase());
                        socketWrapper.write(x);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    System.out.println("Invalid name or password");
                    socketWrapper=clientMap.get(clientName.toLowerCase());
                    socketWrapper.write("Invalid name or password");
                }
            } else if (str[0].equals("Customer")) {
                System.out.println("Serve for customer");
                socketWrapper=clientMap.get(clientName.toLowerCase());
                socketWrapper.write(restaurantList);
            }
        }
        new ReadThreadServer(clientMap, socketWrapper,restaurantList);
        //new Administration(restaurantList);
    }

    public static void main(String args[]) throws Exception {
        new Server();
    }
}
