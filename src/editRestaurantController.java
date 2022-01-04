import java.sql.SQLException;
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

public class editRestaurantController {

    private String Email;
    private String Password;
    private String Name;

    public void Receive(String e)   throws SQLException {
        Email = e;

    }
    @FXML private RadioButton openclose;
    @FXML private Button change, submit;
    @FXML private Label msg;
    public void opencloseRes(ActionEvent e)throws SQLException{
        Restaurant R= new Restaurant();
        Name=R.getRestaurantName(R.getRestaurantIDViaEmail(Email));
        System.out.println(Name);
        R.OpenCloseRestaurant(R.getRestaurantName(R.getRestaurantIDViaEmail(Email)), openclose.isSelected());
        if (openclose.isSelected())
            msg.setText("Opened");
        else if (!openclose.isSelected())
            msg.setText("Closed");
    }
    @FXML TextField add;
    @FXML PasswordField pass;

    public void updateInfo(ActionEvent e) throws Exception
    {
        Restaurant R= new Restaurant();
        Name=R.getRestaurantName(R.getRestaurantIDViaEmail(Email));

        String Address = add.getText();
        String Password = pass.getText();
        if (( Password == null && Address==null) || Password.equals("")
                && Address.equals("")) {
            msg.setText("Please fill at least one");
        }
        else
        {
            String Phone="23456";
            if (Password!=null && !Password.equals("")) { //password enetered, check password
                InputVerification Input = new InputVerification(); //create object of input verification
                String status = Input.Validate_Email_Password(Email, Password, Phone); //check if valid
                if (status == "Valid") {  //details valid and ready to send
                    msg.setText("Password is Valid");

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManagerSignIn3.fxml"));
                    Parent root1=(Parent)fxmlLoader.load();

                    ManagerSignIn3Controller Manager=fxmlLoader.getController();
                    Manager.Receive(Name, Email, Password,Address);

                    Stage stage=new Stage();
                    stage.setScene(new Scene(root1, 325, 525));
                    stage.show();

                } else {
                    msg.setText("Password is Invalid");
                }
            }
            else //password not entered
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManagerSignIn3.fxml"));
                Parent root1=(Parent)fxmlLoader.load();

                ManagerSignIn3Controller Manager=fxmlLoader.getController();
                Manager.Receive(Name, Email, Password,Address);

                Stage stage=new Stage();
                stage.setScene(new Scene(root1, 325, 525));
                stage.show();
                msg.setText("Details Updated");
            }
        }
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
    private Button menu;
    public void changeMenu(ActionEvent e)throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RMenu.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        RMenuController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=menu.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)menu.getScene().getWindow();
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



}
