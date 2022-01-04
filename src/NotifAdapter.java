import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NotifAdapter {
    SimpleIntegerProperty num;
    SimpleStringProperty message;

    NotifAdapter(int x, String y)
    {
        this.num = new SimpleIntegerProperty(x);
        this.message = new SimpleStringProperty(y);

    }

    public int getNum() {
        return num.get();
    }

    public void setNum(int num) {
        this.num = new SimpleIntegerProperty(num);
    }

    public String getMessage() {
        return message.get();
    }

    public void setMessage(String message) {
        this.message = new SimpleStringProperty(message);
    }
}
