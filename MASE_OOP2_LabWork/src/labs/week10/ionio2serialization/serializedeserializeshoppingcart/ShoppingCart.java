package labs.week10.ionio2serialization.serializedeserializeshoppingcart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    /* API: A serializable class can declare its own serialVersionUID explicitly by declaring a 
       field named "serialVersionUID" that must be static, final, and of type long:
            ANY-ACCESS-MODIFIER static final long serialVersionUID = 42L; */
    private static final long serialVersionUID = 23L;
//    private static final long serialVersionUID = 24L;
    private int cartId;
    private ArrayList<Item> items; // Note that Item is also Serializable and has its own serialVersionUID 
    private int itemCount;
    private transient double cartTotal; // Note: transient

    public ShoppingCart(int cartId) {
        this.cartId = cartId;
        items = new ArrayList<>();
        itemCount = 0;
        cartTotal = 0;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public int getCartId() {
        return cartId;
    }

    public void addItem(Item item) {
        if (items.add(item)) {
            cartTotal += item.getCost();
        }
    }

    public int getCartSize() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }
    /*
    Java API - Serializable interface
        Classes that require special handling during the serialization and deserialization process must 
        implement special methods with these exact signatures:

            private void writeObject(java.io.ObjectOutputStream out) throws IOException
            private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException;    
    */

    // Default serialization for a class can be overridden using the writeObject 
    // and the readObject methods.
    // This method is only called during deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // defaultReadObject() - Read the non-static and non-transient fields of the current 
        // class from this stream. This may only be called from the readObject method of the 
        // class being deserialized.
        ois.defaultReadObject();
        // Recalculate the total if the cart was deserialized - cartTotal is 0 because it is 
        // not serialised in the first place (as it is transient)
        if (cartTotal == 0 && (items.size() > 0)) {
            for (Item item : items) {
                cartTotal += item.getCost();
            }
        }
        // writeObject(Object obj) i.e. writes out Object, hence we must use a cast here
        LocalDate date = (LocalDate)ois.readObject(); 
        System.out.println ("Restored Shopping Cart from: " + date);
    }

    // Default serialization for a class can be overridden using the writeObject 
    // and the readObject methods.
    // This method is only called during serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // defaultWriteObject - Write the non-static (serialVersionUID is a special exception) 
        // and non-transient fields of the current class to this stream. This may 
        // only be called from the writeObject method of the class being serialized.
        oos.defaultWriteObject();
        oos.writeObject(LocalDate.now());  // takes Object as its param
    }
}