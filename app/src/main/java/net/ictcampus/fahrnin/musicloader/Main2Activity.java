package net.ictcampus.fahrnin.musicloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    public final String MESSAGE_SEARCH = "Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        String sq = bundle.getString(MESSAGE_SEARCH);
        YoutubeManager yM = new YoutubeManager();
        List<SearchResult> lr = yM.searchByString(sq,10);
        ArrayList<VideoEntry> vE = new ArrayList<VideoEntry>();
        for (SearchResult result : lr) {
            SearchResultSnippet s = result.getSnippet();
            VideoEntry e = new VideoEntry(1, s.getTitle(), 50, 5);
            vE.add(e);
            System.out.println(s);
            VideoEntryAdapter improvise_overcome_adapt = new VideoEntryAdapter(vE,this);
            ListView lastvew = (ListView) findViewById(R.id.listView);
            lastvew.setPadding(lastvew.getPaddingLeft(),lastvew.getHeight(),lastvew.getPaddingRight(),0);
            lastvew.setAdapter(improvise_overcome_adapt);
        }
    }
}
