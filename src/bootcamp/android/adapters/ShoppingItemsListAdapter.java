package bootcamp.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import bootcamp.android.R;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemsListAdapter extends BaseAdapter {

	private final Context context;
	public List<Product> products = new ArrayList<Product>();

	public ShoppingItemsListAdapter(Context context){
		this.context = context;
		ProductRepository productRepository = new ProductRepository();
		products = productRepository.getProducts();
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if( convertView == null ){
			convertView = LayoutInflater.from(context).inflate(R.layout.cell_layout, null);
		}

		ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
		TextView textView = (TextView) convertView.findViewById(R.id.title);
		Product product = products.get(position);
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), product.getDrawable());
		imageView.setImageBitmap(bitmap);
		textView.setText(product.getTitle());

		return convertView;
	}
}

