package itp341.chung.catherine.feelsgoodman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itp341.chung.catherine.feelsgoodman.model.GiphyGifData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by catherinechung on 4/25/17.
 */

public class LaughActivity extends AppCompatActivity {

    private GiphyGifData giphyGifData;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laugh);

        // create references to widgets
        imageView = (ImageView) findViewById(R.id.memeImage);

        // start laugh fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new LaughFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_laugh, fragment);
        ft.commit();

    }

    // derived from https://github.com/nmillward/GifLoadingExample

    public void getGiphy() {
        //URL Format: http://api.giphy.com/v1/gifs/search?q=cute+cat&api_key=dc6zaTOxFJmzC&limit=1&offset=0
        //Random Search URL: http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC&tag=cute+funny+cat+kitten
        String apiKey = "dc6zaTOxFJmzC"; //Giphy's Public API Key

        String giphyUrl =
                "http://api.giphy.com/v1/gifs/random" +
                        "?api_key=" +
                        apiKey +
                        "&tag=" +
                        "cat";


        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(giphyUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            giphyGifData = getGif(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        }
                    }
                    catch (IOException e) {

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Network is not available!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private GiphyGifData getGif(String jsonData) throws JSONException {
        JSONObject giphy = new JSONObject(jsonData);
        JSONObject data = giphy.getJSONObject("data");

        GiphyGifData gif = new GiphyGifData();
        gif.setUrl(data.getString("image_url"));

        return gif;
    }

    private void updateDisplay() {
        String gifUrl = giphyGifData.getUrl();

        Glide.with(LaughActivity.this)
                .load(gifUrl)
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        //check isRefreshing
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getGiphy();
    }


}
