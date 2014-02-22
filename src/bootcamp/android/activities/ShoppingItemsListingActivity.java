package bootcamp.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidplugins.Callback;
import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.ArrayList;
import static bootcamp.android.constants.Constants.*;

public class ShoppingItemsListingActivity extends Activity {

    private ProductRepository productRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final GridView gridView = (GridView) findViewById(R.id.grid_view);
        final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...", true, true);
        ProductRepository productRepository = new ProductRepository();
        productRepository.getProducts(productsCallback(gridView, progressDialog));
    }

    private Callback<ArrayList<Product>> productsCallback(final GridView gridView, final ProgressDialog progressDialog) {
        return new Callback<ArrayList<Product>>() {
            @Override
            public void execute(ArrayList<Product> products) {
                renderProducts(gridView, products);
                progressDialog.dismiss();
            }
        };
    }
    private void renderProducts(GridView gridView, ArrayList<Product> products) {
        gridView.setAdapter(new ShoppingItemsListAdapter(this, products));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Product product = (Product) adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra(PRODUCT_KEY, product);
                startActivity(intent);
            }
        });
    }
}
