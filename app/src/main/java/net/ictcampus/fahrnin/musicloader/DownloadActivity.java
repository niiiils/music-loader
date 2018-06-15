package net.ictcampus.fahrnin.musicloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        TextInputEditText input = (TextInputEditText) findViewById(R.id.inputView);
        bundle = getIntent().getExtras();
        Button downloadBtn = (Button) findViewById(R.id.downloadButton);

        input.setHint("Song Title");
        loadThumbnail(bundle.getString("Thumbnail"));
        input.setText(bundle.getString("Title"));

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask task = new AsyncTask(){

                    @Override
                    protected Object doInBackground(Object[] objects) {

                        Mp3Downloader.downloadAsMp3(bundle.getString("VideoID"));
                        return null;
                    }

                };

                task.execute();
            }
        });
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public void loadThumbnail(String src){
        ImageView imageView = (ImageView) findViewById(R.id.imageViewthumbnail);
        Bitmap image = getBitmapFromURL(src) ;

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(image);
    }


}
