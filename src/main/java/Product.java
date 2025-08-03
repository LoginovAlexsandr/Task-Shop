public class Product implements IRatingProvider {
    private final int id;
    private final String name;
    private final double price;
    private final String manufacturer;
    private double rating;

    public Product(int id, String name, double price, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.rating = Constants.DEFAULT_RATING; // Избегание магических чисел
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getManufacturer() { return manufacturer; }

    @Override
    public double getRating() { return rating; }

    @Override
    public void setRating(double rating) {
        if (rating >= Constants.MIN_RATING && rating <= Constants.MAX_RATING) {
            this.rating = rating;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manufacturer='" + manufacturer + '\'' +
                ", rating=" + rating +
                '}';
    }
}