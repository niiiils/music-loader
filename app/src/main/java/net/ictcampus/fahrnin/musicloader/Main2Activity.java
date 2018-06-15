package net.ictcampus.fahrnin.musicloader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private static final String MESSAGE_SEARCH = "Search";
    private static final String MESSAGE_TITLE = "Title";
    private static final String MESSAGE_THUMBNAIL = "Thumbnail";
    private static final String MESSAGE_VIDEOID = "VideoID";

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = new Intent(this,DownloadActivity.class);

        Bundle bundle = getIntent().getExtras();
        String sq = bundle.getString(MESSAGE_SEARCH);
        YoutubeManager yM = new YoutubeManager();
        List<SearchResult> lr = yM.searchByString(sq,10);
        ArrayList<VideoEntry> vE = new ArrayList<VideoEntry>();
        for (SearchResult result : lr) {
            SearchResultSnippet s = result.getSnippet();
            VideoEntry e = new VideoEntry(result.getId().getVideoId(), s.getThumbnails().getDefault().getUrl(), s.getTitle(), 50, 5);
            vE.add(e);
            System.out.println(s);
            final VideoEntryAdapter improvise_overcome_adapt = new VideoEntryAdapter(vE,this);
            ListView lastvew = (ListView) findViewById(R.id.listView);
            lastvew.setPadding(lastvew.getPaddingLeft(),lastvew.getHeight(),lastvew.getPaddingRight(),0);
            lastvew.setAdapter(improvise_overcome_adapt);
            lastvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    VideoEntry vE1 =(VideoEntry) parent.getItemAtPosition(position);

                    intent.putExtra(MESSAGE_TITLE, vE1.getTitle());
                    intent.putExtra(MESSAGE_THUMBNAIL, vE1.getThumbnail());
                    intent.putExtra(MESSAGE_VIDEOID, vE1.getVideoID());
                    startActivity(intent);
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
