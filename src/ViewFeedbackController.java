import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewFeedbackController {

    private String Email;
    private ArrayList<String> Feedback ;
    @FXML
    private TableView<FeedbackAdapter> table;

    @FXML private TableColumn<FeedbackAdapter, Integer> no;
    @FXML private TableColumn<FeedbackAdapter, String> f;

    private ObservableList<FeedbackAdapter> R;

    public void Receive(String e) throws SQLException
    {
        Email=e;
        no.setCellValueFactory(new PropertyValueFactory<>("Num"));
        f.setCellValueFactory(new PropertyValueFactory<>("Review"));

        Restaurant Res= new Restaurant();
        Feedback= Res.ViewReviews(Res.getRestaurantName(Res.getRestaurantIDViaEmail(Email)));

        R= FXCollections.observableArrayList();

        for (int i=0; i<Feedback.size(); i++)
        {
            FeedbackAdapter temp = new FeedbackAdapter(i+1, Feedback.get(i));
            R.add(temp);
        }

        table.setItems(R);

    }
    @FXML
    private Button edit;
    public void changeInfo(ActionEvent e)throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editRestaurant.fxml"));
        Parent root1=(Parent)fxmlLoader.load();

        editRestaurantController a=fxmlLoader.getController();
        a.Receive(Email);

        Scene current=edit.getScene();
        Scene search=new Scene(root1, 325, 525);
        Stage currentStage= (Stage)edit.getScene().getWindow();
        currentStage.setScene(search);

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
