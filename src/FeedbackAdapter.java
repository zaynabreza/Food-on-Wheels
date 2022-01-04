import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class FeedbackAdapter {
    SimpleStringProperty review;
    SimpleIntegerProperty num;

    FeedbackAdapter(int i, String rev)
    {
        this.review = new SimpleStringProperty(rev);
        this.num = new SimpleIntegerProperty(i);
    }


    public String getReview() {
        return review.get();
    }

    public void setReview(String review) {
        this.review = new SimpleStringProperty(review);
    }

    public int getNum() {
        return num.get();
    }

    public void setNum(int num) {
        this.num = new SimpleIntegerProperty(num);
    }
}
