package bootcamp.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static bootcamp.android.constants.Constants.DESCRIPTION_KEY;
import static bootcamp.android.constants.Constants.TITLE_KEY;
import static bootcamp.android.constants.Constants.DRAWABLE_KEY;

public class ShoppingItemsListingActivity extends Activity {

	private ProductRepository productRepository;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		final GridView gridView = (GridView) findViewById(R.id.grid_view);

		gridView.setAdapter(new ShoppingItemsListAdapter(this));

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

				Product product = (Product) adapterView.getAdapter().getItem(position);
				Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
				intent.putExtra(TITLE_KEY, product.getTitle());
				intent.putExtra(DESCRIPTION_KEY, product.getDescription());
				intent.putExtra(DRAWABLE_KEY, product.getDrawable());
				startActivity(intent);
			}
		});
	}


}
