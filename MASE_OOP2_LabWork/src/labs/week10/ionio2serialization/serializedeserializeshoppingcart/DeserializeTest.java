package labs.week10.ionio2serialization.serializedeserializeshoppingcart;

import labs.week10.ionio2serialization.serializedeserializeshoppingcart.ShoppingCart;
import labs.week10.ionio2serialization.serializedeserializeshoppingcart.Item;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.text.NumberFormat;
import java.util.List;

/* 
The error below occurs if you serialise with serialVersionUID = 23L (ShoppingCart.java) and 
then dererialise with  serialVersionUID = 20L (ShoppingCart.java). The CartID was 400.

Enter the ID of the cart file to deserialize or q exit.
400
Exception deserializing .cart400.ser: java.io.InvalidClassException: com.example.domain.ShoppingCart; local class incompatible: stream classdesc serialVersionUID = 23, local class serialVersionUID = 20
Java Result: 255

*/

public class DeserializeTest {

    public static void main(String[] args) {
        String directory = "./"; // project home directory
//        String directory = "D:\\labs\\10-IO_Fundamentals\\practices\\";
        String cartId = null;
        System.out.println("Enter the ID of the cart file to deserialize or q exit.");
        // Wrap the System.in InputStream with a BufferedReader to read
        // each line from the keyboard.
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            cartId = in.readLine();
            if (cartId.equals("q")) {
                System.exit(0);
            }
        } catch (IOException e) { // Catch any IO exceptions.
            System.out.println("Exception: " + e);
            System.exit(-1);
        }

        // Attempt to open the file and deserialize it into an object
        String cartFile = directory + "cart" + cartId + ".ser";
        ShoppingCart cart = null;
        try (FileInputStream fis = new FileInputStream(cartFile);
                ObjectInputStream in = new ObjectInputStream(fis)) {
            cart = (ShoppingCart) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Exception deserializing " + cartFile + ": " + e);
            System.exit(-1);
        }
        System.out.println("Successfully deserialized shopping cart with ID: " + cart.getCartId());
        System.out.println("Shopping cart contains: ");
        List<Item> cartContents = cart.getItems();
        for (Item item : cartContents) {
            System.out.println(item);
        }
        System.out.println("Shopping cart total: "
                + NumberFormat.getCurrencyInstance().format(cart.getCartTotal()));
    }
}
/*
Enter the ID of the cart file to deserialize or q exit.
2
Restored Shopping Cart from: 24-May-2015            - this comes from ShoppingCart::readObject()
Successfully deserialized shopping cart with ID: 2
Shopping cart contains: 
Item ID: 101 Description: Duke Plastic Circular Flying Disc Cost: 10.95
Item ID: 123 Description: Duke Soccer Pro Soccer ball Cost: 29.95
Item ID: 45 Description: Duke "The Edge" Tennis Balls - 12-Ball Bag Cost: 17.49
Shopping cart total: €58.39
*/