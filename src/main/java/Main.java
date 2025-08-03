import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    private static final List<Order> orders = new ArrayList<>();
    private static final RatingService ratingService = new RatingService();
    private static final RecommendationService recommendationService = new RecommendationService(store, new Product(0, "", 0, "")); // Пример, использует Product как провайдер

    public static void main(String[] args) {
        initStore(); // Инициализация тестовых данных

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Вывести все товары");
            System.out.println("2. Фильтр по цене (max)");
            System.out.println("3. Фильтр по производителю");
            System.out.println("4. Добавить товар в корзину");
            System.out.println("5. Просмотреть корзину");
            System.out.println("6. Оформить заказ");
            System.out.println("7. Трекинг заказа");
            System.out.println("8. Вернуть заказ");
            System.out.println("9. Поставить рейтинг товару");
            System.out.println("10. Рекомендации");
            System.out.println("exit - Выход");

            String choice = scanner.nextLine();
            if (choice.equals("exit")) break;

            switch (choice) {
                case "1" -> listProducts(store.getAllProducts());
                case "2" -> {
                    System.out.print("Введите max цену: ");
                    double maxPrice = Double.parseDouble(scanner.nextLine());
                    listProducts(store.filterByPrice(maxPrice));
                }
                case "3" -> {
                    System.out.print("Введите производителя: ");
                    String manufacturer = scanner.nextLine();
                    listProducts(store.filterByManufacturer(manufacturer));
                }
                case "4" -> {
                    System.out.print("Введите ID товара: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Product product = findProductById(id);
                    if (product != null) {
                        cart.addItem(product);
                        System.out.println("Товар добавлен в корзину.");
                    } else {
                        System.out.println("Товар не найден.");
                    }
                }
                case "5" -> {
                    System.out.println("Корзина:");
                    listProducts(cart.getItems());
                    System.out.println("Итого: " + cart.getTotal());
                }
                case "6" -> {
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Корзина пуста.");
                    } else {
                        Order order = new Order(cart.getItems());
                        orders.add(order);
                        cart.clear();
                        System.out.println("Заказ оформлен. ID: " + order.getId());
                    }
                }
                case "7" -> {
                    System.out.print("Введите ID заказа: ");
                    int orderId = Integer.parseInt(scanner.nextLine());
                    Order order = findOrderById(orderId);
                    if (order != null) {
                        System.out.println("Статус заказа: " + order.getStatus());
                    } else {
                        System.out.println("Заказ не найден.");
                    }
                }
                case "8" -> {
                    System.out.print("Введите ID заказа: ");
                    int orderId = Integer.parseInt(scanner.nextLine());
                    Order order = findOrderById(orderId);
                    if (order != null) {
                        order.returnOrder();
                        System.out.println("Заказ возвращен.");
                    } else {
                        System.out.println("Заказ не найден.");
                    }
                }
                case "9" -> {
                    System.out.print("Введите ID товара: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Product product = findProductById(id);
                    if (product != null) {
                        System.out.print("Введите рейтинг (0-5): ");
                        double rating = Double.parseDouble(scanner.nextLine());
                        ratingService.rateProduct(product, rating);
                        System.out.println("Рейтинг обновлен.");
                    } else {
                        System.out.println("Товар не найден.");
                    }
                }
                case "10" -> {
                    List<Product> recs = recommendationService.getRecommendations(Constants.RECOMMENDATION_COUNT);
                    System.out.println("Рекомендации:");
                    listProducts(recs);
                }
                default -> System.out.println("Неверная команда.");
            }
        }
    }

    private static void initStore() {
        store.addProduct(new Product(1, "Laptop", 999.99, "Apple"));
        store.addProduct(new Product(2, "Phone", 599.99, "Samsung"));
        store.addProduct(new Product(3, "Headphones", 199.99, "Sony"));
        store.addProduct(new Product(4, "Keyboard", 49.99, "Logitech"));
    }

    private static void listProducts(List<Product> products) {
        products.forEach(System.out::println);
    }

    private static Product findProductById(int id) {
        return store.getAllProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static Order findOrderById(int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }
}