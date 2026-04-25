package hce.web.hce_dmo_web1.models;
public class Product {

    public long id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
    public double rating_rate;
    public int rating_count;

    public Product() {
    }

    public Product(
            String title,
            double price,
            String description,
            String category,
            String image,
            double rating_rate,
            int rating_count) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating_rate = rating_rate;
        this.rating_count = rating_count;
    }

}