package lectures.week3.sortingviacomparablecomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorting_CollectionsSort {

	public static void main(String[] args) {
		comparable();
	}

	private static void comparable() {
		List<Product> products = new ArrayList<>();
		
		products.add(new Product(99));
		products.add(new Product(9));
		products.add(new Product(19));
		
		Collections.sort(products);
		System.out.println(products); 	// [Product{id=9}, Product{id=19}, Product{id=99}]
	}
}

class Product implements Comparable<Product> {

	private Integer id;
	
	public Product(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Product{ id=" + this.getId() + " }"; 
	}
	
	// equal objects should have the same haschode
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + id;
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product otherProduct = (Product) obj;
			if (this.id == otherProduct.id) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Product product) { // specifies "natural ordering" for Product
		// delegate to Integer which implements Comparable<Integer>
		return id.compareTo(product.id);		
//		return Integer.compare(this.id, product.id);	// another option
//		return id-product.id;		// sorts ascending by id
	}
}