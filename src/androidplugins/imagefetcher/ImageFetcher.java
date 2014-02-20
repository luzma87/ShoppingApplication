package androidplugins.imagefetcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import androidplugins.Callback;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ImageFetcher extends AsyncTask<String, Void, Bitmap> {
    private Callback<Bitmap> bitmapCallback;
    private Context context;
    private static final String IMAGE_CACHING_PREFERENCES_FILE_NAME = "ImageCachingPreferences";

    public ImageFetcher(Callback<Bitmap> bitmapCallback, Context context) {
        this.bitmapCallback = bitmapCallback;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String imageUrl = urls[0];
        return  downloadAndCacheImage(imageUrl);

    }

    private Bitmap downloadAndCacheImage(String imageUrl) {
        Bitmap imageBitmap = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            URL url = new URL(imageUrl);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            HttpRequestBase httpRequest = new HttpGet(uri);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream content = httpEntity.getContent();
                imageBitmap = BitmapFactory.decodeStream(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(imageBitmap != null) cacheImage(imageBitmap, imageUrl);
        return imageBitmap;
    }

    private void cacheImage(Bitmap result, String imageUrl) {
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        bitmapCallback.execute(bitmap);
    }
}