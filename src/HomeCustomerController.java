import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeCustomerController {
    // private Vector<Restaurant> myrestaurant;
    private HelloCustomerClient main;
    String CustomerClientName;
    SocketWrapper socketWrapper;

    @FXML
    private Label customerName;

    @FXML
    private Button button_search_option;

    @FXML
    private TableView tableView;

    @FXML
    ImageView image;

    ObservableList<Food> data;

    private Vector<Restaurant> restaurantList;

    private Vector<Food> allFoods;

    @FXML
    private TableView Ordered_item_tableView;

    private Vector<Food> Ordered_food_List = new Vector<Food>();

    ObservableList<Food> OrderedFoodData;

    private void makeFoodlist() { // make food list all
        allFoods = new Vector<Food>();
        for (int i = 0; i < restaurantList.size(); i++) {
            allFoods.addAll(restaurantList.get(i).getAllFoods());
        }
    }

    public void init(Vector<Restaurant> restaurants, String name, SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        System.out.println("socekwrapper in homecustomer " + socketWrapper);
        this.CustomerClientName = name;
        customerName.setText(name);
        this.restaurantList = restaurants;
        Image img = new Image(Main.class.getResourceAsStream("burger.jpg"));
        image.setImage(img);
        makeFoodlist();
        initializeColumns();
        order_table_initialize();
    }

    private void initializeColumns() {
        TableColumn<Food, Integer> idNameCol = new TableColumn<>("Restaurant ID");
        idNameCol.setMinWidth(80);
        idNameCol.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));

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

        tableView.setEditable(true);
        tableView.getColumns().addAll(idNameCol, CategoryNameCol, FoodnameCol, PriceValueCol);
        // Ordered_item_tableView.getColumns().addAll(idNameCol,CategoryNameCol,FoodnameCol,PriceValueCol);
    }

    private void order_table_initialize() {
        TableColumn<Food, Integer> idNameCol = new TableColumn<>("Restaurant ID");
        idNameCol.setMinWidth(80);
        idNameCol.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));

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

        // tableView.setEditable(true);
        // tableView.getColumns().addAll(idNameCol, CategoryNameCol, FoodnameCol,
        // PriceValueCol);
        Ordered_item_tableView.getColumns().addAll(idNameCol, CategoryNameCol, FoodnameCol, PriceValueCol);

    }

    @FXML
    void LoadMenu(ActionEvent event) {
        Platform.runLater(() -> {
            for (Food f : allFoods) {
                f.DisplayFoodWithId();
            }
            System.out.println("myname is alvee");
            data = FXCollections.observableArrayList(
                    allFoods);

            tableView.setEditable(true);
            tableView.setItems(data);

        });
        // listView.getSelectionModel().selectedItemProperty().addListener(
        // (observableValue, oldValue, newValue) -> {
        // Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        // a.setContentText(newValue);
        // a.showAndWait();
        // });
    }

    @FXML
    void searchOptionAction(ActionEvent event) throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    main.showSearchRestauantOptions();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setmain(HelloCustomerClient main) {
        this.main = new HelloCustomerClient();
        this.main = main;
    }

    public void load() {

    }

    @FXML
    void Place_order_button(ActionEvent event) {
        System.out.println("inside button of order");
        Platform.runLater(() -> {
            System.out.println("inside runnale");
            for (Food f : Ordered_food_List) {
                f.DisplayFoodWithId();
            }
            System.out.println("hi there");
            OrderedFoodData = FXCollections.observableArrayList(
                    Ordered_food_List);
            Ordered_item_tableView.setEditable(true);
            Ordered_item_tableView.setItems(OrderedFoodData);

        });
    }

    @FXML
    void tableViewMouseClicked(MouseEvent event) {
        System.out.println("hi");
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("hi ion event");
                Food food = (Food) tableView.getSelectionModel().getSelectedItem();
                food.DisplayFoodWithId();
                Ordered_food_List.add(food);
                System.out.println("hi mouse click");
            }
        });
    }

    @FXML
    void ResetAllButton(ActionEvent event) {
        // init(restaurantList,CustomerClientName);
        Ordered_food_List.clear();
        OrderedFoodData.clear();
        Ordered_item_tableView.setItems(OrderedFoodData);
    }

    @FXML
    void ConfirmAllOrderButtonAction(ActionEvent event) throws IOException {
        // Vector<String> order_item_name=new Vector<String>();
        // for(int i=0;i<Ordered_food_List.size();i++){
        // order_item_name.add(Ordered_food_List.get(i).getName());
        // System.out.println("food is: ");
        // }
        System.out.println("ordered item  button pressed");
        for (Food f : Ordered_food_List) {
            f.DisplayFoodWithId();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("in socket of passing");
                    //socketWrapper.setClientName(CustomerClientName);
                    socketWrapper.write(Ordered_food_List);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
