package bootcamp.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import bootcamp.android.R;
import bootcamp.android.fragments.ProductDetailsFragment;
import bootcamp.android.models.Product;

import java.util.ArrayList;

import static bootcamp.android.constants.Constants.*;


public class ProductDetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_container);

        Bundle extraArguments = getIntent().getExtras();
        ArrayList<Product> products = extraArguments.getParcelableArrayList(PRODUCTS_KEY);
        int current = extraArguments.getInt(CURRENT_PRODUCT_KEY);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ProductDetailsPagerAdapter(getSupportFragmentManager(),products));
        viewPager.setCurrentItem(current);

    }


    private class ProductDetailsPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Product> products;

        public ProductDetailsPagerAdapter(FragmentManager fm, ArrayList<Product> products) {
            super(fm);
            this.products = products;
        }

        @Override
        public Fragment getItem(int index) {
            Fragment productDetailsFragment = new ProductDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(PRODUCT_KEY, products.get(index));
            productDetailsFragment.setArguments(bundle);
            return productDetailsFragment;
        }

        @Override
        public int getCount() {
            return products.size();
        }
    }
}
