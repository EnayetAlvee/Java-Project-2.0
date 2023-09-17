

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class FoodTempClass  {
    private final SimpleStringProperty simplecategory;
    private final SimpleStringProperty simplefoodName;
    private final SimpleStringProperty simplePrice;
    //private final SimpleStringProperty simplecount;

    FoodTempClass(String fName, String lName, String simplePrice) {
        this.simplecategory = new SimpleStringProperty(fName);
        this.simplefoodName = new SimpleStringProperty(lName);
        this.simplePrice = new SimpleStringProperty(simplePrice);
        // this.button = new Button("Click");
        // button.setOnAction( e -> {
        //             System.out.println(ge simplecategory() + " " + getsimplefoodName() + " " + getsimplePrice());
        //             Alert a = new Alert(Alert.AlertType.INFORMATION);
        //             a.setContentText(ge simplecategory() + " " + getsimplefoodName() + " " + getsimplePrice());
        //             a.showAndWait();
        //         }
        // );

    }

    public String getsimplecategory() {
        return simplecategory.get();
    }
    public void  setsimplecategory(String fName) {
     simplecategory.set(fName);
    }

    public String getsimplefoodName() {
        return simplefoodName.get();
    }
    public void setsimplefoodName(String fName) {
        simplefoodName.set(fName);
    }

    public String getsimplePrice() {
        return simplePrice.get();
    }
    public void setsimplePrice(String fName) {
        simplePrice.set(fName);
    }

    public String toString() {
        return simplecategory + ", " + simplefoodName + ", " + simplePrice;
    }

    // public Button getButton() {
    //     return button;
    // }


}

