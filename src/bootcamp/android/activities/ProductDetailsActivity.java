package bootcamp.android.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
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
        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap bitmap = imageDownloader.downloadImage(imageUrl);
        ImageView imageView = (ImageView) findViewById(R.id.product_image);
        imageView.setImageBitmap(bitmap);
        TextView issueDescription = (TextView) findViewById(R.id.product_description);
        issueDescription.setText(description);
    }
}
