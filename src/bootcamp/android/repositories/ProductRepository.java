package bootcamp.android.repositories;

import bootcamp.android.models.Product;
import bootcamp.android.services.ContentFetcher;
import bootcamp.android.services.ProductsParser;

import java.util.ArrayList;

public class ProductRepository {

    public ArrayList<Product> getProducts() {
        String strJSONData = new ContentFetcher().fetchResponse("http://xplorationstudio.com/sample_images/products_json.json");
        return new ProductsParser().parseProducts(strJSONData);
    }
}
