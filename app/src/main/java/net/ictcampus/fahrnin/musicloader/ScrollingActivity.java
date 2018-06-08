package net.ictcampus.fahrnin.musicloader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Video;

import java.util.ArrayList;
import java.util.List;

//import com.example.quickstart.R;

public class ScrollingActivity extends AppCompatActivity {
    public final String MESSAGE_SEARCH = "Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();
                }
            });
        }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        String sq = bundle.getString(MESSAGE_SEARCH);
        YoutubeManager yM = new YoutubeManager();
        List<SearchResult> lr = yM.searchByString(sq,10);
        for (SearchResult result : lr) {
            ArrayList<VideoEntry> vE = new ArrayList<VideoEntry>();
            SearchResultSnippet s = result.getSnippet();
            VideoEntry e = new VideoEntry(0, s.getTitle(), 5, 5);
            vE.add(e);
            System.out.println(s);
            VideoEntryAdapter improvise_overcome_adapt = new VideoEntryAdapter(vE,this);
            ListView lastvew = (ListView) findViewById(R.id.item1);
            lastvew.setPadding(lastvew.getPaddingLeft(),lastvew.getHeight(),lastvew.getPaddingRight(),0);
            lastvew.setAdapter(improvise_overcome_adapt);
        }
    }
}