import java.io.Serializable;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class Operations implements Serializable {
    int giveId[] = new int[100000];
    int Rescount = 0;
    private Vector<Restaurant> restaurantlList = new Vector<Restaurant>();
    List<String> CATEGORY_LIST = new ArrayList<>(); // list of all categories of all restaurants
    List<Food> food_items = new ArrayList<>(); // contain all food items of all restaurants

    public void setRestaurants(Vector<Restaurant> restaurants) {
        restaurantlList = restaurants;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantlList;
    }

    private boolean checkId(int id) {
        if (Rescount == 0)
            return false;
        for (int x : giveId) {
            if (x == id) {
                return true;
            }
        }
        return false;
    }

    private boolean checkName(String name) {
        for (Restaurant x : restaurantlList) {
            if (x.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSame(String x) {
        for (String i : CATEGORY_LIST) {
            if (i.equals(x))
                return true;
        }
        return false;
    }

    private void setCategoryList() {
        for (Restaurant x : restaurantlList) {
            for (int i = 0; i < x.getCatCounter(); i++) {
                if (!isSame(x.getCategory()[i])) {
                    CATEGORY_LIST.add(x.getCategory()[i]);
                }
            }
        }
    }

    public void CategoryWiseDisplay() {
        setCategoryList();
        for (int i = 0; i < CATEGORY_LIST.size(); i++) {
            System.out.print(CATEGORY_LIST.get(i) + ": ");
            for (Restaurant x : restaurantlList) {
                for (int j = 0; j < x.getCatCounter(); j++) {
                    if (x.getCategory()[j].equals(CATEGORY_LIST.get(i)))
                        System.out.print("," + x.getName());
                }
            }
            System.out.println();
        }
    }

    public void addRestaurant(int id, String name, double score, String zipCode, String catName[]) {
        if (checkId(id)) {
            System.out.println("Your given Id is already Registered .Please Enter a new id");
            return;
        }
        giveId[Rescount] = id;
        Rescount++;
        restaurantlList.add(new Restaurant(id, name, score, zipCode, catName));
    }

    public void addRestaurant(int id, String name, double score, String price, String zipCode, String catName[]) {
        if (checkId(id)) {
            System.out.println("Your given Id is already Registered .Please Enter a new id");
            return;
        }
        if (checkName(name)) {
            System.out.println("Your given Restaurant name is already Registered .Please Enter a new id");
            return;
        }
        giveId[Rescount] = id;
        Rescount++;
        restaurantlList.add(new Restaurant(id, name, score, price, zipCode, catName));
    }

    private boolean isResAvailable(String resname) { // equals
        for (Restaurant x : restaurantlList) {
            if (x.getName().equals(resname))
                return true;
        }
        return false;
    }

    private boolean isResAvailable(int id) {
        for (Restaurant x : restaurantlList) {
            if (x.getId() == id)
                return true;
        }
        return false;
    }

    public void addFoodToMenu(String resName, String category, String foodname, double price) { // equals
        if (!isResAvailable(resName)) {
            System.out.println("No such resturant is found .Enter Restaurant Name Correctly");
            return;
        }
        for (Restaurant x : restaurantlList) {
            if (x.getName().equals(resName)) {
                x.addFood(category, foodname, price);
            }

        }

    }

    public void searchRestaurant(String name) { // by name
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            if (x.getName().toLowerCase().contains(name.toLowerCase())) {
                x.DisplayRestaurant();
                flag++;
            }
        }
        if (flag == 0) {
            System.out.println("No such Restaurant found with this name.");
        }
    }

    public void DisplayRestaurants(double a, double b) { // display deatails of the restaurants between score by score
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            if (a <= x.getScore() && b >= x.getScore()) {
                flag++;
                x.DisplayRestaurant();
                System.out.println();
            }
        }
        if (flag == 0)
            System.out.println("No such restaurant with this score range");
    }

    public void searchRestaurantByCat(String name) { // by category
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            for (int i = 0; i < x.getCatCounter(); i++) {
                if (x.getCategory()[i].toLowerCase().contains(name.toLowerCase())) {
                    x.DisplayRestaurant();
                    flag++;
                }
            }
        }
        if (flag == 0)
            System.out.println("No such restaurant found with this Category");
    }

    public void searchRestaurantByprice(String price) { // by price
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            if (x.getPrice().equals(price)) {
                flag++;
                x.DisplayRestaurant();
            }
        }
        if (flag == 0)
            System.out.println("No such restaurant with this price");
    }

    public void searchRestaurantByZipcode(String zipcode) { // by zip code
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            if (x.getZip_code().contains(zipcode)) {
                x.DisplayRestaurant();
                flag++;
            }
        }
        if (flag == 0)
            System.out.println("No such restaurant with this zipcode");
    }

    private void MakeFoodItemList() { // make a list of all foods of all restaurants
        for (Restaurant x : restaurantlList) {
            for (int i = 0; i < x.getAllFoods().size(); i++) {
                food_items.add(x.getAllFoods().get(i));
            }
        }
    }

    public void searchFoodItems(String name) { // search food item by name
        MakeFoodItemList();
        int flag = 0;
        for (int i = 0; i < food_items.size(); i++) {
            if (food_items.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                food_items.get(i).DisplayFoodWithId();
                flag++;
            }
        }
        if (flag == 0) {
            System.out.println("No such food item with this name");
            return;
        }
        System.out.println();
    }

    public void SearchfoodInResTaurant(String foodname, String Resname) {
        int flag = 0;
        int flag1 = 0;
        System.out.println("foods are: ");
        for (Restaurant x : restaurantlList) {
            if (x.getName().toLowerCase().contains(Resname.toLowerCase())) {
                flag++;
                x.searchFoodByName(foodname);
            }
        }
        if (flag == 0) {
            System.out.println("No restaurant found");
            return;
        }
        if (flag1 == 0) {
            System.out.println("No such food item with this name in this restaurant");
        }

    }

    public void DisplayfoodFromCategory(String catname) {
        int flag = 0;
        for (Food f : food_items) {
            if (f.getCatagory().toLowerCase().contains(catname.toLowerCase())) {
                flag++;
                f.DisplayFoodWithId();
            }
        }
        if (flag == 0) {
            System.out.println("No such Food with this category");
        }
    }

    public void DisplayfoodFromCategoryInRestaurant(String resName, String foodCat) {
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            if (x.getName().toLowerCase().contains(resName.toLowerCase())) {
                x.searchFoodByCategory(foodCat);
            }
        }
        if (flag == 0) {
            System.out.println("No such food with this category in this restaurant");
        }
    }

    public void searchFoodwithPrice(double low, double high) {
        for (Restaurant x : restaurantlList) {
            for (Food f : x.getAllFoods()) {
                if (f.getPrice() >= low && f.getPrice() <= high) {
                    f.DisplayFoodWithId();
                }
            }
        }
    }

    public void SearchfoodInResTaurantwithprice(double low, double high, String Resname) {
        for (Restaurant x : restaurantlList) {
            if (x.getName().toLowerCase().contains(Resname.toLowerCase())) {

            }
        }
    }



    public void DisplayCostliestItemOnRestaurant(String resname) {
        int flag = 0;
        for (Restaurant x : restaurantlList) {
            if (x.getName().toLowerCase().contains(resname.toLowerCase())) {
                flag++;
                x.DisplayMaxPriceFoods();
            }
        }
        if (flag == 0) {
            System.out.println("No such Restauratnt is found");
        }
    }

    public void DisplayAllFoodAllrestaurants() {
        for (Restaurant x : restaurantlList) {
            x.DisplayRestaurantFoods();
        }
    }

    public void addFoodToMenu(int resId, String category, String foodname, double price) { // by id
        if (!isResAvailable(resId)) {
            System.out.println("No such resturant is found .enter Restaurant Name Correctly");
            return;
        }
        for (Restaurant x : restaurantlList) {
            if (x.getId() == resId) {
                x.addFood(category, foodname, price);
            }

        }

    }

    public void printRes() { // in console
        for (Restaurant x : restaurantlList) {
            System.out.print(
                    x.getId() + "," + x.getName() + "," + x.getScore() + "," + x.getPrice() + "," + x.getZip_code());
            x.printCategory();
            System.out.println();
        }
    }

}
