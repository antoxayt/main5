import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // ===== ENTITY =====

    static class Visitor {
        Long id;
        String name;
        int age;
        String gender;

        public Visitor(Long id, String name, int age, String gender) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }

    enum CuisineType {
        EUROPEAN, ITALIAN, CHINESE
    }

    static class Restaurant {
        Long id;
        String name;
        String description;
        CuisineType cuisineType;
        double averageCheck;
        BigDecimal rating;

        public Restaurant(Long id, String name, String description,
                          CuisineType cuisineType, double averageCheck, BigDecimal rating) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.cuisineType = cuisineType;
            this.averageCheck = averageCheck;
            this.rating = rating;
        }
    }

    static class Review {
        Long visitorId;
        Long restaurantId;
        int rating;
        String text;

        public Review(Long visitorId, Long restaurantId, int rating, String text) {
            this.visitorId = visitorId;
            this.restaurantId = restaurantId;
            this.rating = rating;
            this.text = text;
        }
    }

    // ===== "REPOSITORIES" =====

    static List<Visitor> visitors = new ArrayList<>();
    static List<Restaurant> restaurants = new ArrayList<>();
    static List<Review> reviews = new ArrayList<>();

    // ===== LOGIC =====

    public static void main(String[] args) {

        // добавляем посетителей
        visitors.add(new Visitor(1L, "Андрей", 19, "M"));
        visitors.add(new Visitor(2L, null, 22, "F"));

        // добавляем ресторан
        restaurants.add(new Restaurant(1L, "Pizza", "Good",
                CuisineType.ITALIAN, 15.0, BigDecimal.ZERO));

        // добавляем отзывы
        reviews.add(new Review(1L, 1L, 5, "Отлично"));
        reviews.add(new Review(2L, 1L, 3, "Норм"));

        // пересчёт рейтинга
        recalc(1L);

        // вывод
        for (Restaurant r : restaurants) {
            System.out.println(r.name + " рейтинг: " + r.rating);
        }
    }

    static void recalc(Long restaurantId) {
        int sum = 0;
        int count = 0;

        for (Review r : reviews) {
            if (r.restaurantId.equals(restaurantId)) {
                sum += r.rating;
                count++;
            }
        }

        if (count == 0) return;

        double avg = (double) sum / count;

        for (Restaurant r : restaurants) {
            if (r.id.equals(restaurantId)) {
                r.rating = BigDecimal.valueOf(avg);
            }
        }
    }
}