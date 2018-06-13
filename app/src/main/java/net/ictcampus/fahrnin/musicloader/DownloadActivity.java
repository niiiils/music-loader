package net.ictcampus.fahrnin.musicloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.ictcampus.fahrnin.musicloader.yt2mp3.DownloadMP3;

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
                DownloadMP3.downloadMP3(bundle.getString("VideoID"),"/storage/sdcard0/Downloads");
                System.out.print("sassasasas");
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
