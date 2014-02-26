package bootcamp.android.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidplugins.Callback;
import androidplugins.imagefetcher.ImageFetcher;
import bootcamp.android.R;
import bootcamp.android.services.ImageDownloader;

import static bootcamp.android.constants.Constants.*;


public class ProductDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString(TITLE_KEY);
        String description = extras.getString(DESCRIPTION_KEY);
        String imageUrl = extras.getString(IMAGE_URL_KEY);
        TextView imageTitle = (TextView) findViewById(R.id.product_title);
        imageTitle.setText(title);
        ImageView imageView = (ImageView) findViewById(R.id.product_image);
        ImageFetcher imageFetcher = new ImageFetcher(bitmapCallback(imageView), this);
        imageFetcher.execute(imageUrl);
        TextView issueDescription = (TextView) findViewById(R.id.product_description);
        issueDescription.setText(description);
    }

    private Callback<Bitmap> bitmapCallback(final ImageView imageView) {
      return new Callback<Bitmap>() {
        @Override
        public void execute(Bitmap object) {
          imageView.setImageBitmap(object);
        }
      };
    }



}
