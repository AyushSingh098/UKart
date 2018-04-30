package register;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dilukshan Mahendra
 */
public class item {

   //public String id;
   public String name;
   public double price;
   public int quant;
   public int itemid;

    public item(String a, double b, int c, int d) {
        this.name = a;
        this.price = b;
        this.quant = c;
        this.itemid = d;
    }
    public String getName()
    {            return name;
    
    }
    public double getPrice()
    {            return price;
    
    }
    public int getQuant()
    {
        return quant;
    }
    public int getID()
    {
        return itemid;
    }
}
