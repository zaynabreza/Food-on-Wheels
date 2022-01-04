import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
public class tempOrder {

    public String cusEmail;
    public String Restaurant;
    public int Rider;
    public String Promo;
    public String Comments;
    public double tax;
    public double deliverycharges;
    public java.sql.Date date;
    public String status;

    public double total;
    public int max_items;
    public ArrayList<items> I;
    tempOrder(String email, String Res)
    {
        cusEmail=email;
        Restaurant=Res;
        I= new ArrayList<items>();
        total=0;
        max_items=5;
    }




    public String Add(String Item, Double Price)
    {
        boolean matching=false;

        for (int i=0; i<I.size(); i++)
        {
            if (I.get(i).name.equals(Item))
            {
                matching=true;
            }
        }

        if (I.size() >=max_items && !matching)
        {
            System.out.println("More than 5 items");
            status="full";
            return status;
        }
        else if (matching)
        {
            for (int i=0; i<I.size(); i++)
            {
                if (I.get(i).name.equals(Item))
                {
                    items temp= new items(I.get(i).name, I.get(i).quantity+1);
                    I.set(i, temp);
                    total+=Price;
                    System.out.println("Just added "+temp.name+" "+temp.quantity);
                    status="Added "+temp.name+" total: "+total;
                    System.out.println("Total price "+total);
                    return status;
                }
            }
        }

        I.add(new items(Item, 1));
        total+=Price;
        System.out.println("Just added "+I.get(I.size()-1).name+" "+I.get(I.size()-1).quantity);
        System.out.println("Total price "+total);
        status="Added "+I.get(I.size()-1).name+" total: "+total;
        return status;



    }

    public void Reorder(ArrayList<String> Item, ArrayList<Integer> Quantity, ArrayList<Double> Price)
    {
        for (int j=0; j<Price.size(); j++)
        {
            this.I.add(new items(Item.get(j), Quantity.get(j)));
            this.total+=Quantity.get(j) * Price.get(j);
        }
    }


}
