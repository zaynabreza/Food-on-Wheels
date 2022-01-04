import java.util.ArrayList;

public class Order_Line_Items_List {
    public int num;
    public ArrayList<String> Item;
    public ArrayList<Double> Price;
    public ArrayList<Integer> Quantity;
    public int Rest_ID;

    public Order_Line_Items_List(ArrayList<String> i, ArrayList<Double> p, ArrayList<Integer>Q, int r){
        this.Item=i;
        this.Price=p;
        this.Quantity=Q;
        this.Rest_ID=r;
    }
    public Order_Line_Items_List(ArrayList<String>  i,ArrayList<Integer>Q,int r){
        this.Item=i;
        this.Quantity=Q;
        this.Rest_ID=r;
    }
    public Order_Line_Items_List(ArrayList<Double> P){
        this.Price=P;
    }
    void Add_Price(ArrayList<Double> Price){
        this.Price=Price;
    }
}
