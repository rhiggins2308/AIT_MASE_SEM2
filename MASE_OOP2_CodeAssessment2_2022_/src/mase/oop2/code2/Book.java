// Given.
package mase.oop2.code2;

public class Book {
    private String title, category;
    private Double price;

    public Book(String title, String category, Double price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", category=" + category + ", price=" + price + '}';
    }

}
