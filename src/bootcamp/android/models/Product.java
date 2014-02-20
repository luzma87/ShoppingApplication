package bootcamp.android.models;

public class Product {

    private final String image_url;
    private String title;
    private String description;

    public Product(String title, String description, String image_url) {
        this.title = title;
        this.description = description;
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return image_url;
    }

    @Override
    public String toString() {
        return title;
    }
}

