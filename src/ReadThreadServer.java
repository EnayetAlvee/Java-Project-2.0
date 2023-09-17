
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    public HashMap<String, SocketWrapper> clientMap;
    Vector<Restaurant> restaurants;

    public ReadThreadServer(HashMap<String, SocketWrapper> map, SocketWrapper socketWrapper,
            Vector<Restaurant> restaurants) {
        this.clientMap = map;
        this.restaurants = restaurants;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o instanceof Vector && !((Vector) o).isEmpty() && ((Vector) o).get(0) instanceof String) {

                    Vector<String> v = (Vector<String>) o; // v is the recieved food list from client and index 0
                                                           // contain the name of restaurant
                    String resName = v.get(0); // it will give me restaurant name
                    String foodarray[] = new String[v.size() - 1];
                    for (int i = 1; i < v.size(); i++) {
                        foodarray[i - 1] = v.get(i);
                    }
                    SocketWrapper nu = clientMap.get(resName.toLowerCase(null));
                    if (nu != null) {
                        nu.write(foodarray); // index start form 1;
                    } // write to the restaurants

                }

                else if (o instanceof Vector && !((Vector) o).isEmpty() && ((Vector) o).get(0) instanceof Food) {
                    Vector<Food> v = (Vector<Food>) o; // v is the recieved food list from client and index 0
                                                       // contain the name of restaurant
                    String resName; // it will give me restaurant name
                    // Food foodarray[] = new Food[v.size() - 1];
                    // for (int i = 1; i < v.size(); i++) {
                    // foodarray[i - 1] = v.get(i);
                    // }
                    System.out.println("passed object from customer is food");
                    // int i = 0;
                    for (Food f : v) {
                        int flag=0;
                        for (Restaurant x : restaurants) {
                            System.out.println("for");
                            if (x.getId() == f.getRestaurantId()) {
                                flag=1;
                                System.out.println("if");
                                resName = x.getName();
                                System.out.println("resname: "+resName);
                                SocketWrapper nu = clientMap.get(resName.toLowerCase());

                                System.out.println("getting socketwrapper"+socketWrapper);
                                System.out.println("yes");
                                nu.write(f); // now server is writing food for restaurant
                                System.out.println("food is written for restaurant");
                                f.DisplayFoodWithId();

                            }
                            if(flag==0){

                            }
                            // index start form 1;
                        } // write to the restaurants
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
