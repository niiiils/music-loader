package net.ictcampus.fahrnin.musicloader;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView sv;
    private List<SearchResult> lr;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this,ScrollingActivity.class);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        setContentView(R.layout.activity_main);


        sv = (SearchView) findViewById(R.id.searchBar);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                waduhec(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
    public void waduhec(String s) {
        intent.putExtra("Search",s);
        startActivity(intent);
    }
}