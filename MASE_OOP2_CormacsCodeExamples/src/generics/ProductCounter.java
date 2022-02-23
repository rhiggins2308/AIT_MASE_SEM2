package generics;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductCounter {

    private Map<String, Long> productCountMap = new HashMap<>();
    private Map<String, String> productNames = new TreeMap<>();

    public static void main(String[] args) {

        // List of part data
        String[] parts = new String[]{"1S01", "1S01", "1S01", "1S01", "1S01", "1S02", "1S02", "1S02", "1H01", "1H01", "1S02", "1S01", "1S01", "1H01", "1H01", "1H01", "1S02", "1S02", "1M02", "1M02", "1M02"};

        // Create Product Name Part Number map
        Map<String, String> productNames = new TreeMap<>();
        productNames.put("Blue Polo Shirt", "1S01");// key, value
        productNames.put("Black Polo Shirt", "1S02");
        productNames.put("Red Ball Cap", "1H01");
        productNames.put("Duke Mug   ", "1M02");

        // Create Product Counter Object and process
        ProductCounter pc1 = new ProductCounter(productNames);
        pc1.processList(parts);// initialise the productCountMap with e.g. "1S01" -> 7
        pc1.printReport();
    }

    public ProductCounter(Map productNames) {
        this.productNames = productNames;
    }

    public void processList(String[] list) {
        long curVal = 0;
//        String[] parts = new String[]{"1S01", "1S01", "1S01", "1S01", "1S01", "1S02", "1S02", "1S02", "1H01", "1H01", "1S02", "1S01", "1S01", "1H01", "1H01", "1H01", "1S02", "1S02", "1M02", "1M02", "1M02"};
        for (String itemNumber : list) {
            if (productCountMap.containsKey(itemNumber)) {
                curVal = productCountMap.get(itemNumber);
//                System.out.println("curVal == "+curVal);
                curVal++;
                productCountMap.put(itemNumber, new Long(curVal));// replaces old value if one exists e.g. "1S01" -> 2
            } else {
                productCountMap.put(itemNumber, 1L);// "1S01" -> 1
            }
        }
    }

    public void printReport() {
        System.out.println("=== Product Report ===");
//        productNames.put("Blue Polo Shirt", "1S01");// key, value
//        productNames.put("Black Polo Shirt", "1S02");
//        productNames.put("Red Ball Cap", "1H01");
//        productNames.put("Duke Mug   ", "1M02");
        for (String key : productNames.keySet()) {
            System.out.println("key: " + key); // e.g. "Black Polo Shirt"
            System.out.println("Going to productCountMap with the following key == "+productNames.get(key));
            System.out.println("\t\tCount: " + productCountMap.get(productNames.get(key))); // e.g. "6"
        }
    }
}
/* Output:
        === Product Report ===
        key: Black Polo Shirt
        Going to productCountMap map with the following key == 1S02
                        Count: 6
        key: Blue Polo Shirt
        Going to productCountMap map with the following key == 1S01
                        Count: 7
        key: Duke Mug   
        Going to productCountMap map with the following key == 1M02
                        Count: 3
        key: Red Ball Cap
        Going to productCountMap map with the following key == 1H01
                        Count: 5
 */
