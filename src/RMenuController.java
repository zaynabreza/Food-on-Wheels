import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RMenuController {

    private String Email;
    private String Password;

    public void Receive(String e)   throws SQLException {
        Email = e;

    }


    @FXML private TextField name, price;
    @FXML private RadioButton avail;
    @FXML private Label msg;

    public void addtoDeals(ActionEvent e) throws SQLException
    {
        String N=name.getText();
        double P=Double.parseDouble(price.getText());
        Restaurant R= new Restaurant();
        String status=R.addToDeals(R.getRestaurantName(R.getRestaurantIDViaEmail(Email)), N, P);
        if (status.equals("Added"))
        {
            msg.setText("Added to Menu");
        }
        else msg.setText(status);
    }
    public void addtoMenu(ActionEvent e) throws SQLException
    {
        String N=name.getText();
        double P=Double.parseDouble(price.getText());
        if (price.getText().equals("") || price.getText()==null || N==null || N.equals(""))
        {
            msg.setText("Enter all fields please");
        }
        else {
            boolean A = avail.isSelected();
            Restaurant R = new Restaurant();
            String status = R.addToMenu(R.getRestaurantName(R.getRestaurantIDViaEmail(Email)), N, P, A);
            if (status.equals("Added")) {
                msg.setText("Added to Menu");
            } else msg.setText(status);
        }
    }
    public void editMenu(ActionEvent e) throws SQLException
    {
        String N=name.getText();
        if (price.getText().equals("") || price.getText()==null || N==null || N.equals(""))
        {
            msg.setText("Enter all fields please");
        }
        else {
            double P = Double.parseDouble(price.getText());
            boolean A = avail.isSelected();
            Restaurant R = new Restaurant();
            R.editRestaurantMenu(R.getRestaurantName(R.getRestaurantIDViaEmail(Email)), N, P, A);
            msg.setText("Done");
        }
    }
    public void removeItem(ActionEvent e) throws SQLException
    {
        String N=name.getText();


        Restaurant R= new Restaurant();
        R.removeFromMenu(R.getRestaurantName(R.getRestaurantIDViaEmail(Email)), N);
        msg.setText("Done");
    }
    @FXML
    private Button status;
    public void changeStatus(ActionEvent e)throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RestaurantA.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        RestaurantAController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=status.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)status.getScene().getWindow();
        currentStage.setScene(search);

    }



    @FXML
    private Button logout;
    public void LogOut(ActionEvent e)throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene current = logout.getScene();
        Scene Signin = new Scene(root, 325, 525);
        Stage currentStage = (Stage) logout.getScene().getWindow();
        currentStage.setScene(Signin);

    }
    @FXML
    private Button editbutton;
    public void changeInfo(ActionEvent e)throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editRestaurant.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        editRestaurantController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=editbutton.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)editbutton.getScene().getWindow();
        currentStage.setScene(search);

    }
}
