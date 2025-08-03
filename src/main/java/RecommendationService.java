import java.util.List;
import java.util.stream.Collectors;

public class RecommendationService {
    private final Store store;
    private final IRatingProvider ratingProvider; // Dependency Inversion: зависимость от абстракции

    public RecommendationService(Store store, IRatingProvider ratingProvider) {
        this.store = store;
        this.ratingProvider = ratingProvider;
    }

    public List<Product> getRecommendations(int count) {
        return store.getAllProducts().stream()
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .limit(count)
                .collect(Collectors.toList());
    }
}