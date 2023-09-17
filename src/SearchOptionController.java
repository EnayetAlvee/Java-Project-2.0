import java.util.Vector;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchOptionController {
    HelloCustomerClient main;
    Restaurant myRestaurant;
    private Vector<Restaurant> restaurantlist;
    private String customer_name;
    ObservableList<Restaurant> restaurant_data;
    ObservableList<Food> food_data;
    @FXML
    private Label customer_name_label;

    @FXML
    private TableView foodTableView;

    @FXML
    private Button homepage_button;

    @FXML
    private TextField resId_textfield;

    @FXML
    private TextField resName_textfield;

    @FXML
    private TableView resTableview;

    @FXML
    private TextField res_zipcode_textfiled;

    @FXML
    Label load_food_item_label;

    @FXML
    private Button reset_button;

    @FXML
    void Back_to_Homepage_Action(ActionEvent event) throws Exception {
        main.showHomePage(restaurantlist, customer_name);
    }

    @FXML
    public void Load_food_menu_Button(ActionEvent event) {
        FoodTableInitializeColumns();
        food_data = FXCollections.observableArrayList(
                myRestaurant.getAllFoods()
        // new Food(1, "a", "b", 50)
        );

        foodTableView.setEditable(true);
        foodTableView.setItems(food_data);

    }

    // tableview initialization
    private void initializeColumns() {
        TableColumn<Restaurant, Integer> resId_col = new TableColumn<>("ID");
        resId_col.setMinWidth(50);
        resId_col.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Restaurant, String> resName_col = new TableColumn<>("Restaurant Name");
        resName_col.setMinWidth(80);
        resName_col.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Restaurant, String> resScore_col = new TableColumn<>("Score");
        resScore_col.setMinWidth(50);
        resScore_col.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("score"));

        TableColumn<Restaurant, String> resPrice_col = new TableColumn<>("Price");
        resPrice_col.setMinWidth(80);
        resPrice_col.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("price"));

        TableColumn<Restaurant, String> zip_code_col = new TableColumn<>("Zip_code");
        zip_code_col.setMinWidth(80);
        zip_code_col.setCellValueFactory(new PropertyValueFactory<>("zip_code"));

        TableColumn<Button, String> buttonCol = new TableColumn<>("Food Menu");
        buttonCol.setMinWidth(80);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        // TableColumn<Food,Integer> order_count_col = new TableColumn<>("Order_Count");
        // order_count_col.setMinWidth(80);
        // order_count_col.setCellValueFactory(new PropertyValueFactory<Food,
        // Integer>("count")); // , order_count_col

        resTableview.setEditable(true);
        // resTableview.getColumns().addAll(resId_col, resName_col, resPrice_col,
        // zip_code_col);

        resTableview.getColumns().addAll(resId_col, resScore_col, resName_col, resPrice_col, zip_code_col);

    }

    @FXML
    void SearchByIdAction(ActionEvent event) {
        int restaurant_id = Integer.parseInt(resId_textfield.getText());
        initializeColumns();
        System.out.println("inside the search");
        int count = 0;
        for (Restaurant x : restaurantlist) {
            if (x.getId() == restaurant_id) {
                this.myRestaurant = x;
                break;
            }
            count++;
        }

        System.out.println("inside search indide");
        restaurant_data = FXCollections.observableArrayList(
                restaurantlist.get(count));

        resTableview.setEditable(true);
        resTableview.setItems(restaurant_data);

    }

    @FXML
    void SearchByZipcodeAction(ActionEvent event) {
        String zip_code = res_zipcode_textfiled.getText();
        initializeColumns();
        System.out.println(zip_code);
        int count = 0;
        for (Restaurant x : restaurantlist) {
            if (x.getZip_code().equals(zip_code)) {
                this.myRestaurant = x;
                break;
            }
            count++;
        }

        System.out.println("inside search indide");
        restaurant_data = FXCollections.observableArrayList(
                restaurantlist.get(count));

        resTableview.setEditable(true);
        resTableview.setItems(restaurant_data);

    }

    private void FoodTableInitializeColumns() {
        TableColumn<Food, String> CategoryNameCol = new TableColumn<>("Category");
        CategoryNameCol.setMinWidth(80);
        CategoryNameCol.setCellValueFactory(new PropertyValueFactory<>("catagory"));

        TableColumn<Food, String> FoodnameCol = new TableColumn<>("Food_Name");
        FoodnameCol.setMinWidth(80);
        FoodnameCol.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));

        TableColumn<Food, String> PriceValueCol = new TableColumn<>("Price");
        PriceValueCol.setMinWidth(80);
        PriceValueCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        // TableColumn<Food,Integer> order_count_col = new TableColumn<>("Order_Count");
        // order_count_col.setMinWidth(80);
        // order_count_col.setCellValueFactory(new PropertyValueFactory<Food,
        // Integer>("count")); // , order_count_col

        foodTableView.setEditable(true);
        foodTableView.getColumns().addAll(CategoryNameCol, FoodnameCol, PriceValueCol);
    }

    @FXML
    void SearchByNameAction(ActionEvent event) {
        String name = resName_textfield.getText();
        initializeColumns();
        System.out.println(name);
        int count = 0;
        for (Restaurant x : restaurantlist) {
            if (x.getName().equalsIgnoreCase(name)) {
                this.myRestaurant = x;
                break;
            }
            count++;
        }

        System.out.println("inside search indide");
        restaurant_data = FXCollections.observableArrayList(
                restaurantlist.get(count));

        resTableview.setEditable(true);
        resTableview.setItems(restaurant_data);

    }

    @FXML
    void logout_Button_action(ActionEvent event) throws Exception {
        main.showLoginPage();
    }

    @FXML
    void reset_button_action(ActionEvent event) {
        resId_textfield.setText(null);
        resName_textfield.setText(null);
        res_zipcode_textfiled.setText(null);
    }

    public void setMain(HelloCustomerClient hello) {
        this.main = hello;
    }

    public void init(Vector<Restaurant> restaurants, String name) {
        this.restaurantlist = restaurants;
        this.customer_name = name;
        customer_name_label.setText(customer_name);
    }

}
