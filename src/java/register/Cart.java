package register;

import java.util.HashMap;
/**
 *
 * @author rajat
 */
public class Cart {
    HashMap<String, Double> cartItems;
    public Cart(){
     cartItems = new HashMap<>();
      
    }
    public HashMap getCartItems(){
        return cartItems;
    }
    public void addToCart(String itemId, double price){
        cartItems.put(itemId, price);
    }
    public void deleteFromCart(String itemId){
        cartItems.remove(itemId);
    }
}