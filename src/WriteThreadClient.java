import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class WriteThreadClient implements Runnable {

    private Thread thr;
    private Vector<Restaurant> restaurants = new Vector<Restaurant>();
    Operations myOperations = new Operations();
    private SocketWrapper socketWrapper;
    String name;  //Customer name

    public WriteThreadClient(SocketWrapper socketWrapper, String name, Vector<Restaurant> restaurants) {
        this.socketWrapper = socketWrapper;
        this.name = name;
        this.restaurants = restaurants;
        myOperations.setRestaurants(this.restaurants);
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            int ch = 0, option = 0;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Main Menu");
                System.out.println("1) Search Restaurants");
                System.out.println("2) Search Food Items");
                System.out.println("3) Make order items");
                // System.out.println("4) Add Food Items to the Menu");
                System.out.println("5) Exit System");
                ch = scanner.nextInt();
                if (ch == 1) {
                    System.out.println("Restaurant Searching Options");
                    System.out.println("1) By Name");
                    System.out.println("2) By Score");
                    System.out.println("3) By Category");
                    System.out.println("4) By Price ");
                    System.out.println("5) By Zip Code");
                    System.out.println("6) Different Category Wise List of Restaurants");
                    System.out.println("7) Back to Main Menu");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println("Enter Restaurant Name: ");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            myOperations.searchRestaurant(name);
                            break;
                        case 2:
                            System.out.println("Enter Restaurant Score range: ");
                            System.out.print("Lowest Score: ");
                            double Lowestscore = scanner.nextDouble();
                            System.out.println();
                            System.out.print("Highest Score: ");
                            double highestscore = scanner.nextDouble();
                            System.out.println();
                            myOperations.DisplayRestaurants(Lowestscore, highestscore);
                            break;
                        case 3:
                            System.out.println("Enter Category: ");
                            scanner.nextLine();
                            String category = scanner.nextLine();
                            myOperations.searchRestaurantByCat(category);
                            break;
                        case 4:
                            System.out.println("Enter Price");
                            scanner.nextLine();
                            String price = scanner.nextLine();
                            myOperations.searchRestaurantByprice(price);
                            break;
                        case 5:
                            System.out.println("Enter ZipCode");
                            scanner.nextLine();
                            String zipcode = scanner.nextLine();
                            myOperations.searchRestaurantByZipcode(zipcode);
                            break;
                        case 6:
                            System.out.println("Catefory Wise Restaurant names: ");
                            myOperations.CategoryWiseDisplay();
                            break;
                        case 7:
                            System.out.println("Returning To back main menu..........");
                            break;
                        default:
                            System.out.println("Enter Correct Option");
                            break;
                    }
                } else if (ch == 2) {
                    System.out.println("Food Item Searching Options");
                    System.out.println("1) By Name");
                    System.out.println("2) By Name in a Given Restaurant");
                    System.out.println("3) By Category");
                    System.out.println("4) By Category in a Given Restaurant");
                    System.out.println("5) By Price Range");
                    System.out.println("6) By Price Range in a Given Restaurant");
                    System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                    System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                    System.out.println("9) Back to Main Menu");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println("Enter Food name:");
                            scanner.nextLine();
                            String foodname = scanner.nextLine();
                            myOperations.searchFoodItems(foodname);
                            break;
                        case 2:
                            System.out.println("Enter food name");
                            scanner.nextLine();
                            foodname = scanner.nextLine();
                            System.out.println("Enter Restaurant name");
                            // scanner.nextLine();
                            String resName = scanner.nextLine();
                            myOperations.SearchfoodInResTaurant(foodname, resName);
                            break;
                        case 3:
                            System.out.println("Enter Category: ");
                            scanner.nextLine();
                            String catname = scanner.nextLine();
                            myOperations.DisplayfoodFromCategory(catname);
                            break;
                        case 4:
                            System.out.println("Enter food category");
                            scanner.nextLine();
                            String foodCat = scanner.nextLine();
                            System.out.println("Enter Restaurant name: ");
                            scanner.nextLine();
                            resName = scanner.nextLine();
                            myOperations.DisplayfoodFromCategoryInRestaurant(resName, foodCat);
                            break;
                        case 5:
                            System.out.println("Enter Price range");
                            System.out.print("Lowest Price: ");
                            double lowPrice = scanner.nextDouble();
                            System.out.println();
                            System.out.print("Highwest Price: ");
                            double highPrice = scanner.nextDouble();
                            System.out.println();
                            myOperations.searchFoodwithPrice(lowPrice, highPrice);
                            break;
                        case 6:
                            System.out.println("Enter Price range and restauratn name");
                            System.out.print("Lowest Price: ");
                            lowPrice = scanner.nextDouble();
                            System.out.println();
                            System.out.print("Lowest Price: ");
                            highPrice = scanner.nextDouble();
                            System.out.println();
                            System.out.println("Restaurant Name: ");
                            scanner.nextLine();
                            resName = scanner.nextLine();
                            myOperations.SearchfoodInResTaurantwithprice(lowPrice, highPrice, resName);
                            break;
                        case 7:
                            System.out.println("Enter Restaurant Name: ");
                            scanner.nextLine();
                            resName = scanner.nextLine();
                            myOperations.DisplayCostliestItemOnRestaurant(resName);
                            break;
                        case 8:
                            myOperations.DisplayAllFoodAllrestaurants();
                            break;
                        case 9:
                            break;
                        default:
                            break;
                    }

                } else if (ch == 3) {
                    System.out.println("Making New Order from client");
                    for (Restaurant x : restaurants) {
                        x.DisplayRestaurant();
                    }
                    System.out.println("Select parameter by which you want to search Restaurantaurants");
                    System.out.println("1.By name\n2.By zip code\n3.By Id");
                    int choice = scanner.nextInt();
                    int flag = 0;
                    switch (choice) {
                        case 1:
                            System.out.println("Enter Restaurant Name: ");
                            scanner.nextLine();
                            String name = scanner.nextLine();

                            for (Restaurant x : restaurants) {

                                if (x.getName().equals(name)) {
                                    flag = 1;
                                    x.showFoodDeatails();
                                    System.out.println("how many Foods you want to order: ");

                                    int order_item_no = scanner.nextInt(); //numberr of orders
                                    scanner.nextLine();
                                    Vector<String> ordered_foods = new Vector<String>();

                                    if (order_item_no > x.getAllFoods().size()) {
                                        break;
                                    }
                                    ordered_foods.add(x.getName());// first String will contain the name of the
                                                                   // Restaurant
                                    for (int i = 0; i < order_item_no; i++) {
                                        String foodname = scanner.nextLine();
                                        ordered_foods.add(foodname);
                                    }
                                    socketWrapper.write(ordered_foods);
                                    break;
                                }

                            }
                            if (flag == 0) {
                                System.out.println("No Restaurant found");
                            }
                            break;
                        case 2:
                            System.out.println("Enter Restaurant Zip Code: ");
                            scanner.nextLine();
                            String zipcode = scanner.nextLine();
                            for (Restaurant x : restaurants) {
                                if (x.getZip_code().equals(zipcode)) {
                                    flag = 1;
                                    x.showFoodDeatails();
                                    System.out.println("how many Foods you want to order: ");
                                    int order_item_no = scanner.nextInt();
                                    scanner.nextLine();
                                    Vector<String> ordered_foods = new Vector<String>();
                                    if (order_item_no > x.getAllFoods().size()) {
                                        break;
                                    }
                                    ordered_foods.add(x.getName());// first String will contain the name of the
                                                                   // Restaurant
                                    for (int i = 0; i < order_item_no; i++) {
                                        String foodname = scanner.nextLine();
                                        ordered_foods.add(foodname);
                                    }
                                    socketWrapper.write(ordered_foods);
                                    break;
                                }

                            }
                            if (flag == 0) {
                                System.out.println("No Restaurant found");
                            }
                            break;
                        case 3:
                            System.out.println("Enter Restaurant Id: ");
                            scanner.nextLine();
                            int id = scanner.nextInt();
                            for (Restaurant x : restaurants) {

                                if (x.getId() == id) {
                                    flag = 1;
                                    x.showFoodDeatails();
                                    System.out.println("how many Foods you want to order: ");
                                    int order_item_no = scanner.nextInt();
                                    scanner.nextLine();
                                    Vector<String> ordered_foods = new Vector<String>();
                                    if (order_item_no > x.getAllFoods().size()) {
                                        break;
                                    }
                                    ordered_foods.add(x.getName());// first String will contain the name of the
                                                                   // Restaurant
                                    for (int i = 0; i < order_item_no; i++) {
                                        String foodname = scanner.nextLine();
                                        ordered_foods.add(foodname);
                                    }
                                    socketWrapper.write(ordered_foods);
                                    break;
                                }

                            }
                            if (flag == 0) {
                                System.out.println("No Restaurant found");
                            }
                            break;
                        default:
                            break;
                    }
                } else if (ch == 5) {
                    break;
                } else {
                    System.out.println("Enter correct number: ");
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
