import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // Фильтрация по цене (Open-Closed: можно расширять новыми фильтрами)
    public List<Product> filterByPrice(double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Product> filterByManufacturer(String manufacturer) {
        return products.stream()
                .filter(p -> p.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }
}