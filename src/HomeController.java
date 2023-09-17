
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeController {
    private Restaurant myrestaurant;
    private HelloRestaurantClient main;
    private Vector<FoodTempClass>foods = new Vector<FoodTempClass>();

    @FXML
    private Button button;

    @FXML
    private ImageView image;

    @FXML
    private Label resID;

    @FXML
    private Label resName;

    @FXML
    private Label resPrice;

    @FXML
    private Label resScore;

    @FXML
    private Label resZipcode;

    @FXML
    private TableView tableView;

    ObservableList<Food> data;
    boolean initsome=true;


    // private void makeSimpleFoodlist(){
    //     for(Food f: myrestaurant.getAllFoods()){
    //         FoodTempClass tempFood=new FoodTempClass(f.getCategory(),f.getfoodname(),Double.toString(f.getPrice()));
    //         foods.add( tempFood);
    //     }
    // }

    private void initializeColumns() {
        TableColumn<Food, String> CategoryNameCol = new TableColumn<>("Category");
        CategoryNameCol.setMinWidth(80);
        CategoryNameCol.setCellValueFactory(new PropertyValueFactory<>("catagory"));

        TableColumn<Food, String> FoodnameCol = new TableColumn<>("Food_Name");
        FoodnameCol.setMinWidth(80);
        FoodnameCol.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));

        TableColumn<Food, String> PriceValueCol = new TableColumn<>("Price");
        PriceValueCol.setMinWidth(80);
        PriceValueCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        TableColumn<Food,Integer> order_count_col = new TableColumn<>("Order_Count");
       order_count_col.setMinWidth(80);
       order_count_col.setCellValueFactory(new PropertyValueFactory<Food, Integer>("count")); // , order_count_col

       tableView.setEditable(true);
        tableView.getColumns().addAll(CategoryNameCol, FoodnameCol, PriceValueCol,order_count_col);
    }

    public void init(String msg, Restaurant R) {
        this.myrestaurant = R;
        //this.foods=R.getAllFoods();
        resID.setText(Integer.toString(myrestaurant.getId()));
        resName.setText(myrestaurant.getName());
        resPrice.setText(myrestaurant.getPrice());
        resZipcode.setText(myrestaurant.getZip_code());
        resScore.setText(Double.toString(myrestaurant.getScore()));
        Image img = new Image(Main.class.getResourceAsStream("1.png"));
        image.setImage(img);

       // makeSimpleFoodlist();
        //initializeColumns();
    }


    public void load() {
        if (initsome) {
            initializeColumns();
            initsome = false;
        }

        data = FXCollections.observableArrayList(
           myrestaurant.getAllFoods()
            //new Food(1, "a", "b", 50)
        );

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(HelloRestaurantClient main) {
        this.main = main;
    }

}
