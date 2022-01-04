import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.w3c.dom.Text;


import java.sql.SQLException;

public class GenNotificationController {

    private String Email;

    public void Receive(String e)
    {
        Email=e;
    }

    @FXML
    private TextField notif, promo, dis;
    @FXML private Button submit;
    @FXML private Label msg;
    public void generate(ActionEvent e) throws SQLException {
        String Notif=notif.getText();
        if (Notif.equals("") || Notif==null)
        {
            msg.setText("Enter some message");
        }
        else {
            String Promo = promo.getText();
            int d = Integer.parseInt(dis.getText());

            Notification newNotif = new Notification();

            SalesManager M = new SalesManager();
            M.GenerateNotification(newNotif, Notif, d, Promo);
            //now push notification
            Notifications notificationBuilder=Notifications.create().title("Notification").text(Notif).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
            notificationBuilder.show();

            msg.setText("Customers Notified");

        }
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
