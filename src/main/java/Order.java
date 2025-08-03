import java.util.List;

public class Order implements IOrderTracker {
    private static int nextId = 1;
    private final int id;
    private final List<Product> products;
    private OrderStatus status;

    public Order(List<Product> products) {
        this.id = nextId++;
        this.products = products;
        this.status = OrderStatus.PLACED;
    }

    public int getId() { return id; }

    // Single Responsibility: только управление статусом
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public void returnOrder() {
        this.status = OrderStatus.RETURNED;
    }

    @Override
    public OrderStatus getStatus() {
        return status;
    }

    public List<Product> getProducts() {
        return products;
    }
}