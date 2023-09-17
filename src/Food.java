import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//import javafx.beans.property.SimpleStringProperty;

public class Food implements Serializable {

    private static final long serialVersionUID=5268671505325445379l;
    // SimpleStringProperty simpleCategory;
    // SimpleStringProperty simpleName;
    // SimpleStringProperty simplePrice;
    int restaurantId;
    String catagory;
    String name;
    double price;
    int count = 0;
    
    public Food(int id, String category, String name, double price) {
        // this.simpleCategory = new SimpleStringProperty(category);
        // this.simpleName = new SimpleStringProperty(name);
        // this.simplePrice = new SimpleStringProperty(Double.toString(price));
        this.restaurantId = id;
        this.catagory = category;
        this.name = name;
        this.price = price;
    }

    

//    public void setFoodname(String name) {
//        this.name = name;
//    }

//    public void setCategory(String name) {
//        this.catagory = name;
//    }

//    public void setprice(double n) {
//        this.price = n;
//    }

//    public int getresid() {
//        return this.restaurantId;
//    }

//    public String getCategory() {
//        return this.catagory;
//    }


//    public String getfoodname() {
//        return this.name;
//    }

//    public double getPrice() {

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getCatagory() {
        return catagory;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
//        return this.price;
//    }

//    public int getFoodCount () {
//        return this.count;
//    }


    // public void setcount(int count){
    // this.count=count;
    // }

    // public int getcount(){
    // return this.count;
    // }


    public void IncrementFoodCount() {
        this.count++;
    }

    public void Displayfood() { // category name price
        System.out.println("Category: " + catagory + ", Food Name: " + name + ", Price: " + price);
    }

    public void DisplayFoodWithId() {
        System.out.println("Restaurant id: " + this.restaurantId + ", Category: " + catagory + ", Food Name: " + name
                + " Price: " + price);
    }



    public String toString() {
    return getCatagory() + ", " + name + ", " +
    Double.toString(price)+","+Integer.toString(count);
    }
}
