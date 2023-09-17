
import java.io.IOException;

import javafx.application.Platform;

public class ReadThreadRestaurantClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    Restaurant obj;
    private HelloRestaurantClient hello;

    public ReadThreadRestaurantClient(SocketWrapper socketWrapper, HelloRestaurantClient hello) {
        this.hello = hello;
        this.socketWrapper = socketWrapper;
        System.out.println("readThreadRestaurantClient "+socketWrapper);
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            System.out.println("Entry in read thread");
            while (true) {
                System.out.println("inside run of ReadThread client");
                Object o = socketWrapper.read();
                System.out.println("it can read obj");
                if (o instanceof String) { // log in ussuccessfull
                    System.out.println("inside else of ReadThread client");
                    String s = (String) o;
                    System.out.println(s);
                    if (s.equals("Invalid name or password")) {

                        socketWrapper.closeConnection();
                    }
                }
                if (this.obj != null) {
                    System.out.println("Details of items sell");
                    System.out.println("****************************************************************");
                    System.out.println(" FoodName" + "          counts");
                    for (Food f : this.obj.getAllFoods()) {
                        System.out.println(f.getName() + "    " + f.getCount());
                    }
                    System.out.println("****************************************************************");
                }
                if (o instanceof Restaurant) { // log in successfull
                    System.out.println("inside if of ReadThread client");
                    this.obj = (Restaurant) o;
                    this.obj.DisplayRestaurant();
                    System.out.println("After restaurant display");

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                hello.showHomePage(obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    });

                } else if (o instanceof Food) { // passed food items through client and server
                    Food passedfood = (Food) o;
                    System.out.println("Food instance passed");
                    passedfood.DisplayFoodWithId();
                    for (Food f : this.obj.getAllFoods()) {
                        if (f.getName().equals(passedfood.getName())) {
                            f.IncrementFoodCount();
                        }
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("new homepage of res");
                                hello.showHomePage(obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    });

                }
            }
        } catch (

        Exception e) {
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
